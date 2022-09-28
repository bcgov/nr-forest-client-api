import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ClientTypeCodeController } from './controllers/clientTypeCode.controller';
import { ClientTypeCodeEntity } from './entities/clientTypeCode.entity';
import { ClientTypeCodeService } from './services/clientTypeCode.service';

@Module({
  imports: [TypeOrmModule.forFeature([ClientTypeCodeEntity], 'postgresdb')],
  controllers: [ClientTypeCodeController],
  providers: [ClientTypeCodeService]
})
export class ClientTypeCodeModule {}
