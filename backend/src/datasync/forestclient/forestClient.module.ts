import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import {
  ForestClientEntity,
  ClientEntity,
} from './entities/forestClient.entity';
import { ForestClientService } from './services/forestClient.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([ForestClientEntity], 'oracledb'),
    TypeOrmModule.forFeature([ClientEntity], 'postgresdb'),
  ],
  providers: [ForestClientService],
})
export class ForestClientModule {}
