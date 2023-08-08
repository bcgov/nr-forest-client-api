package ca.bc.gov.api.oracle.legacy.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenApiCustomizer customizer() {
    return (OpenAPI openApi) ->
        openApi
            .getPaths()
            .values()
            .forEach(pathItem ->
                pathItem
                    .readOperations()
                    .forEach(operation ->
                        operation
                            .getParameters()
                            .add(new io.swagger.v3.oas.models.parameters.HeaderParameter()
                                .name("X-API-KEY")
                                .description("API Key used for authentication")
                                .required(true)
                                .schema(new io.swagger.v3.oas.models.media.StringSchema())
                            )

                    )
            );
  }
}
