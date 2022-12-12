package ca.bc.gov.api.core.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket applicationApi() {
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("ca.bc.gov.api")).paths(regex("/api/m/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("FSA Forest Client Rest APIs")
				.description("APIs that allows systems to consume forest client data.").version("1.0").build();
	}

}