package ca.bc.gov.api.oracle.legacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** Raised when a client number does not match the required format. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidClientNumberException extends ResponseStatusException {

  /** Creates the exception with the default validation message. */
  public InvalidClientNumberException() {
    super(HttpStatus.BAD_REQUEST, "Invalid Client Number");
  }
}
