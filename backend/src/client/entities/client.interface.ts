export interface Client {
  clientId: number;
  clientNumberInOracle: string;
  incorporationNumber: string;
  organizationName: string;
  firstName: string;
  middleName: string;
  lastName: string;
  clientStatusCode: string;
  clientTypeCode: string;
  dateOfBirth: Date;
  comment: string;
  validInd: string;
  createTimestamp: Date;
  updateTimestamp: Date;
  createUser: string;
  updateUser: string;
}
