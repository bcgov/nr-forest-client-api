import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { PageOptionsDto } from '../../pagination/dtos/page-option.dto';
import { PageDto } from '../../pagination/dtos/page.dto';
import { PageMetaDto } from '../../pagination/dtos/page-meta.dto';
import { ClientEntity } from '../entities/client.entity';
import { Client } from '../entities/client.interface';
import { Cron } from '@nestjs/schedule';

@Injectable()
export class ClientService {
  constructor(
    @InjectRepository(ClientEntity)
    private clientRepository: Repository<ClientEntity>,
  ) {}

  @Cron('*/15 * * * * *') //Runs every 15 seconds
  async findAll(): Promise<Client[]> {
    let clients: Client[];
    const queryBuilder =  this.clientRepository
      .createQueryBuilder('FOREST_CLIENT')
      .where("FOREST_CLIENT.CLIENT_TYPE_CODE in ('C', 'A')")
      .andWhere("FOREST_CLIENT.CLIENT_STATUS_CODE='ACT'");

    const itemCount = await queryBuilder.getCount();
      
    for (let i = 1; i <= 5; i++ ) {
      
      const take = 10;
      const skip = (i - 1) * take;
      
      queryBuilder.skip(skip).take(take).getMany()
      .then((res) => {
        console.log("Page #: " + i);
        if (res) {
          console.log(res);
        }
      });
      //clients.push(queryBuilder.skip(skip).take(take).getMany());
    }
    //console.log(itemCount);
    
    return queryBuilder.take(10).getMany();
    //return clients;
  }

}
