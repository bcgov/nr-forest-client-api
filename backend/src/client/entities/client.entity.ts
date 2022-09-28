
import { ClientStatusCodeEntity } from 'src/clientstatuscode/entities/clientStatusCode.entity';
import { ClientTypeCodeEntity } from 'src/clienttypecode/entities/clientTypeCode.entity';
import { BaseEntity, Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';


@Entity({ name: 'client' })
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

  //@ManyToOne(() => ClientStatusCodeEntity, (entity) => entity.code)
  @ManyToOne(type => ClientStatusCodeEntity)
  @JoinColumn({ referencedColumnName: "code" })
  clientStatusCode: ClientStatusCodeEntity;

  @ManyToOne(() => ClientTypeCodeEntity, (entity) => entity.code)
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

