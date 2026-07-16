package ca.bc.gov.api.oracle.legacy.configuration;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.DefaultNewSpanParser;
import io.micrometer.tracing.annotation.ImperativeMethodInvocationProcessor;
import io.micrometer.tracing.annotation.MethodInvocationProcessor;
import io.micrometer.tracing.annotation.NewSpanParser;
import io.micrometer.tracing.annotation.SpanAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Defines tracing and observation beans used by Micrometer instrumentation. */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class TracingConfiguration {

  /**
   * Creates the aspect that propagates observation context.
   *
   * @param observationRegistry the application observation registry
   * @return the configured observed aspect
   */
  @Bean
  ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
    ObservationThreadLocalAccessor.getInstance().setObservationRegistry(observationRegistry);
    return new ObservedAspect(observationRegistry);
  }

  /**
   * Creates the aspect that handles span annotations.
   *
   * @param methodInvocationProcessor the processor used to handle span annotations
   * @return the configured span aspect
   */
  @Bean
  @ConditionalOnProperty(name = "management.tracing.enabled", havingValue = "true", matchIfMissing = true)
  SpanAspect spanAspect(MethodInvocationProcessor methodInvocationProcessor) {
    return new SpanAspect(methodInvocationProcessor);
  }

  /**
   * Creates the processor responsible for invoking methods with span support.
   *
   * @param newSpanParser the parser that interprets span annotations
   * @param tracer the tracer used to create spans
   * @param beanFactory the Spring bean factory
   * @return the configured method invocation processor
   */
  @Bean
  @ConditionalOnProperty(name = "management.tracing.enabled", havingValue = "true", matchIfMissing = true)
  MethodInvocationProcessor methodInvocationProcessor(
      NewSpanParser newSpanParser,
      Tracer tracer,
      BeanFactory beanFactory) {
    return new ImperativeMethodInvocationProcessor(
        newSpanParser,
        tracer,
        beanFactory::getBean,
        beanFactory::getBean);
  }

  /**
   * Creates the parser used to interpret {@code @NewSpan} annotations.
   *
   * @return the default new span parser
   */
  @Bean
  @ConditionalOnProperty(name = "management.tracing.enabled", havingValue = "true", matchIfMissing = true)
  NewSpanParser newSpanParser() {
    return new DefaultNewSpanParser();
  }
}
