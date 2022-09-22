import { Controller, Get } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ForestClientService } from '../services/forestClient.service';

@ApiTags('Forest Client')
@Controller('forest_client')
export class ForestClientController {
  constructor(
    private readonly forestClientService: ForestClientService,
  ) {}

  @Get('/findAll')
  findAll() {
    return this.forestClientService.findAll();
  }
  
}
