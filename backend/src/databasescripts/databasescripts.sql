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
    valid_ind                   varchar(10)    	null,
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
    description                 varchar(100)    	null,
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
    description                 varchar(100)    	null,
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

-- 
-- FIXED DATA
--

insert into client_status_code (client_status_code, description, effective_date, create_user) values ('ACT', 'Active', current_timestamp, 'mariamar');
insert into client_status_code (client_status_code, description, effective_date, create_user) values ('DAC', 'Deactivated', current_timestamp, 'mariamar');
insert into client_status_code (client_status_code, description, effective_date, create_user) values ('DEC', 'Deceased', current_timestamp, 'mariamar');
insert into client_status_code (client_status_code, description, effective_date, create_user) values ('REC', 'Receivership', current_timestamp, 'mariamar');
insert into client_status_code (client_status_code, description, effective_date, create_user) values ('SPN', 'Suspended', current_timestamp, 'mariamar');

insert into client_type_code (client_type_code, description, effective_date, create_user) values ('A', 'Association', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('B', 'First Nation Band', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('C', 'Corporation', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('F', 'Ministry of Forests and Range', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('G', 'Government', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('I', 'Individual', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('L', 'Limited Partnership', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('P', 'General Partnership', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('R', 'First Nation Group', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('S', 'Society', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('T', 'First Nation Tribal Council', current_timestamp, 'mariamar');
insert into client_type_code (client_type_code, description, effective_date, create_user) values ('U', 'Unregistered Company', current_timestamp, 'mariamar');