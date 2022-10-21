drop table if exists client;
drop table if exists client_status_code;
drop table if exists client_type_code;

-- 
-- TABLE: client
--

create table client (
    client_id               	serial			not null,
    legacy_client_id            varchar(50)    	null,
	incorporation_number		varchar(50)    	null,
    organization_name           varchar(100)    null,
    first_name                  varchar(100)    null,
	middle_name                 varchar(100)    null,
	last_name                 	varchar(100)    null,
    client_status_code          varchar(3)    	not null,
	client_type_code          	varchar(1)    	not null,
	date_of_birth				date 			null,
	client_comment				varchar(5000)	null,
    valid_ind                   varchar(1)    	null,
	create_timestamp  			timestamp      	default current_timestamp not null,
    update_timestamp  			timestamp      	default current_timestamp,
    create_user       			varchar(60)    	not null,
    update_user       			varchar(60)		null
);

comment on table client is 'Any individual who performs, will or intends to perform business with the Ministry to deal with the Ministry.';
comment on column client.client_id is 'A sequential id assigned to a client.';
comment on column client.legacy_client_id is 'An ID inherited from the legacy CLIENT system previously called Client Number.';
comment on column client.incorporation_number is 'The incorporation registration number of the Ministry Client. This number is a concatenation of the corporate registration number and the registry company type code of the legacy CLIENT system.';
comment on column client.organization_name is 'The name of the Ministry Client, either a Company or Individual.';
comment on column client.middle_name is 'The legal first name of the Ministry Client.';
comment on column client.first_name is 'The legal middle name of the Ministry Client.';
comment on column client.last_name is 'The legal last name of the Ministry Client.';
comment on column client.client_status_code is 'A code indicating the status of a ministry client. Examples include but are not limited to: Active, Deactivated, and Deceased, among others.';
comment on column client.client_type_code is 'A code indicating a type of ministry client. Examples include but are not limited to: Corporation, Individual, and Association, among others.';
comment on column client.date_of_birth is 'The date of birth of the Ministry Client if this is an individual.';
comment on column client.client_comment is 'Text for a comment on the client.';
comment on column client.valid_ind is 'An indicator that determines if a client is valid based on the status and name in BC Registries and Online Services.';
comment on column client.create_timestamp is 'This is the date and time that the client record was created.';
comment on column client.update_timestamp is 'This is the date and time that the client record was updated.';
comment on column client.create_user is 'The user or proxy account that created the record.';
comment on column client.update_user is 'The user or proxy account that created or last updated the record.';

-- 
-- TABLE: client_status_code
--

create table client_status_code (
    client_status_code          varchar(3)      not null,
    description                 varchar(100)    not null,
    effective_date              date            not null,
    expiry_date                 date            default to_date('99991231','YYYYMMDD') not null,
    create_timestamp            timestamp       default current_timestamp not null,
    update_timestamp            timestamp       default current_timestamp,
    create_user                 varchar(60)     not null,
    update_user                 varchar(60)
);

comment on table client_status_code is 'The status of a ministry client.';
comment on column client_status_code.client_status_code is 'A code indicating the status of a ministry client. Examples include but are not limited to: Active, Deactivated, and Deceased, among others.';
comment on column client_status_code.description is 'The display quality description of the code value.';
comment on column client_status_code.effective_date is 'The date that the code value has become or is expected to become effective. Default is the data that the code value is created.';
comment on column client_status_code.expiry_date is 'The date on which the code value has expired or is expected to expire. Default 9999-12-31';
comment on column client_status_code.create_timestamp is 'The date and time the record was created.';
comment on column client_status_code.update_timestamp is 'The date and time the record was created or last updated.';
comment on column client_status_code.create_user is 'The user or proxy account that created the record.';
comment on column client_status_code.update_user is 'The user or proxy account that created or last updated the record.';

-- 
-- TABLE: client_type_code
--

create table client_type_code (
    client_type_code            varchar(1)      not null,
    description                 varchar(100)    not null,
    effective_date              date            not null,
    expiry_date                 date            default to_date('99991231','YYYYMMDD') not null,
    create_timestamp            timestamp       default current_timestamp not null,
    update_timestamp            timestamp       default current_timestamp,
    create_user                 varchar(60)     not null,
    update_user                 varchar(60)
);

comment on table client_type_code is 'The status of a ministry client.';
comment on column client_type_code.client_type_code is 'A code indicating a type of ministry client. Examples include but are not limited to: Corporation, Individual, and Association, among others.';
comment on column client_type_code.description is 'The display quality description of the code value.';
comment on column client_type_code.effective_date is 'The date that the code value has become or is expected to become effective. Default is the data that the code value is created.';
comment on column client_type_code.expiry_date is 'The date on which the code value has expired or is expected to expire. Default 9999-12-31';
comment on column client_type_code.create_timestamp is 'The date and time the record was created.';
comment on column client_type_code.update_timestamp is 'The date and time the record was created or last updated.';
comment on column client_type_code.create_user is 'The user or proxy account that created the record.';
comment on column client_type_code.update_user is 'The user or proxy account that created or last updated the record.';

-- 
-- CONSTRAINTS
--

alter table client 
add constraint client_id_pk 
primary key (client_id);

alter table client 
add constraint legacy_client_id_sk 
unique (legacy_client_id);

alter table client_type_code 
add constraint client_type_code_pk 
primary key (client_type_code);

alter table client_status_code 
add constraint client_status_code_pk 
primary key (client_status_code);

alter table client 
add constraint client_client_status_code_fk
foreign key (client_status_code)
references client_status_code(client_status_code);

alter table client 
add constraint client_client_type_code_fk
foreign key (client_type_code)
references client_type_code(client_type_code);