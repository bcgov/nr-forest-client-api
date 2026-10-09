package ca.bc.gov.api.oracle.legacy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import ca.bc.gov.api.oracle.legacy.dto.YesNoEnum;
import ca.bc.gov.api.oracle.legacy.entity.ClientLocationEntity;
import ca.bc.gov.api.oracle.legacy.exception.ClientNotFoundException;
import ca.bc.gov.api.oracle.legacy.repository.ClientLocationRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test | ClientLocationService")
class ClientLocationServiceTest {

  @Mock
  private ClientLocationRepository repository;

  private ClientLocationService service;

  @BeforeEach
  void setUp() {
    service = new ClientLocationService(repository);
  }

  @Test
  @DisplayName("Should count client locations")
  void shouldCountClientLocations() {
    when(repository.countByClientNumber("00000001")).thenReturn(Mono.just(3L));

    StepVerifier.create(service.countClientLocations("00000001"))
        .expectNext(3L)
        .verifyComplete();
  }

  @Test
  @DisplayName("Should list client locations with valid mapping")
  void shouldListClientLocations() {
    ClientLocationEntity entity = ClientLocationEntity.builder()
        .clientNumber("00000001")
        .locationCode("00")
        .locationName("Head Office")
        .companyCode("12345")
        .address1("123 Main St")
        .city("Victoria")
        .province("BC")
        .postalCode("V8V1V1")
        .country("CANADA")
        .businessPhone("2505551234")
        .email("office@example.com")
        .expired("N")
        .trusted("Y")
        .returnedMailDate(LocalDateTime.of(2025, 1, 15, 10, 0))
        .build();

    when(repository.findByClientNumber(eq("00000001"), any(Pageable.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.listClientLocations("00000001", 0, 10))
        .assertNext(dto -> {
          assertNotNull(dto);
          assertEquals("00000001", dto.clientNumber());
          assertEquals("00", dto.locationCode());
          assertEquals("Head Office", dto.locationName());
          assertEquals("Victoria", dto.city());
          assertEquals(YesNoEnum.NO, dto.expired());
          assertEquals(YesNoEnum.YES, dto.trusted());
          assertNotNull(dto.returnedMailDate());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should handle null expired, trusted, and mail date in list")
  void shouldHandleNullFlagsInList() {
    ClientLocationEntity entity = ClientLocationEntity.builder()
        .clientNumber("00000001")
        .locationCode("01")
        .expired(null)
        .trusted(null)
        .returnedMailDate(null)
        .build();

    when(repository.findByClientNumber(eq("00000001"), any(Pageable.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.listClientLocations("00000001", 0, 10))
        .assertNext(dto -> {
          assertNull(dto.expired());
          assertNull(dto.trusted());
          assertNull(dto.returnedMailDate());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should get client location details when found")
  void shouldGetClientLocationDetailsWhenFound() {
    ClientLocationEntity entity = ClientLocationEntity.builder()
        .clientNumber("00000001")
        .locationCode("00")
        .locationName("Main Site")
        .expired("N")
        .trusted("N")
        .build();

    when(repository.findByClientNumberAndLocationCode("00000001", "00"))
        .thenReturn(Mono.just(entity));

    StepVerifier.create(service.getClientLocationDetails("00000001", "00"))
        .assertNext(dto -> {
          assertEquals("00000001", dto.clientNumber());
          assertEquals("00", dto.locationCode());
          assertEquals(YesNoEnum.NO, dto.expired());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should throw ClientNotFoundException when location not found")
  void shouldThrowWhenLocationNotFound() {
    when(repository.findByClientNumberAndLocationCode("00000001", "99"))
        .thenReturn(Mono.empty());

    StepVerifier.create(service.getClientLocationDetails("00000001", "99"))
        .expectError(ClientNotFoundException.class)
        .verify();
  }
}

