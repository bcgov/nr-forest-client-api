package ca.bc.gov.api.oracle.legacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSearchParameterFound extends ResponseStatusException {
  public NoSearchParameterFound() {
    super(HttpStatus.BAD_REQUEST, "No search parameter found");
  }

  public NoSearchParameterFound(String param) {
    super(HttpStatus.BAD_REQUEST, "No " + param + " parameter found");
  }
}
