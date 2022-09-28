import { ClientStatusCodeEntity } from "src/clientstatuscode/entities/clientStatusCode.entity";

export interface Client {
  clientId: number;
  clientNumberInOracle?: string;
  incorporationNumber?: string;
  organizationName?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  clientStatusCode: ClientStatusCodeEntity;
  clientTypeCode: string;
  dateOfBirth?: Date;
  comment?: string;
  validInd?: string;
  createTimestamp: Date;
  updateTimestamp?: Date;
  createUser: string;
  updateUser?: string;
}
