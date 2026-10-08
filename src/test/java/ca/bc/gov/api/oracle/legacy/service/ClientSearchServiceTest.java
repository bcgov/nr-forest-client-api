package ca.bc.gov.api.oracle.legacy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import ca.bc.gov.api.oracle.legacy.dto.SearchNumberScoreProjection;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientCountEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import ca.bc.gov.api.oracle.legacy.repository.ForestClientRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test | ClientSearchService")
class ClientSearchServiceTest {

  @Mock
  private R2dbcEntityTemplate template;

  @Mock
  private ForestClientRepository forestClientRepository;

  private ClientSearchService service;

  @BeforeEach
  void setUp() {
    service = new ClientSearchService(template, forestClientRepository);
  }

  @Test
  @DisplayName("Should build criteria by IDs")
  void shouldBuildCriteriaById() {
    Criteria criteria = service.searchById(List.of("00000001", "00000002"));
    assertNotNull(criteria);
    assertFalse(criteria.isEmpty());
  }

  @Test
  @DisplayName("Should build empty criteria for empty or null IDs")
  void shouldBuildEmptyCriteria() {
    assertTrue(service.searchById(null).isEmpty());
    assertTrue(service.searchById(Collections.emptyList()).isEmpty());
  }

  @Test
  @DisplayName("Should return empty Flux when criteria is empty")
  void shouldReturnEmptyWhenCriteriaIsEmpty() {
    StepVerifier.create(service.searchClientByQuery(Criteria.empty(), 0, 10))
        .verifyComplete();
  }

  @Test
  @DisplayName("Should search clients by query criteria")
  void shouldSearchClientByQuery() {
    Criteria criteria = Criteria.where("clientNumber").is("00000001");
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000001")
        .clientName("PACIFIC WOODS")
        .build();

    when(template.count(any(Query.class), eq(ForestClientEntity.class)))
        .thenReturn(Mono.just(1L));
    when(template.select(any(Query.class), eq(ForestClientEntity.class)))
        .thenReturn(Flux.just(entity));

    StepVerifier.create(service.searchClientByQuery(criteria, 0, 10))
        .assertNext(dto -> {
          assertEquals("00000001", dto.getClientNumber());
          assertEquals(1L, dto.getCount());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Should search by acronym, name, and padded number")
  void shouldSearchByAcronymNameNumber() {
    SearchNumberScoreProjection projection = new SearchNumberScoreProjection() {
      @Override
      public String getClientNumber() {
        return "00000001";
      }

      @Override
      public Integer getScore() {
        return 1000;
      }
    };

    when(forestClientRepository.searchNumberByNameAcronymNumber(
        eq("BOND"), eq("JB"), eq("00000001"), eq(0L), eq(10)))
        .thenReturn(Flux.just(projection));

    StepVerifier.create(service.searchByAcronymNameNumber("bond", "jb", "1", 0, 10))
        .assertNext(criteria -> assertFalse(criteria.isEmpty()))
        .verifyComplete();
  }

  @Test
  @DisplayName("Should search by IDs and name")
  void shouldSearchByIdsAndName() {
    ForestClientCountEntity countEntity = ForestClientCountEntity.builder()
        .clientNumber("00000001")
        .clientName("WESTERN FORESTRY")
        .count(1L)
        .build();

    when(forestClientRepository.searchByIdsAndName(eq(List.of("00000001")), eq("WESTERN"), eq(0L), eq(10)))
        .thenReturn(Flux.just(countEntity));

    StepVerifier.create(service.searchByIdsAndName(List.of("00000001"), "western", 0, 10))
        .assertNext(dto -> {
          assertEquals("00000001", dto.getClientNumber());
          assertEquals(1L, dto.getCount());
        })
        .verifyComplete();
  }
}

