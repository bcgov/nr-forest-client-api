import { Injectable, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Client } from 'src/client/entities/client.interface';
import { ForestClient } from 'src/forestclient/entities/forestClient.interface';
import { Repository } from 'typeorm';
import { ClientEntity } from '../entities/client.entity';

@Injectable()
export class ClientService {
  private readonly logger = new Logger(ClientService.name);

  constructor(
    @InjectRepository(ClientEntity, 'postgresdb')
    private clientRepository: Repository<ClientEntity>,
  ) {}

  findAll(): Promise<Client[]> {
    return this.clientRepository.createQueryBuilder('client').getMany();
  }

  //@Cron('*/15 * * * * *') //Runs every 15 seconds
  async postData(): Promise<any> {
    console.log('test');
    const client = new ClientEntity();
    client.organizationName = 'Mayis';
    this.clientRepository.save(client);
  }

  async postClient(clientFromOracleAsObj) {
    const forestClient: ForestClient = clientFromOracleAsObj.client;

    const registryCompanyTypeCode = forestClient.registryCompanyTypeCode ? forestClient.registryCompanyTypeCode : '';
    const corpRegnNmbr = forestClient.corpRegnNmbr ? forestClient.corpRegnNmbr : '';

    let incorporationNumber = '';
    incorporationNumber = incorporationNumber + !this.isEmptyOrHasSpaces(registryCompanyTypeCode) ? registryCompanyTypeCode : '';
    incorporationNumber = incorporationNumber + !this.isEmptyOrHasSpaces(corpRegnNmbr) ? corpRegnNmbr : '';
    incorporationNumber = !this.isEmptyOrHasSpaces(incorporationNumber) ? incorporationNumber : null;

    const client = new ClientEntity();
    client.clientNumberInOracle = forestClient.clientNumber;
    client.organizationName = forestClient.clientName;
    client.firstName = forestClient.legalFirstName;
    client.middleName = forestClient.legalMiddleName;
    client.incorporationNumber = incorporationNumber;
    client.clientStatusCode = forestClient.clientStatusCode;
    client.clientTypeCode = forestClient.clientTypeCode;
    client.createUser = 'mariamar';
    client.createTimestamp = new Date();

    //this.clientRepository.save(client);

    /*client.clientStatusCode = clientFromOracle.clientStatusCode;
    client.clientTypeCode = clientFromOracle.clientTypeCode;*/

    /*const queryRunner = this.dataSource.createQueryRunner();

    await queryRunner.connect();
    await queryRunner.startTransaction();
    try {

      

      await queryRunner.manager.save(client);
      await queryRunner.commitTransaction();
    }
    catch (err) {
      this.logger.error("\nFailed to write into db: \n" + err + "." +
                        "\nThe transaction has been rollbacked.");
      await queryRunner.rollbackTransaction();
    } 
    finally {
      await queryRunner.release();
    }*/
  }

  isEmptyOrHasSpaces(str) {
    return null === str || str.trim() === '';
  }
}
