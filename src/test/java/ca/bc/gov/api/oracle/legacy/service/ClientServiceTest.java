package ca.bc.gov.api.oracle.legacy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import ca.bc.gov.api.oracle.legacy.ApplicationConstants;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.exception.InvalidClientNumberException;
import ca.bc.gov.api.oracle.legacy.exception.NoSearchParameterFound;
import ca.bc.gov.api.oracle.legacy.repository.ForestClientRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test | ClientService")
class ClientServiceTest {

  @Mock
  private ForestClientRepository forestClientRepository;

  @Mock
  private R2dbcEntityTemplate template;

  private ClientService service;

  @BeforeEach
  void setUp() {
    service = new ClientService(forestClientRepository, template);
  }

  @Test
  @DisplayName("Should return client when found by numeric client number")
  void shouldFindByClientNumber() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000001")
        .clientName("MINISTRY OF FORESTS")
        .clientStatusCode("ACT")
        .clientTypeCode("G")
        .clientAcronym("MOF")
        .build();

    when(forestClientRepository.findById("00000001")).thenReturn(Mono.just(entity));

    StepVerifier.create(service.findByClientNumber("00000001"))
        .assertNext(dto -> {
          assertEquals("00000001", dto.getClientNumber());
          assertEquals("MINISTRY OF FORESTS", dto.getClientName());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should fail when client number is non-numeric")
  void shouldFailOnNonNumericClientNumber() {
    StepVerifier.create(service.findByClientNumber("abc"))
        .expectError(InvalidClientNumberException.class)
        .verify();
  }

  @Test
  @DisplayName("Should fail when client number is not found")
  void shouldFailWhenClientNotFound() {
    when(forestClientRepository.findById("00000099")).thenReturn(Mono.empty());

    StepVerifier.create(service.findByClientNumber("00000099"))
        .expectError(ClientNotFoundException.class)
        .verify();
  }

  @Test
  @DisplayName("Should find clients by number or name with count and descriptions")
  void shouldFindByClientNumberOrName() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000002")
        .clientName("PACIFIC WOODS")
        .clientStatusCode("ACT")
        .clientTypeCode("C")
        .build();

    when(forestClientRepository.countByClientNumberContainingOrClientNameContaining("PACIFIC", "PACIFIC"))
        .thenReturn(Mono.just(1L));
    when(forestClientRepository.findByClientNumberContainingOrClientNameContaining(
        eq("PACIFIC"), eq("PACIFIC"), any(Pageable.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.findByClientNumberOrName(0, 10, "PACIFIC"))
        .assertNext(dto -> {
          assertEquals("00000002", dto.getClientNumber());
          assertEquals("Corporation", dto.getClientTypeCodeDescription());
          assertEquals("Active", dto.getClientStatusCodeDescription());
          assertEquals(1L, dto.getCount());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should find non-individual clients")
  void shouldFindAllNonIndividualClients() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000003")
        .clientName("ABC FORESTRY")
        .clientStatusCode("ACT")
        .clientTypeCode("C")
        .build();

    when(forestClientRepository.countByClientTypeCodeNot(ApplicationConstants.INDIVIDUAL))
        .thenReturn(Mono.just(5L));
    when(forestClientRepository.findByClientTypeCodeNot(eq(ApplicationConstants.INDIVIDUAL), any(Pageable.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.findAllNonIndividualClients(0, 10, "clientName"))
        .assertNext(dto -> {
          assertEquals("00000003", dto.getClientNumber());
          assertEquals(5L, dto.getCount());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should fail searchByNames when all parameters are empty")
  void shouldFailSearchByNamesWhenAllEmpty() {
    StepVerifier.create(service.searchByNames(null, null, null, null, 0, 10))
        .expectError(NoSearchParameterFound.class)
        .verify();
  }

  @Test
  @DisplayName("Should search by names when parameters provided")
  void shouldSearchByNames() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000004")
        .clientName("SMITH")
        .legalFirstName("JOHN")
        .clientStatusCode("ACT")
        .clientTypeCode("I")
        .build();

    when(template.count(any(Query.class), eq(ForestClientEntity.class)))
        .thenReturn(Mono.just(1L));
    when(template.select(any(Query.class), eq(ForestClientEntity.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.searchByNames("SMITH", "JOHN", null, List.of("I"), 0, 10))
        .assertNext(dto -> {
          assertEquals("00000004", dto.getClientNumber());
          assertEquals(1L, dto.getCount());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should search by acronym when found")
  void shouldSearchByAcronym() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000005")
        .clientName("TEST ACRO")
        .clientStatusCode("ACT")
        .clientTypeCode("C")
        .clientAcronym("TACRO")
        .build();

    when(forestClientRepository.findByClientAcronym("TACRO"))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.searchByAcronym("tacro"))
        .assertNext(dto -> {
          assertEquals("00000005", dto.getClientNumber());
          assertEquals(1L, dto.getCount());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should fail searchByAcronym when acronym is blank")
  void shouldFailSearchByAcronymWhenBlank() {
    StepVerifier.create(service.searchByAcronym("   "))
        .expectError(NoSearchParameterFound.class)
        .verify();
  }

  @Test
  @DisplayName("Should fail searchByAcronym when no client found")
  void shouldFailSearchByAcronymWhenNotFound() {
    when(forestClientRepository.findByClientAcronym("NONE"))
        .thenReturn(Flux.empty());

    StepVerifier.create(service.searchByAcronym("NONE"))
        .expectError(ClientNotFoundException.class)
        .verify();
  }
}

