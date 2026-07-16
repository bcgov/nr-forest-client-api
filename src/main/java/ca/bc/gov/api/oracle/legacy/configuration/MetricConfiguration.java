package ca.bc.gov.api.oracle.legacy.configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.micrometer.metrics.autoconfigure.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sets up custom metrics and configuration for the application's meter registry.
 *
 * <p>It applies common tags to all metrics, configures percentile distribution statistics,
 * and manages Prometheus meter registry configuration. This class also provides a
 * {@link TimedAspect} bean to support {@code @Timed} annotations for methods.
 */
@Configuration
public class MetricConfiguration {

  @Value("${info.app.version}")
  private String appVersion;

  @Value("${info.app.name}")
  private String appName;

  @Value("${info.app.zone}")
  private String appZone;

  /**
   * Creates a {@link TimedAspect} bean for timing method executions.
   *
   * @param registry the {@link MeterRegistry} to register the aspect with
   * @return a {@link TimedAspect} configured to record method execution times
   */
  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }

  /**
   * Adds common tags like version, app name, and zone to all registered metrics.
   *
   * @return a {@link MeterRegistryCustomizer} to configure common tags and filters for metrics
   */
  @Bean
  public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config()
        .commonTags(
            "version", appVersion,
            "app", appName,
            "zone", appZone)
        .meterFilter(ignoreTag())
        .meterFilter(distribution());
  }

  /**
   * Configures the meter registry for use with Prometheus metrics.
   *
   * @return a {@link MeterRegistryCustomizer} for meter registry configuration.
   */
  @Bean
  public MeterRegistryCustomizer<MeterRegistry> prometheusConfiguration() {
    return MeterRegistry::config;
  }

  /**
   * Creates a meter filter that ignores the {@code type} tag in all registered metrics.
   *
   * @return a {@link MeterFilter} that ignores the {@code type} tag
   */
  public MeterFilter ignoreTag() {
    return MeterFilter.ignoreTags("type");
  }

  /**
   * Configures a meter filter to track percentile histograms and percentiles.
   *
   * @return a {@link MeterFilter} that configures percentile statistics and histograms
   */
  public MeterFilter distribution() {
    return new MeterFilter() {

      @Override
      public DistributionStatisticConfig configure(
          Meter.Id id, DistributionStatisticConfig config) {
        return DistributionStatisticConfig.builder()
            .percentiles(0.5, 0.95, 0.99)
            .percentilesHistogram(true)
            .build()
            .merge(config);
      }
    };
  }
}
