import {
  BaseEntity,
  Column,
  Entity,
  PrimaryColumn,
} from 'typeorm';

@Entity({ name: 'FOREST_CLIENT' })
export class ClientEntity extends BaseEntity {
  @PrimaryColumn({ name: 'CLIENT_NUMBER' })
  clientNumber: string;

  @Column({ name: 'CLIENT_NAME' })
  clientName: string;

  @Column({ name: 'LEGAL_FIRST_NAME' })
  legalFirstName: string;

  @Column({ name: 'LEGAL_MIDDLE_NAME' })
  legalMiddleName: string;

  @Column({ name: 'CLIENT_STATUS_CODE' })
  clientStatusCode: string;

  @Column({ name: 'CLIENT_TYPE_CODE' })
  clientTypeCode: string;

  @Column({ name: 'BIRTHDATE' })
  birthdate: string;

  @Column({ name: 'CLIENT_ID_TYPE_CODE' })
  clientIdTypeCode: string;

  @Column({ name: 'CLIENT_IDENTIFICATION' })
  clientIdentification: string;

  @Column({ name: 'REGISTRY_COMPANY_TYPE_CODE' })
  registryCompnayTypeCode: string;

  @Column({ name: 'CORP_REGN_NMBR' })
  corpRegnNmbr: string;

  @Column({ name: 'CLIENT_ACRONYM' })
  clientAcronym: string;

  @Column({ name: 'WCB_FIRM_NUMBER' })
  wcbFirmNumber: string;

  @Column({ name: 'OCG_SUPPLIER_NMBR' })
  ocgSupplierNmbr: string;

  @Column({ name: 'CLIENT_COMMENT' })
  clientComment: string;

  @Column({ name: 'ADD_TIMESTAMP' })
  addTimestamp: string;

  @Column({ name: 'ADD_USERID' })
  addUserid: string;

  @Column({ name: 'ADD_ORG_UNIT' })
  addOrgUnit: string;

  @Column({ name: 'UPDATE_TIMESTAMP' })
  updateTimestamp: string;

  @Column({ name: 'UPDATE_USERID' })
  updateUserid: string;

  @Column({ name: 'UPDATE_ORG_UNIT' })
  updateOrgUnit: string;

  @Column({ name: 'REVISION_COUNT' })
  revisionCount: string;
}
