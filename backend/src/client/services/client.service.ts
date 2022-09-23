import { HttpException, HttpStatus, Injectable, Logger } from '@nestjs/common';
import { Cron } from '@nestjs/schedule';
import { InjectRepository } from '@nestjs/typeorm';
import axios from 'axios';
import { Client } from 'src/client/entities/client.interface';
import { Repository } from 'typeorm';
import { ClientEntity } from '../entities/client.entity';

@Injectable()
export class ClientService {
  private readonly logger = new Logger(ClientService.name);

  constructor(
    @InjectRepository(ClientEntity, 'postgresdb')
    private clientRepository: Repository<ClientEntity>,
  ) {}

  findAllClients(): Promise<Client[]> {
    return this.clientRepository
      .createQueryBuilder('client')
      .getMany();
  }

  findClientByClientNumber(clientNumberInOracle: string): Promise<Client> {
    return this.clientRepository
      .createQueryBuilder('client')
      .where('client_number_in_oracle = :clientNumberInOracle', {
        clientNumberInOracle: clientNumberInOracle,
      })
      .getOne();
  }

  //@Cron('*/15 * * * * *') //Runs every 15 seconds
  async migrateDataFromOracle() {
    console.log('test');
  }

  async postClient(activeClient: Client) {
    const client = new ClientEntity();
    client.clientStatusCode = activeClient.clientStatusCode;
    client.clientTypeCode = activeClient.clientTypeCode;

    this.clientRepository.save(client);

    /*const queryRunner = this.dataSource.createQueryRunner();

    await queryRunner.connect();
    await queryRunner.startTransaction();
    try {
      const client = new ClientEntity() ;
      client.clientNumber = activeClient.clientNumber;
      client.clientName = activeClient.clientName;
      client.clientStatusCode = activeClient.clientStatusCode;
      client.clientTypeCode = activeClient.clientTypeCode;
      client.legalFirstName = activeClient.legalFirstName;
      client.legalMiddleName = activeClient.legalMiddleName;

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
}
