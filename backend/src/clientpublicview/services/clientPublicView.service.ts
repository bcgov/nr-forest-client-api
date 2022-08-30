import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { PageOptionsDto } from '../../pagination/dtos/page-option.dto';
import { PageDto } from '../../pagination/dtos/page.dto';
import { PageMetaDto } from '../../pagination/dtos/page-meta.dto';
import { ClientPublicViewEntity } from '../entities/clientPublicView.entity';
import { ClientPublicView } from '../entities/clientPublicView.interface';

@Injectable()
export class ClientPublicViewService {
  constructor(
    @InjectRepository(ClientPublicViewEntity)
    private clientPublicViewRepository: Repository<ClientPublicViewEntity>,
  ) {}

  async findByNumber(
    clientNumber: string,
  ): Promise<ClientPublicView[] | HttpException> {
    if (!clientNumber || clientNumber == '')
      return new HttpException(
        'Client number is required',
        HttpStatus.BAD_REQUEST,
      );

    let sqlWhereStr = '1=1';
    sqlWhereStr = sqlWhereStr + ' AND CLIENT_NUMBER = :clientNumber';
    
    return this.clientPublicViewRepository
      .createQueryBuilder('V_CLIENT_PUBLIC')
      .where(sqlWhereStr, {
        clientNumber: clientNumber,
      })
      .getMany();
  }

  async findByNames(
    clientName: string,
    clientFirstName: string,
    clientMiddleName: string,
    clientTypeCodesAsCsv: string,
    pageOptionsDto: PageOptionsDto,
  ): Promise<PageDto<ClientPublicView> | HttpException> {
    if (!clientName && !clientFirstName && !clientMiddleName && !clientTypeCodesAsCsv)
      return new HttpException(
        'Must provide at least one paramater: clientName, clientFirstName, clientMiddleName, clientTypeCode',
        HttpStatus.BAD_REQUEST,
      );

    let sqlWhereStr = '1=1';
    if (clientName && clientName !== '') {
      sqlWhereStr = sqlWhereStr + ' AND LOWER(V_CLIENT_PUBLIC.CLIENT_NAME) LIKE LOWER(:clientName)';
    }

    if (clientFirstName && clientFirstName !== '') {
      sqlWhereStr = sqlWhereStr + ' AND LOWER(V_CLIENT_PUBLIC.LEGAL_FIRST_NAME) LIKE LOWER(:clientFirstName)';
    }

    if (clientMiddleName && clientMiddleName !== '') {
      sqlWhereStr = sqlWhereStr + ' AND LOWER(V_CLIENT_PUBLIC.LEGAL_MIDDLE_NAME) LIKE LOWER(:clientMiddleName)';
    }

    let clientTypeCodeAsList = [];
    if (clientTypeCodesAsCsv && clientTypeCodesAsCsv !== '') {
      sqlWhereStr = sqlWhereStr + ' AND CLIENT_TYPE_CODE IN (:...clientTypeCode)';
      clientTypeCodeAsList = clientTypeCodesAsCsv.toUpperCase().split(',');
    }

    const skip = (pageOptionsDto.page - 1) * pageOptionsDto.take;

    const queryBuilder = this.clientPublicViewRepository
      .createQueryBuilder('V_CLIENT_PUBLIC')
      .where(sqlWhereStr, {
        clientName: `%${clientName}%`,
        clientFirstName: `%${clientFirstName}%`,
        clientMiddleName: `%${clientMiddleName}%`,
        clientTypeCode: clientTypeCodeAsList
      });

    queryBuilder
      .orderBy('V_CLIENT_PUBLIC.CLIENT_NUMBER', pageOptionsDto.order)
      .skip(skip)
      .take(pageOptionsDto.take);

    const itemCount = await queryBuilder.getCount();
    const { entities } = await queryBuilder.getRawAndEntities();

    const pageMetaDto = new PageMetaDto({ itemCount, pageOptionsDto });

    return new PageDto(entities, pageMetaDto);
  }

  async findAllNonIndividuals(
    pageOptionsDto: PageOptionsDto,
  ): Promise<PageDto<ClientPublicView>> {
    const skip = (pageOptionsDto.page - 1) * pageOptionsDto.take;

    const queryBuilder = this.clientPublicViewRepository
      .createQueryBuilder('V_CLIENT_PUBLIC')
      .where('V_CLIENT_PUBLIC.CLIENT_TYPE_CODE != :clientTypeCode', {
        clientTypeCode: 'I',
      });

    queryBuilder
      .orderBy('V_CLIENT_PUBLIC.CLIENT_NUMBER', pageOptionsDto.order)
      .skip(skip)
      .take(pageOptionsDto.take);

    const itemCount = await queryBuilder.getCount();
    const { entities } = await queryBuilder.getRawAndEntities();

    const pageMetaDto = new PageMetaDto({ itemCount, pageOptionsDto });

    return new PageDto(entities, pageMetaDto);
  }
}
