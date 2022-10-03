import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CodeDesc } from 'src/core/codeDesc.interface';
import { Repository } from 'typeorm';
import { ClientStatusCodeEntity } from '../entities/clientStatusCode.entity';

@Injectable()
export class ClientStatusCodeService {
  constructor(
    @InjectRepository(ClientStatusCodeEntity, 'postgresdb')
    private clientStatusCodeRepository: Repository<ClientStatusCodeEntity>,
  ) {}

  findAllActive(): Promise<CodeDesc[]> {
    return this.clientStatusCodeRepository
      .createQueryBuilder()
      .select('n')
      .from(ClientStatusCodeEntity, 'n')
      .where("(n.expiry_date is null or n.expiry_date > current_date) " +
             "and n.effective_date <= current_date")
      .orderBy("n.description")
      .getMany();
  }

}
