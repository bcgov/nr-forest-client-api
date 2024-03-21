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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class TracingConfiguration {

  @Bean
  ObservedAspect observedAspect(
      ObservationRegistry observationRegistry
  ) {
    ObservationThreadLocalAccessor.getInstance().setObservationRegistry(observationRegistry);
    return new ObservedAspect(observationRegistry);
  }

  @Bean
  SpanAspect spanAspect(MethodInvocationProcessor methodInvocationProcessor) {
    return new SpanAspect(methodInvocationProcessor);
  }

  @Bean
  MethodInvocationProcessor methodInvocationProcessor(NewSpanParser newSpanParser, Tracer tracer,
      BeanFactory beanFactory) {
    return new ImperativeMethodInvocationProcessor(newSpanParser, tracer, beanFactory::getBean,
        beanFactory::getBean);
  }

  @Bean
  NewSpanParser newSpanParser() {
    return new DefaultNewSpanParser();
  }
}