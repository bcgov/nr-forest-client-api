import { NestFactory } from '@nestjs/core';
import * as bodyParser from 'body-parser';
import 'dotenv/config';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // enable cors only for our frontend/backend address
  // const whitelist = [process.env.FRONTEND_URL, process.env.BACKEND_URL];
  // app.enableCors({
  //   origin: (origin, callback) => {
  //     if (!origin || whitelist.indexOf(origin) !== -1) {
  //       callback(null, true);
  //     }
  //     // else {
  //     //   callback(new Error('Not allowed by CORS'));
  //     // }
  //   },
  // });

  app.enableCors();

  app.use(bodyParser.json({ limit: '100mb' }));

  const config = new DocumentBuilder()
    .setTitle('DB example')
    .setDescription('The user API description')
    .setVersion('1.0')
    .build();

  const document = SwaggerModule.createDocument(app, config);

  SwaggerModule.setup('api', app, document);

  // if (process.env.NODE_ENV && process.env.NODE_ENV === 'development') {
  //   SwaggerModule.setup('api', app, document);
  // } else {
  //   SwaggerModule.setup('api', app, document, {
  //     swaggerOptions: {
  //       supportedSubmitMethods: [],
  //     },
  //   });
  // }

  await app.listen(3000);
}
bootstrap();
