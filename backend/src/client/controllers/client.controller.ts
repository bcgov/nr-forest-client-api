import { Controller, Get, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ClientService } from '../services/client.service';

@ApiTags('Client')
@Controller('client')
export class ClientController {
  constructor(private readonly clientService: ClientService) {}

  @Get('/findAllClients')
  findAllClients() {
    return this.clientService.findAllClients();
  }

  @Get('/findClientByClientNumber')
  findClientByClientNumber(@Query('clientNumber') clientNumber?: string) {
    return this.clientService.findClientByClientNumber(clientNumber);
  }

}
