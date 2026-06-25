package ca.bc.gov.api.oracle.legacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** Raised when a search request does not include any supported search parameter. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSearchParameterFound extends ResponseStatusException {

  /** Creates the exception with the default message. */
  public NoSearchParameterFound() {
    super(HttpStatus.BAD_REQUEST, "No search parameter found");
  }

  /**
   * Creates the exception for a specific missing parameter.
   *
   * @param param the missing parameter name
   */
  public NoSearchParameterFound(String param) {
    super(HttpStatus.BAD_REQUEST, "No " + param + " parameter found");
  }
}
