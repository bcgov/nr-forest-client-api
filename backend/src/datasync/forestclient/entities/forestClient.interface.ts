export interface ForestClient {
  clientNumber: string;
  clientName: string;
  clientStatusCode: string;
  clientTypeCode: string;
  legalFirstName?: string;
  legalMiddleName?: string;
  registryCompanyTypeCode?: string;
  corpRegnNmbr?: string;
  clientComment?: string;
  birthdate?: Date;
  clientIdTypeCode?: string;
  clientIdentification?: string;
  clientAcronym?: string;
  wcbFirmNumber?: string;
  ocgSupplierNmbr?: string;
  addTimestamp: Date;
  addUserid: string;
  addOrgUnit?: number;
  updateTimestamp: Date;
  updateUserid: string;
  updateOrgUnit?: number;
  revisionCount?: number;
}

export interface Client {
  clientId: number;
  clientNumberInOracle: string;
  incorporationNumber?: string;
  organizationName?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  clientStatusCode: string;
  clientTypeCode: string;
  dateOfBirth?: Date;
  comment?: string;
  validInd?: string;
  createTimestamp: Date;
  updateTimestamp: Date;
  createUser: string;
  updateUser: string;
}
