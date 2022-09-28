import { BaseEntity, Column, Entity, PrimaryColumn } from 'typeorm';

@Entity({ name: 'client_status_code' })
export class ClientStatusCodeEntity extends BaseEntity {

  @PrimaryColumn({ name: 'client_status_code' })
  code: string;

  @Column({ name: 'description' })
  description: string;

  @Column({ name: 'effective_date' })
  effectiveDate: Date;

  @Column({ name: 'expiry_date' })
  expiryDate: Date;
  
}
