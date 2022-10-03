import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ClientController } from './controllers/client.controller';
import { ClientEntity } from './entities/client.entity';
import { ClientService } from './services/client.service';

@Module({
  imports: [TypeOrmModule.forFeature([ClientEntity], 'postgresdb')],
  controllers: [ClientController],
  providers: [ClientService],
})
export class ClientModule {}
