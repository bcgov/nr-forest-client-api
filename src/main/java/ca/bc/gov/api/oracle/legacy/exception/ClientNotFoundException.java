package ca.bc.gov.api.oracle.legacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends ResponseStatusException {

  public ClientNotFoundException() {
    super(HttpStatus.NOT_FOUND, "Client not found");
  }

  public ClientNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
