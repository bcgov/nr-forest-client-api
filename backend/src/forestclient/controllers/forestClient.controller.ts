import { Controller, Get, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ForestClientService } from '../services/forestClient.service';

@ApiTags('Forest Client')
@Controller('forest_client')
export class ForestClientController {
  constructor(
    private readonly forestClientService: ForestClientService,
  ) {}

  @Get('/migrateFromOracleToPostgress')
  migrationFromOracleToPostgress() {
    return this.forestClientService.migrateFromOracleToPostgress();
  }
  
}
