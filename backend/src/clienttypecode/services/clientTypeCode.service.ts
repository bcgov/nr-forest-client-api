import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ClientTypeCodeEntity } from '../entities/clientTypeCode.entity';
import { CodeDesc } from '../../core/codeDesc.interface';

@Injectable()
export class ClientTypeCodeService {
 
  constructor(
    @InjectRepository(ClientTypeCodeEntity, 'postgresdb')
    private clientTypeCodeRepository: Repository<ClientTypeCodeEntity>,
) { }

  findAllActive(): Promise<CodeDesc[]> {
    return this.clientTypeCodeRepository
      .createQueryBuilder()
      .select("n")
      .from(ClientTypeCodeEntity, "n")
      .where("(n.expiry_date is null or n.expiry_date > current_date) " +
             "and n.effective_date <= current_date")
      .orderBy("n.description")
      .getMany();
  }

}
