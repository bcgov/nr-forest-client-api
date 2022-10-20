package ca.bc.gov.api;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
          .apis(RequestHandlerSelectors.basePackage("ca.bc.gov.api"))
          .paths(regex("/api/m/.*"))
          .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("FSA Forest Client Rest APIs")
				.description("APIs that allows systems to consume forest client data.").version("1.0").build();
    }
}