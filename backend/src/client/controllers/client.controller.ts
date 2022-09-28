import { Body, Controller, Get, Post, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ForestClient } from 'src/forestclient/entities/forestClient.interface';
import { Client } from '../entities/client.interface';
import { ClientService } from '../services/client.service';

@ApiTags('Client')
@Controller('client')
export class ClientController {
  constructor(private readonly clientService: ClientService) {}

  @Get('/findAll')
  findAll() {
    return this.clientService.findAll();
  }

  @Post('/postClient')
  async postClient(@Body() client: ForestClient) {
    return this.clientService.postClient(client);
  }

}
