package ca.bc.gov.api.oracle.legacy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidClientNumberException extends ResponseStatusException {
  public InvalidClientNumberException() {
    super(HttpStatus.BAD_REQUEST, "Invalid Client Number");
  }
}
