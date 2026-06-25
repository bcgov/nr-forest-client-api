package ca.bc.gov.api.oracle.legacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** Raised when the requested client data cannot be found. */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends ResponseStatusException {

  /** Creates the exception with the default message. */
  public ClientNotFoundException() {
    super(HttpStatus.NOT_FOUND, "Client not found");
  }

  /**
   * Creates the exception with a custom message.
   *
   * @param message the error message
   */
  public ClientNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
