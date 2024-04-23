package ca.bc.gov.api.oracle.legacy.service;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.query.Criteria;
import reactor.test.StepVerifier;

@DisplayName("Integration Test | Client Search Service")
class ClientSearchServiceIntegrationTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  ClientSearchService service;

  @ParameterizedTest
  @MethodSource("searchByQuery")
  @DisplayName("Search by query")
  void shouldSearchByQuery(
      List<String> clientNumbers,
      Criteria queryCriteria
  ) {
    service
        .searchClientByQuery(queryCriteria, 0, 10)
        .map(ClientPublicViewDto::getClientNumber)
        .as(StepVerifier::create)
        .expectNextCount(clientNumbers.size())
        .verifyComplete();
  }

  @ParameterizedTest
  @MethodSource("searchById")
  @DisplayName("Search by ID")
  void shouldSearchById(
      List<String> clientNumbers,
      Criteria queryCriteria
  ) {
    Assertions.assertEquals(
        Criteria.empty().and(queryCriteria).toString(),
        service.searchById(clientNumbers).toString()
    );
  }


  private static Stream<Arguments> searchByQuery() {
    return
        Stream
            .of(
                Arguments.of(
                    List.of(),
                    Criteria.empty()
                ),
                Arguments.of(
                    List.of("00000001"),
                    Criteria.where("clientNumber").in("00000001", "2")
                ),
                Arguments.of(
                    List.of(),
                    Criteria
                        .where("clientNumber")
                        .in("4", "5")
                ),
                Arguments.of(
                    List.of("00000008", "00000007", "00000009"),
                    Criteria
                        .where("clientName")
                        .is("bond")
                        .or(
                            Criteria.where("legalMiddleName").is("hunt")
                        )
                )
            );
  }

  private static Stream<Arguments> searchById() {
    return
        Stream
            .of(
                Arguments.of(
                    List.of("00000001"),
                    Criteria.where("clientNumber").in("00000001")
                ),
                Arguments.of(
                    List.of("00000001", "00000002"),
                    Criteria.where("clientNumber").in("00000001", "00000002")
                ),
                Arguments.of(
                    List.of(),
                    Criteria.empty()
                )
            );
  }

}