import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
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
    const queryBuilder =  this.clientRepository
      .createQueryBuilder('FOREST_CLIENT')
      .where("FOREST_CLIENT.CLIENT_TYPE_CODE in ('C', 'A')")
      .andWhere("FOREST_CLIENT.CLIENT_STATUS_CODE='ACT'")
      .andWhere('FOREST_CLIENT.CORP_REGN_NMBR is not null')
      .andWhere('FOREST_CLIENT.REGISTRY_COMPANY_TYPE_CODE is not null');

    return queryBuilder.take(10).getMany();
  }

}
