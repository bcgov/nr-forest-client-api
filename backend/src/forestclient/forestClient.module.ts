import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ForestClientController } from './controllers/forestClient.controller';
import { ForestClientEntity } from './entities/forestClient.entity';
import { ForestClientService } from './services/forestClient.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([ForestClientEntity]),
  ],
  controllers: [ForestClientController],
  providers: [ForestClientService],
})
export class ForestClientModule {}
