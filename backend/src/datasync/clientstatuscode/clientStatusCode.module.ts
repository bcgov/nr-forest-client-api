import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ClientStatusCodeController } from './controllers/clientStatusCode.controller';
import { ClientStatusCodeEntity } from './entities/clientStatusCode.entity';
import { ClientStatusCodeService } from './services/clientStatusCode.service';

@Module({
  imports: [TypeOrmModule.forFeature([ClientStatusCodeEntity], 'postgresdb')],
  controllers: [ClientStatusCodeController],
  providers: [ClientStatusCodeService]
})
export class ClientStatusCodeModule {}
