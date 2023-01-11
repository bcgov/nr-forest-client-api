package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping(value = "/findByClientNumber/{clientNumber}")
  public ResponseEntity<ClientPublicViewDto> findByClientNumber(
      @PathVariable String clientNumber) {
    return ResponseEntity.ok(clientService.findByClientNumber(clientNumber));
  }

  @GetMapping("/findAllNonIndividuals")
  public ResponseEntity<List<ClientPublicViewDto>> findAllNonIndividuals(
      @RequestParam(defaultValue = "1") int currentPage,
      @RequestParam(defaultValue = "10") int itemsPerPage,
      @RequestParam(defaultValue = "clientName") String sortedColumnName) {

    return ResponseEntity.ok(
        clientService.findAllNonIndividualClients(currentPage, itemsPerPage, sortedColumnName));
  }

  @GetMapping(value = "/search")
  public ResponseEntity<Object> findByNames(
      @RequestParam(required = false)
      @Parameter(description = "The name of the entity or individual's last name")
      String clientName,

      @RequestParam(required = false)
      @Parameter(description = "The client's first name")
      String clientFirstName,

      @RequestParam(required = false)
      @Parameter(description = "The client's middle name")
      String clientMiddleName,

      @RequestParam(required = false)
      @Parameter(description =
          "A code indicating a type of ministry client.<br>" +
              "Examples include but are not limited to: Corporation, Individual, Association, First Nation Band...<br>" +
              "Please enter one or more client type codes")
      List<String> clientTypeCodes,

      @RequestParam(defaultValue = "1") int currentPage,
      @RequestParam(defaultValue = "10") int itemsPerPage) {

    return ResponseEntity.ok(
        clientService.searchByNames(
            clientName,
            clientFirstName,
            clientMiddleName,
            clientTypeCodes,
            currentPage,
            itemsPerPage));
  }
}
