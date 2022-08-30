import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ClientPublicViewModule } from './clientpublicview/clientPublicView.module';

@Module({
  imports: [
    ConfigModule.forRoot(),
    TypeOrmModule.forRoot({
      type: 'oracle',
      host: process.env.ORACLEDB_HOST || 'localhost',
      port: Number(process.env.ORACLEDB_PORT || 1521),
      serviceName: process.env.ORACLEDB_SERVICENAME,
      //NOTE: Uncomment this out when deploying to OS
      connectString: `(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCPS)(HOST=nrcdb03.bcgov)(PORT=1543))) (CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=FORTMP1.nrs.bcgov)))`,
      database: process.env.ORACLEDB_DATABASE || 'oracle',
      username: process.env.ORACLEDB_USER || 'oracle',
      password: process.env.ORACLEDB_PASSWORD,
      autoLoadEntities: true, // Auto load all entities registered by typeorm forFeature method.
      synchronize: false, // This changes the DB schema to match changes to entities, which we might not want.
      //logging: true
    }),
    ClientPublicViewModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
