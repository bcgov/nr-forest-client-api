import { Controller, Get, Query } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { ApiQuery } from '@nestjs/swagger';
import { PageOptionsDto } from '../../pagination/dtos/page-option.dto';
import { ClientPublicViewService } from '../services/clientPublicView.service';

@ApiTags('Client View')
@Controller('client')
export class ClientPublicViewController {
  constructor(
    private readonly clientPublicViewService: ClientPublicViewService,
  ) {}

  @Get('/findByNumber')
  @ApiQuery({
    name: 'clientNumber',
    required: true,
    type: String,
    description: 'The number of the client'
  })
  findByNumber(
    @Query('clientNumber') clientNumber: string,
  ) {
    return this.clientPublicViewService.findByNumber(
      clientNumber
    );
  }

  @Get('/findByName')
  @ApiQuery({
    name: 'clientName',
    required: false,
    type: String,
    description: 'The name of the entity'
  })
  @ApiQuery({
    name: 'clientFirstName',
    required: false,
    type: String,
    description: "The client's first name"
  })
  @ApiQuery({
    name: 'clientMiddleName',
    required: false,
    type: String,
    description: "The client's middle name"
  })
  @ApiQuery({
    name: 'clientTypeCodesAsCsv',
    required: false,
    type: String,
    description: 'A code indicating a type of ministry client.<br>' +
                 'Examples include but are not limited to: Corporation, Individual, Association, First Nation Band...<br>' + 
                 'Please enter one or more client type codes as CSV, i.e. C,A,B.'
  })
  findByName(
    @Query('clientName') clientName: string,
    @Query('clientFirstName') clientFirstName: string,
    @Query('clientMiddleName') clientMiddleName: string,
    @Query('clientTypeCodesAsCsv') clientTypeCodesAsCsv: string,
    @Query() pageOptionsDto: PageOptionsDto,
  ) {
    return this.clientPublicViewService.findByNames(
      clientName,
      clientFirstName,
      clientMiddleName,
      clientTypeCodesAsCsv,
      pageOptionsDto,
    );
  }

  @Get('/findAllNonIndividuals')
  findAllNonIndividuals(@Query() pageOptionsDto: PageOptionsDto) {
    return this.clientPublicViewService.findAllNonIndividuals(pageOptionsDto);
  }
}
