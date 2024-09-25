package ca.bc.gov.api.oracle.legacy.configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfiguration {

  @Value("${info.app.version}")
  private String appVersion;

  @Value("${info.app.name}")
  private String appName;

  @Value("${info.app.zone}")
  private String appZone;

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }

  @Bean
  public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config()
        .commonTags(
            "version", appVersion,
            "app", appName,
            "zone",appZone
        )
        .meterFilter(ignoreTag())
        .meterFilter(distribution());
  }

  @Bean
  public MeterRegistryCustomizer<PrometheusMeterRegistry> prometheusConfiguration() {
    return MeterRegistry::config;
  }

  public MeterFilter ignoreTag() {
    return MeterFilter.ignoreTags("type");
  }

  public MeterFilter distribution() {
    return new MeterFilter() {

      @Override
      public DistributionStatisticConfig configure(Meter.Id id,
          DistributionStatisticConfig config) {
        return DistributionStatisticConfig
            .builder()
            .percentiles(0.5, 0.95, 0.99)
            .percentilesHistogram(true)
            .build()
            .merge(config);
      }
    };
  }

}
