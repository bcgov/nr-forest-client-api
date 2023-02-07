package ca.bc.gov.api.oracle.legacy.handlers;

import java.util.function.Consumer;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface BaseHandler {
  Mono<ServerResponse> handle(ServerRequest serverRequest);
  Consumer<Builder> documentation(String tag);

}
