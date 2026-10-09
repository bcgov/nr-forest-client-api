package ca.bc.gov.api.oracle.legacy.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientCountEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit Test | ClientMapper")
class ClientMapperTest {

  @Test
  @DisplayName("Should map ForestClientEntity to ClientPublicViewDto")
  void shouldMapEntityToDto() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000001")
        .clientName("MINISTRY OF FORESTS")
        .legalFirstName("JOHN")
        .legalMiddleName("DOE")
        .clientStatusCode("ACT")
        .clientTypeCode("G")
        .clientAcronym("MOF")
        .build();

    ClientPublicViewDto dto = ClientMapper.mapEntityToDto(entity);

    assertNotNull(dto);
    assertEquals("00000001", dto.getClientNumber());
    assertEquals("MINISTRY OF FORESTS", dto.getClientName());
    assertEquals("JOHN", dto.getLegalFirstName());
    assertEquals("DOE", dto.getLegalMiddleName());
    assertEquals("ACT", dto.getClientStatusCode());
    assertEquals("G", dto.getClientTypeCode());
    assertEquals("MOF", dto.getAcronym());
    assertNull(dto.getCount());
  }

  @Test
  @DisplayName("Should map ForestClientEntity and count to ClientPublicViewDto")
  void shouldMapEntityToDtoWithCount() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000002")
        .clientName("SAMPLE CORP")
        .clientStatusCode("ACT")
        .clientTypeCode("C")
        .clientAcronym("SC")
        .build();

    ClientPublicViewDto dto = ClientMapper.mapEntityToDto(entity, 42L);

    assertNotNull(dto);
    assertEquals("00000002", dto.getClientNumber());
    assertEquals("SAMPLE CORP", dto.getClientName());
    assertEquals("ACT", dto.getClientStatusCode());
    assertEquals("C", dto.getClientTypeCode());
    assertEquals("SC", dto.getAcronym());
    assertEquals(42L, dto.getCount());
  }

  @Test
  @DisplayName("Should map ForestClientCountEntity to ClientPublicViewDto")
  void shouldMapCountEntityToDto() {
    ForestClientCountEntity entity = ForestClientCountEntity.builder()
        .clientNumber("00000003")
        .clientName("FOREST ENTERPRISES")
        .legalFirstName("JANE")
        .legalMiddleName("SMITH")
        .clientStatusCode("DAC")
        .clientTypeCode("I")
        .clientAcronym("FE")
        .count(10L)
        .build();

    ClientPublicViewDto dto = ClientMapper.mapEntityToDto(entity);

    assertNotNull(dto);
    assertEquals("00000003", dto.getClientNumber());
    assertEquals("FOREST ENTERPRISES", dto.getClientName());
    assertEquals("JANE", dto.getLegalFirstName());
    assertEquals("SMITH", dto.getLegalMiddleName());
    assertEquals("DAC", dto.getClientStatusCode());
    assertEquals("I", dto.getClientTypeCode());
    assertEquals("FE", dto.getAcronym());
    assertEquals(10L, dto.getCount());
  }

  @Test
  @DisplayName("Should map ForestClientEntity to ClientViewDto")
  void shouldMapEntityToClientViewDto() {
    ForestClientEntity entity = ForestClientEntity.builder()
        .clientNumber("00000004")
        .clientName("PACIFIC WOODS")
        .legalFirstName("ALICE")
        .legalMiddleName("W")
        .clientStatusCode("ACT")
        .clientTypeCode("A")
        .clientAcronym("PW")
        .build();

    ClientViewDto dto = ClientMapper.mapEntityToClientViewDto(entity);

    assertNotNull(dto);
    assertEquals("00000004", dto.getClientNumber());
    assertEquals("PACIFIC WOODS", dto.getClientName());
    assertEquals("ALICE", dto.getLegalFirstName());
    assertEquals("W", dto.getLegalMiddleName());
    assertEquals("ACT", dto.getClientStatusCode());
    assertEquals("A", dto.getClientTypeCode());
    assertEquals("PW", dto.getAcronym());
  }
}

