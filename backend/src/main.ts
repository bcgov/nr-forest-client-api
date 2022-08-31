import { NestFactory } from '@nestjs/core';
import * as bodyParser from 'body-parser';
import 'dotenv/config';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.enableCors();
  app.use(bodyParser.json({ limit: '100mb' }));

  const config = new DocumentBuilder()
    .setTitle('DB example')
    .setDescription('The user API description')
    .setVersion('1.0')
    .build();

  const document = SwaggerModule.createDocument(app, config);

  SwaggerModule.setup('api', app, document);

  await app.listen(3000);
}
bootstrap();
