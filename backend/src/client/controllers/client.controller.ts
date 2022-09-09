import { Controller, Get, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ApiQuery } from '@nestjs/swagger';
import { PageOptionsDto } from '../../pagination/dtos/page-option.dto';
import { ClientService } from '../services/client.service';

@ApiTags('Client')
@Controller('client')
export class ClientController {
  constructor(
    private readonly clientService: ClientService,
  ) {}

  @Get('/findAll')
  findAll() {
    return this.clientService.findAll();
  }
  
}
