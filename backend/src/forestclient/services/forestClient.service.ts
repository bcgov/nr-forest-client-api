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
            .post(process.env.BACKEND_URL + '/client/postClient', {
              client: client,
            })
            .catch((err) => {
              console.log('Error: ' + err);
              return null;
            });
        });
      }
    
    }

    const endDatetime = new Date();

    const msInMinute = 60 * 1000;
    const timeInMin = Math.round(Math.abs(endDatetime.valueOf() - startDatetime.valueOf()) / msInMinute);

    console.log("Migration initialized on " + startDatetime);
    console.log("Migration finalized on " + endDatetime);
    console.log("Time in minutes: " + timeInMin);

    return null;
  }

  isEmptyOrHasSpaces(str) {
    return str === null || str.trim() === '';
  }
}
