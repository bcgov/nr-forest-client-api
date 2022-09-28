import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import axios from 'axios';
import { Repository } from 'typeorm';
import { ForestClientEntity } from '../entities/forestClient.entity';
import { ForestClient } from '../entities/forestClient.interface';

@Injectable()
export class ForestClientService {
  constructor(
    @InjectRepository(ForestClientEntity)
    private forestClientRepository: Repository<ForestClientEntity>,
  ) {}

  async migrateFromOracleToPostgress(): Promise<ForestClient[]> {
    const startDatetime = new Date();
        
    for (var i = 1989; i <= 2022; i++) {
      console.log("Running year " + i + "...");

      const queryBuilder = this.forestClientRepository
        .createQueryBuilder('FOREST_CLIENT')
        .where('EXTRACT(YEAR FROM FOREST_CLIENT.ADD_TIMESTAMP) = :year', {
          year: i,
        })
        .orderBy('FOREST_CLIENT.CLIENT_NUMBER', 'ASC');

      const itemCount = await queryBuilder.getCount();
      const take = 10;
      const numberOfPages = Math.ceil(itemCount / take);

      for (let page = 1; page <= numberOfPages; page++) {
        const skip = (page - 1) * take;

        let clientList: ForestClientEntity[] = [];
        clientList = await queryBuilder.skip(skip).take(take).getMany();

        clientList.forEach((client) => {
          console.log('Adding client with number ' + client.clientNumber + "...");
          axios
            .post('http://localhost:3000/client/postClient', {
              client: client,
            })
            .catch((err) => console.log('Error: ' + err));
        });
      }
    
    }

    console.log("Migration initialized on " + startDatetime);
    console.log("Migration finalized on " + new Date());

    return null;
  }

  isEmptyOrHasSpaces(str) {
    return str === null || str.trim() === '';
  }
}
