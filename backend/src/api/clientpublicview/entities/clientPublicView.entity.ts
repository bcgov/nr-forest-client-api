
import { ApiProperty } from '@nestjs/swagger';
import { BaseEntity, ViewColumn, ViewEntity, PrimaryColumn } from 'typeorm';


@ViewEntity({ name: 'V_CLIENT_PUBLIC' })
export class ClientPublicViewEntity extends BaseEntity {

  @PrimaryColumn({ name: 'CLIENT_NUMBER' })
  clientNumber: string;

  @ViewColumn({ name: 'CLIENT_NAME' })
  clientName: string;

  @ViewColumn({ name: 'LEGAL_FIRST_NAME' })
  legalFirstName: string;

  @ViewColumn({ name: 'LEGAL_MIDDLE_NAME' })
  legalMiddleName: string;

  @ViewColumn({ name: 'CLIENT_STATUS_CODE' })
  clientStatusCode: string;

  @ViewColumn({ name: 'CLIENT_TYPE_CODE' })
  clientTypeCode: string;

}

