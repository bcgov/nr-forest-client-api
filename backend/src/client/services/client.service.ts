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
    //let clientList: ClientEntity[] = [];

    const queryBuilder = this.clientRepository
      .createQueryBuilder('FOREST_CLIENT')
      .where("FOREST_CLIENT.CLIENT_TYPE_CODE IN ('C', 'A')")
      .andWhere("FOREST_CLIENT.CLIENT_STATUS_CODE = 'ACT'")
      .andWhere("FOREST_CLIENT.CORP_REGN_NMBR IS NOT NULL")
      .orderBy('FOREST_CLIENT.CLIENT_NUMBER', 'ASC');

    const itemCount = await queryBuilder.getCount();
    //console.log('ItemCount: ' + itemCount);

    const take = 10;
    const numberOfPages = Math.ceil(itemCount/take);

    //for (let page = 1; page <= numberOfPages; page++) {
    for (let page = 1; page <= 10; page++) {
      const skip = (page - 1) * take;

      queryBuilder
        .skip(skip)
        .take(take)
        .getMany()
        .then((clients) => {
          
          //console.log('Page # ' + page);

          for (let i = 0; i < clients.length; i++) {
            let registryCompanyTypeCode = clients[i].registryCompanyTypeCode;
            let clientId = !this.isEmptyOrHasSpaces(registryCompanyTypeCode) ? registryCompanyTypeCode : '';
            clientId = clientId + clients[i].corpRegnNmbr;
            //console.log(clientId);

            //let test = axios
            axios
              .get(
                'https://orgbook.gov.bc.ca/api/v4/search/topic?q=' + clientId,
              )
              .then((response) => {
                let results = response.data.results;
                let finding = new CodeDescr();

                if (results.length > 0) {
                  finding.code = 'F';
                  finding.text = 'Client with ID ' + clientId + ' found';
                  findings.push(finding);
                } 
                else {
                  finding.code = 'NF';
                  finding.text = 'Client with ID ' + clientId + ' not found';
                  findings.push(finding);
                }
                //return findings;
              })
              .catch((err) => {
                console.error(err);
              });
  
            //console.log(test);
          }
        });
    }

    setTimeout(() => {
      console.log('========== SAMPLE WITH ' + findings.length + " CLIENTS ==========");
      let foundClients = findings.filter(p => p.code === 'F').length;
      let notFoundClients = findings.filter(p => p.code === 'NF').length;

      console.log('Total of Clients Found = ' + foundClients + ' (' + ((foundClients / findings.length) * 100).toFixed(2) + '%)');
      console.log('Total of Clients Not Found = ' + notFoundClients + ' (' + ((notFoundClients / findings.length) * 100).toFixed(2) + '%)');
      console.log(findings);
    }, 2000);

    return queryBuilder.take(10).getMany();
  }

  isEmptyOrHasSpaces(str) {
    return str === null || str.trim() === '';
  }
}
