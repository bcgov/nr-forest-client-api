CREATE TABLE "THE"."FOREST_CLIENT"(
    "CLIENT_NUMBER" VARCHAR2(8),
	"CLIENT_NAME" VARCHAR2(60),
	"LEGAL_FIRST_NAME" VARCHAR2(30),
	"LEGAL_MIDDLE_NAME" VARCHAR2(30),
	"CLIENT_STATUS_CODE" VARCHAR2(3),
	"CLIENT_TYPE_CODE" VARCHAR2(1),
	"BIRTHDATE" DATE,
	"CLIENT_ID_TYPE_CODE" VARCHAR2(4),
	"CLIENT_IDENTIFICATION" VARCHAR2(40),
	"REGISTRY_COMPANY_TYPE_CODE" VARCHAR2(4),
	"CORP_REGN_NMBR" VARCHAR2(9),
	"CLIENT_ACRONYM" VARCHAR2(8),
	"WCB_FIRM_NUMBER" VARCHAR2(6),
	"OCG_SUPPLIER_NMBR" VARCHAR2(10),
	"CLIENT_COMMENT" VARCHAR2(4000),
	"ADD_TIMESTAMP" DATE NOT NULL ENABLE,
	"ADD_USERID" VARCHAR2(30),
	"ADD_ORG_UNIT" NUMBER(10,0),
	"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,
	"UPDATE_USERID" VARCHAR2(30),
	"UPDATE_ORG_UNIT" NUMBER(10,0),
	"REVISION_COUNT" NUMBER(5,0)
   )
SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000001','BAXTER','JAMES',NULL,'ACT','I',TIMESTAMP'1959-05-18 00:00:00.0','BCDL',NULL,NULL,'00000001',NULL,NULL,NULL,'Il.',TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1999-02-16 10:40:05.0','JDOH',70,1) ;
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000002','FUNNY','THOMAS','Yansi','ACT','I',TIMESTAMP'1939-07-04 00:00:00.0','BCDL','Wull.',NULL,'00000002',NULL,NULL,NULL,'C v.',TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'2000-08-24 15:59:37.0','PLOUSY',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000003','BORIS AND BORIS INC.',NULL,NULL,'ACT','C',NULL,'BCDL',NULL,NULL,'00000003',NULL,NULL,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','CLIENT_REWRITE_CONV',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000004','SAMPLE INDIAN BAND COUNCIL',NULL,NULL,'ACT','B',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);

INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000007','bond','james','bond','ACT','I',TIMESTAMP'1939-07-04 00:00:00.0','BCDL','Wull.',NULL,'00000002',NULL,NULL,NULL,'C v.',TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'2000-08-24 15:59:37.0','PLOUSY',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000010','bbondd','jjamess','bbondd','ACT','A',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000008','hunt','james','hunt','ACT','I',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00000009','james',NULL,'hunt','ACT','I',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00001347','KRAKOWSKY','ERNA',NULL,'ACT','I',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00001351','CORP. OF THE CITY OF VICTORIA',NULL,NULL,'ACT','G',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00001356','BALFOUR FOREST PRODUCTS INC.',NULL,NULL,'ACT','U',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.FOREST_CLIENT (CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE,BIRTHDATE,CLIENT_ID_TYPE_CODE,CLIENT_IDENTIFICATION,REGISTRY_COMPANY_TYPE_CODE,CORP_REGN_NMBR,CLIENT_ACRONYM,WCB_FIRM_NUMBER,OCG_SUPPLIER_NMBR,CLIENT_COMMENT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,REVISION_COUNT) VALUES
('00001357','SLOCAN FOREST PRODUCTS LTD.',NULL,NULL,'ACT','C',NULL,NULL,NULL,'DINA','684','SAMPLIBC',236967,NULL,NULL,TIMESTAMP'1989-11-26 08:52:38.0','CONV',70,TIMESTAMP'1989-11-26 08:52:38.0','IDIR\ITISWATTLES',70,1);

CREATE TABLE "THE"."CLIENT_DOING_BUSINESS_AS"(
    "CLIENT_DBA_ID" NUMBER(12,0),
	"CLIENT_NUMBER" VARCHAR2(8),
	"DOING_BUSINESS_AS_NAME" VARCHAR2(120),
	"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,
	"UPDATE_USERID" VARCHAR2(30),
	"UPDATE_ORG_UNIT" NUMBER(10,0),
	"ADD_TIMESTAMP" DATE NOT NULL ENABLE,
	"ADD_USERID" VARCHAR2(30),
	"ADD_ORG_UNIT" NUMBER(10,0),
	"REVISION_COUNT" NUMBER(5,0)
)
SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

INSERT INTO THE.CLIENT_DOING_BUSINESS_AS (CLIENT_DBA_ID,CLIENT_NUMBER,DOING_BUSINESS_AS_NAME,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
(1,'00000001','BAXTER''S FAMILY',TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.CLIENT_DOING_BUSINESS_AS (CLIENT_DBA_ID,CLIENT_NUMBER,DOING_BUSINESS_AS_NAME,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
(2,'00000002','DOUG FUNNY',TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,1);
INSERT INTO THE.CLIENT_DOING_BUSINESS_AS (CLIENT_DBA_ID,CLIENT_NUMBER,DOING_BUSINESS_AS_NAME,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
(3,'00000003','BORIS AND BORIS INC.',TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,TIMESTAMP'2007-04-24 12:21:47.0','IDIR\ITISWATTLES',70,1);


CREATE TABLE "THE"."CLIENT_STATUS_CODE"(
    "CLIENT_STATUS_CODE" VARCHAR2(3),
	"DESCRIPTION" VARCHAR2(120),
	"EFFECTIVE_DATE" DATE NOT NULL ENABLE,
	"EXPIRY_DATE" DATE NOT NULL ENABLE,
	"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE
)
SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

INSERT INTO THE.CLIENT_STATUS_CODE (CLIENT_STATUS_CODE,DESCRIPTION,EFFECTIVE_DATE,EXPIRY_DATE,UPDATE_TIMESTAMP) VALUES
('DAC','Deactivated',TIMESTAMP'1905-01-01 00:00:00.0',TIMESTAMP'9999-12-31 00:00:00.0',TIMESTAMP'2007-03-14 14:42:21.0');
INSERT INTO THE.CLIENT_STATUS_CODE (CLIENT_STATUS_CODE,DESCRIPTION,EFFECTIVE_DATE,EXPIRY_DATE,UPDATE_TIMESTAMP) VALUES
('ACT','Active',TIMESTAMP'1905-01-01 00:00:00.0',TIMESTAMP'9999-12-31 00:00:00.0',TIMESTAMP'2007-03-14 14:42:21.0');
INSERT INTO THE.CLIENT_STATUS_CODE (CLIENT_STATUS_CODE,DESCRIPTION,EFFECTIVE_DATE,EXPIRY_DATE,UPDATE_TIMESTAMP) VALUES
('DEC','Deceased',TIMESTAMP'1905-01-01 00:00:00.0',TIMESTAMP'9999-12-31 00:00:00.0',TIMESTAMP'2007-03-14 14:42:21.0');
INSERT INTO THE.CLIENT_STATUS_CODE (CLIENT_STATUS_CODE,DESCRIPTION,EFFECTIVE_DATE,EXPIRY_DATE,UPDATE_TIMESTAMP) VALUES
('REC','Receivership',TIMESTAMP'1905-01-01 00:00:00.0',TIMESTAMP'9999-12-31 00:00:00.0',TIMESTAMP'2007-03-14 14:42:21.0');
INSERT INTO THE.CLIENT_STATUS_CODE (CLIENT_STATUS_CODE,DESCRIPTION,EFFECTIVE_DATE,EXPIRY_DATE,UPDATE_TIMESTAMP) VALUES
('SPN','Suspended',TIMESTAMP'1905-01-01 00:00:00.0',TIMESTAMP'9999-12-31 00:00:00.0',TIMESTAMP'2007-03-14 14:42:21.0');


CREATE TABLE "THE"."CLIENT_LOCATION"(
    "CLIENT_NUMBER" VARCHAR2(8),
	"CLIENT_LOCN_CODE" VARCHAR2(2),
	"CLIENT_LOCN_NAME" VARCHAR2(40),
	"HDBS_COMPANY_CODE" VARCHAR2(5),
	"ADDRESS_1" VARCHAR2(40),
	"ADDRESS_2" VARCHAR2(40),
	"ADDRESS_3" VARCHAR2(40),
	"CITY" VARCHAR2(30),
	"PROVINCE" VARCHAR2(50),
	"POSTAL_CODE" VARCHAR2(10),
	"COUNTRY" VARCHAR2(50),
	"BUSINESS_PHONE" VARCHAR2(10),
	"HOME_PHONE" VARCHAR2(10),
	"CELL_PHONE" VARCHAR2(10),
	"FAX_NUMBER" VARCHAR2(10),
	"EMAIL_ADDRESS" VARCHAR2(128),
	"LOCN_EXPIRED_IND" VARCHAR2(1),
	"RETURNED_MAIL_DATE" DATE,
	"TRUST_LOCATION_IND" VARCHAR2(1),
	"CLI_LOCN_COMMENT" VARCHAR2(4000),
	"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,
	"UPDATE_USERID" VARCHAR2(30),
	"UPDATE_ORG_UNIT" NUMBER(10,0),
	"ADD_TIMESTAMP" DATE NOT NULL ENABLE,
	"ADD_USERID" VARCHAR2(30),
	"ADD_ORG_UNIT" NUMBER(10,0),
	"REVISION_COUNT" NUMBER(5,0)
)
SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000001','00',NULL,'01382','2080 Labieux Rd',NULL,NULL,'NANAIMO','BC','V9T6J9','CANADA',NULL,'8006618773',NULL,NULL,NULL,'N',NULL,'N',NULL,TIMESTAMP'2002-03-22 15:52:03.0','JBAXTER',27,TIMESTAMP'1989-11-26 08:52:09.0','CONV',70,1);
INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000002','00',NULL,'01384','Government St','Floor 2',NULL,'VICTORIA','BC','V8V2L8','CANADA',NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',NULL,TIMESTAMP'1991-11-20 13:15:01.0','JBAXTER',64,TIMESTAMP'1989-11-26 08:52:09.0','CONV',70,1);
INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000003','00',NULL,'01385','1950 Douglas St',NULL,NULL,'VICTORIA','BC','V8W1Z2','CANADA',NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',NULL,TIMESTAMP'1999-05-04 09:30:11.0','JBAXTER',70,TIMESTAMP'1989-11-26 08:52:09.0','CONV',70,1);
INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000004','00',NULL,'26573','PO BOX 999',NULL,NULL,'VICTORIA','BC','V8W1M0','CANADA',NULL,'2502502550',NULL,'2502502550',NULL,'N',NULL,'N',NULL,TIMESTAMP'2002-03-05 10:01:05.0','JBAXTER',21,TIMESTAMP'1989-11-26 12:20:50.0','CONV',70,1);
INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000004','01','BAND OFFICE','D3475','INDIAN BAND','916-1150 MAINLAND ST',NULL,'VANCOUVER','BC','V6B2T4','CANADA','2505205200',NULL,NULL,'2505205200',NULL,'N',NULL,'N','THIS LOCATION IS ONLY FOR THE AGREEMENT SIGNAGE',TIMESTAMP'2006-04-03 15:01:44.0','itiswattles',70,TIMESTAMP'2001-01-25 14:08:42.0','ITISWATTLES',3,1);
INSERT INTO THE.CLIENT_LOCATION (CLIENT_NUMBER,CLIENT_LOCN_CODE,CLIENT_LOCN_NAME,HDBS_COMPANY_CODE,ADDRESS_1,ADDRESS_2,ADDRESS_3,CITY,PROVINCE,POSTAL_CODE,COUNTRY,BUSINESS_PHONE,HOME_PHONE,CELL_PHONE,FAX_NUMBER,EMAIL_ADDRESS,LOCN_EXPIRED_IND,RETURNED_MAIL_DATE,TRUST_LOCATION_IND,CLI_LOCN_COMMENT,UPDATE_TIMESTAMP,UPDATE_USERID,UPDATE_ORG_UNIT,ADD_TIMESTAMP,ADD_USERID,ADD_ORG_UNIT,REVISION_COUNT) VALUES
('00000004','02','TRUST ACCOUNT  -RSI','T1350','PO BOX 588',NULL,NULL,'CHASE','BC','V0E1M0','CANADA',NULL,'2502502550',NULL,'2502502550',NULL,'Y',NULL,'Y','TRUST ACCOUNT  - RSI',TIMESTAMP'2005-10-06 15:02:02.0','NEGELY',1834,TIMESTAMP'9999-12-31 00:00:00.0','CONVSNT',70,1);


CREATE OR REPLACE VIEW THE.V_CLIENT_PUBLIC
(CLIENT_NUMBER,CLIENT_NAME,LEGAL_FIRST_NAME,LEGAL_MIDDLE_NAME,CLIENT_STATUS_CODE,CLIENT_TYPE_CODE)
AS
SELECT client_number
       , client_name
       , legal_first_name
       , legal_middle_name
       , client_status_code
       , client_type_code
    FROM THE.forest_client;