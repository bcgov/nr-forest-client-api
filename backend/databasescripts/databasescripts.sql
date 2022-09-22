drop table if exists client;
drop table if exists client_status_code;
drop table if exists client_type_code;

-- 
-- TABLE: client
--

create table client (
    client_id               	serial			not null,
    client_number_in_oracle     varchar(10)    	null,
	incorporation_number		varchar(10)    	null,
    organization_name           varchar(100)    null,
    first_name                  varchar(100)    null,
	middle_name                 varchar(100)    null,
	last_name                 	varchar(100)    null,
    client_status_code          varchar(10)    	not null,
	client_type_code          	varchar(10)    	not null,
	date_of_birth				date 			null,
	comment						varchar(4000)	null,
	create_timestamp  			timestamp      	default current_timestamp not null,
    update_timestamp  			timestamp      	default current_timestamp,
    create_user       			varchar(60)    	not null,
    update_user       			varchar(60)		null
)
;

-- 
-- TABLE: client_status_code
--

create table client_status_code (
    client_status_code          varchar(5)      not null,
    description                 varchar(10)    	null,
    effective_date              date            not null,
    expiry_date                 date            default to_date('99991231','YYYYMMDD') not null,
    create_timestamp            timestamp       default current_timestamp not null,
    update_timestamp            timestamp       default current_timestamp,
    create_user                 varchar(60)     not null,
    update_user                 varchar(60)
)
;

-- 
-- TABLE: client_type_code
--

create table client_type_code (
    client_type_code            varchar(5)      not null,
    description                 varchar(10)    	null,
    effective_date              date            not null,
    expiry_date                 date            default to_date('99991231','YYYYMMDD') not null,
    create_timestamp            timestamp       default current_timestamp not null,
    update_timestamp            timestamp       default current_timestamp,
    create_user                 varchar(60)     not null,
    update_user                 varchar(60)
)
;

-- 
-- CONSTRAINTS
--

alter table client add 
    constraint client_id_pk primary key (client_id)
;

alter table client_type_code add 
    constraint client_type_code_pk primary key (client_type_code)
;

alter table client_status_code add 
    constraint client_status_code_pk primary key (client_status_code)
;

alter table client add constraint client_client_status_code
    foreign key (client_status_code)
    references client_status_code(client_status_code)
;

alter table client add constraint client_client_type_code
    foreign key (client_type_code)
    references client_type_code(client_type_code)
;
