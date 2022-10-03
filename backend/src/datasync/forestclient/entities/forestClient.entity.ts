import {
  BaseEntity,
  Column,
  Entity,
  PrimaryColumn,
  PrimaryGeneratedColumn,
} from 'typeorm';

@Entity({ name: 'FOREST_CLIENT' })
export class ForestClientEntity extends BaseEntity {
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
  birthdate: Date;

  @Column({ name: 'CLIENT_ID_TYPE_CODE' })
  clientIdTypeCode: string;

  @Column({ name: 'CLIENT_IDENTIFICATION' })
  clientIdentification: string;

  @Column({ name: 'REGISTRY_COMPANY_TYPE_CODE' })
  registryCompanyTypeCode: string;

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
  addTimestamp: Date;

  @Column({ name: 'ADD_USERID' })
  addUserid: string;

  @Column({ name: 'ADD_ORG_UNIT' })
  addOrgUnit: number;

  @Column({ name: 'UPDATE_TIMESTAMP' })
  updateTimestamp: Date;

  @Column({ name: 'UPDATE_USERID' })
  updateUserid: string;

  @Column({ name: 'UPDATE_ORG_UNIT' })
  updateOrgUnit: number;

  @Column({ name: 'REVISION_COUNT' })
  revisionCount: number;
}

@Entity({ name: 'CLIENT' })
export class ClientEntity extends BaseEntity {
  @PrimaryGeneratedColumn({ name: 'client_id' })
  clientId: number;

  @Column({ name: 'client_number_in_oracle' })
  clientNumberInOracle: string;

  @Column({ name: 'incorporation_number' })
  incorporationNumber: string;

  @Column({ name: 'organization_name' })
  organizationName: string;

  @Column({ name: 'first_name' })
  firstName: string;

  @Column({ name: 'middle_name' })
  middleName: string;

  @Column({ name: 'last_name' })
  lastName: string;

  @Column({ name: 'client_status_code' })
  clientStatusCode: string;

  @Column({ name: 'client_type_code' })
  clientTypeCode: string;

  @Column({ name: 'date_of_birth' })
  dateOfBirth: Date;

  @Column({ name: 'comment' })
  comment: string;

  @Column({ name: 'valid_ind' })
  validInd: string;

  @Column({ name: 'create_timestamp' })
  createTimestamp: Date;

  @Column({ name: 'update_timestamp' })
  updateTimestamp: Date;

  @Column({ name: 'create_user' })
  createUser: string;

  @Column({ name: 'update_user' })
  updateUser: string;
}
