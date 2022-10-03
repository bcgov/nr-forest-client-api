import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import {
  ForestClientEntity,
  ClientEntity,
} from './entities/forestClient.entity';
import { ForestClientService } from './services/forestClient.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([ForestClientEntity], 'oracle'),
    TypeOrmModule.forFeature([ClientEntity], 'postgres'),
  ],
  providers: [ForestClientService],
})
export class ForestClientModule {}
