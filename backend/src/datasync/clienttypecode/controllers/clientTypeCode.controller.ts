import { Controller, Get } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ClientTypeCodeService } from '../services/clientTypeCode.service';

@ApiTags('Code Tables')
@Controller('clientTypeCode')
export class ClientTypeCodeController {
  constructor(private readonly clientTypeCodeService: ClientTypeCodeService) {}

  @Get('/findAllActive')
  findAllActive() {
      return this.clientTypeCodeService.findAllActive();
  }

}
