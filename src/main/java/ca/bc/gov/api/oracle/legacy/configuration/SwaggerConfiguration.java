package ca.bc.gov.api.oracle.legacy.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Adds a shared API key header parameter to all generated OpenAPI operations. */
@Configuration
public class SwaggerConfiguration {

  /**
   * Adds the API key header parameter to every OpenAPI operation.
   *
   * @return an OpenAPI customizer that appends the shared header parameter
   */
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
                            .addParametersItem(new HeaderParameter()
                                .name("X-API-KEY")
                                .description("API Key used for authentication")
                                .required(true)
                                .schema(new StringSchema()))));
  }
}
