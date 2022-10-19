import { Controller, Get } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ClientStatusCodeService } from '../services/clientStatusCode.service';

@ApiTags('Code Tables')
@Controller('clientStatusCode')
export class ClientStatusCodeController {
  constructor(private readonly clientStatusCodeService: ClientStatusCodeService) {}

  @Get('/findAllActive')
  findAllActive() {
      return this.clientStatusCodeService.findAllActive();
  }

}
