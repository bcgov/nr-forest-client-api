import { Injectable, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import {
  ForestClientEntity,
  ClientEntity,
} from '../entities/forestClient.entity';

@Injectable()
export class ForestClientService {
  private readonly logger = new Logger(ForestClientService.name);

  constructor(
    @InjectRepository(ForestClientEntity, 'oracledb')
    private forestClientRepository: Repository<ForestClientEntity>,
    @InjectRepository(ClientEntity, 'postgresdb')
    private clientRepository: Repository<ClientEntity>,
  ) {}

  async insertClient(newClient: ForestClientEntity) {
    try {
      const client = this.clientAttributeMapping(newClient);
      this.clientRepository.save(client);
    } catch (e) {
      // todo: log error into db
      this.logger.error('Failed to insert new client into db: ');
      this.logger.error(e);
    }
  }

  async updateClient(updateClient: ForestClientEntity) {
    try {
      const client = this.clientAttributeMapping(updateClient);
      this.clientRepository.update(
        { clientNumberInOracle: client.clientNumberInOracle },
        client,
      );
    } catch (e) {
      this.logger.error('Failed to update new client in db: ');
      this.logger.error(e);
    }
  }

  // check daily update in oracle and sync in postgres
  async mergeFromForestClient() {
    try {
      const today = new Date();
      const newAdd = await this.forestClientRepository
        .createQueryBuilder('FOREST_CLIENT')
        .where('EXTRACT(YEAR FROM FOREST_CLIENT.ADD_TIMESTAMP) = :year', {
          year: today.getFullYear(),
        })
        .andWhere('EXTRACT(MONTH FROM FOREST_CLIENT.ADD_TIMESTAMP) = :month', {
          month: today.getMonth() + 1, // getMonth returns 0-11, need to add 1 to match in db
        })
        .andWhere('EXTRACT(DAY FROM FOREST_CLIENT.ADD_TIMESTAMP) = :day', {
          day: today.getDate(),
        })
        .getMany();

      const newUpdate = await this.forestClientRepository
        .createQueryBuilder('FOREST_CLIENT')
        .where('EXTRACT(YEAR FROM FOREST_CLIENT.UPDATE_TIMESTAMP) = :year', {
          year: today.getFullYear(),
        })
        .andWhere(
          'EXTRACT(MONTH FROM FOREST_CLIENT.UPDATE_TIMESTAMP) = :month',
          {
            month: today.getMonth() + 1,
          },
        )
        .andWhere('EXTRACT(DAY FROM FOREST_CLIENT.UPDATE_TIMESTAMP) = :day', {
          day: today.getDate(),
        })
        .getMany();

      newAdd.every((newClient) => {
        this.insertClient(newClient);
      });
      newUpdate.every((updateClient) => {
        this.updateClient(updateClient);
      });

      const endDatetime = new Date();

      const msInMinute = 60 * 1000;
      const timeInMin = Math.round(
        Math.abs(endDatetime.valueOf() - today.valueOf()) / msInMinute,
      );

      console.log('Migration initialized on ' + today);
      console.log('Migration finalized on ' + endDatetime);
      console.log('Time in minutes: ' + timeInMin);
    } catch (error) {
      this.logger.error('Failed to sync data from oracle to postgres ');
    }
  }

  clientAttributeMapping(oracleClient: ForestClientEntity) {
    const registryCompanyTypeCode = oracleClient.registryCompanyTypeCode
      ? oracleClient.registryCompanyTypeCode.trim()
      : '';
    const corpRegnNmbr = oracleClient.corpRegnNmbr
      ? oracleClient.corpRegnNmbr.trim()
      : '';
    let incorporationNumber = registryCompanyTypeCode + corpRegnNmbr;
    incorporationNumber = !this.isEmptyOrHasSpaces(incorporationNumber)
      ? incorporationNumber
      : null;

    const postgresClient = new ClientEntity();
    postgresClient.clientNumberInOracle = oracleClient.clientNumber;
    postgresClient.organizationName = oracleClient.clientName;
    postgresClient.firstName = oracleClient.legalFirstName;
    postgresClient.middleName = oracleClient.legalMiddleName;
    postgresClient.incorporationNumber = incorporationNumber;
    postgresClient.clientStatusCode = oracleClient.clientStatusCode;
    postgresClient.clientTypeCode = oracleClient.clientTypeCode;
    postgresClient.dateOfBirth = oracleClient.birthdate;
    postgresClient.comment = oracleClient.clientComment;
    postgresClient.createTimestamp = oracleClient.addTimestamp;
    postgresClient.updateTimestamp = oracleClient.updateTimestamp;
    postgresClient.createUser = oracleClient.addUserid;
    postgresClient.updateUser = oracleClient.updateUserid;

    return postgresClient;
  }

  isEmptyOrHasSpaces(str) {
    return str === null || str.trim() === '';
  }
}
