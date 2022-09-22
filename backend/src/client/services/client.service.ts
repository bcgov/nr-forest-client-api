import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import axios from 'axios';
import { CodeDescr } from 'src/core/CodeDescrType';
import { Repository } from 'typeorm';
import { ClientEntity } from '../entities/client.entity';
import { Client } from '../entities/client.interface';

@Injectable()
export class ClientService {
  constructor(
    @InjectRepository(ClientEntity)
    private clientRepository: Repository<ClientEntity>,
  ) {}

  async findAll(): Promise<Client[]> {
    let findings: CodeDescr[] = [];
    let clientList: ClientEntity[] = [];

    const queryBuilder = this.clientRepository
      .createQueryBuilder('FOREST_CLIENT')
      .where("FOREST_CLIENT.CLIENT_TYPE_CODE IN ('C', 'A')")
      .andWhere("FOREST_CLIENT.CLIENT_STATUS_CODE = 'ACT'")
      .andWhere("FOREST_CLIENT.CORP_REGN_NMBR IS NOT NULL")
      .orderBy('FOREST_CLIENT.CLIENT_NUMBER', 'ASC');

    //clientList = await queryBuilder.getMany();
    const itemCount = await queryBuilder.getCount();
    console.log('ItemCount: ' + itemCount);

    const take = 10;
    const numberOfPages = Math.ceil(itemCount/take);
    const timer = (ms: number) => new Promise(res => setTimeout(res, ms))
    
    for (let page = 1; page <= numberOfPages; page++) {
      const skip = (page - 1) * take;
      console.log('skip: ' + skip);
      console.log('Page # ' + page);

      clientList = await queryBuilder.skip(skip).take(take).getMany();

      clientList.forEach(
        (client) => {
          let registryCompanyTypeCode = client.registryCompanyTypeCode;
          let clientId = !this.isEmptyOrHasSpaces(registryCompanyTypeCode) ? registryCompanyTypeCode : '';
          clientId = clientId + client.corpRegnNmbr;
          console.log(clientId);

          axios
              .get(
                'http://orgbook.gov.bc.ca/api/v4/search/topic?ordering=-score&q=' + clientId + '&inactive=any&latest=true&revoked=false'
                //'https://orgbook.gov.bc.ca/api/v4/search/topic?q=' + clientId
              )
              .then((response) => {
                let results = response.data.results;
                let finding = new CodeDescr();
                finding.clientId = clientId;
                finding.nameInClient = client.clientName;
                finding.updateTimestamp = client.updateTimestamp;
                
                if (results.length > 0) {
                  finding.code = 'PF';
                  finding.active = !response.data.results[0].inactive ? 'Yes' : 'No';

                  if (response.data.results[0].names.length > 0) {
                    finding.nameInOrgBook = response.data.results[0].names[0].text;

                    if (finding.nameInClient === finding.nameInOrgBook) {
                      finding.code = 'F';
                    }
                  }

                  findings.push(finding);
                } 
                else {
                  axios
                  .get(
                    'https://orgbook.gov.bc.ca/api/v3/search/autocomplete?q=' + encodeURI(client.clientName)
                  )
                  .then((response) => {
                    let results = response.data.results;
                    if (results.length > 0) {
                      let clientName = response.data.results[0].value;
                      finding.nameInOrgBook = clientName;
                      finding.active = !response.data.results[0].inactive ? 'Yes' : 'No';
                      finding.code = 'PF';
                    }
                    else {
                      finding.code = 'NF';
                    }
                    findings.push(finding);
                  });
                }
              })
              .catch((err) => {
                console.error(err);
              });

        }
      );

      await timer(500);

    }

    setTimeout(() => {
      console.log('========== SAMPLE WITH ' + findings.length + " CLIENTS ==========");
      let foundClients = findings.filter(p => p.code === 'F').length;
      let notFoundClients = findings.filter(p => p.code === 'NF').length;

      console.log('Total of Clients Found = ' + foundClients + ' (' + ((foundClients / findings.length) * 100).toFixed(2) + '%)');
      console.log('Total of Clients Not Found = ' + notFoundClients + ' (' + ((notFoundClients / findings.length) * 100).toFixed(2) + '%)');
      //console.log(JSON.stringify(findings));

      const fs = require("fs"); 
      const out = JSON.stringify(findings); 
      const myConsole = new console.Console(fs.createWriteStream("./findings.json")); 
      myConsole.log(out);
    }, 2000);

    return queryBuilder.take(1).getMany();
  }

  isEmptyOrHasSpaces(str) {
    return str === null || str.trim() === '';
  }
}
