package ca.bc.gov.api.oracle.legacy.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Unit Test | Enum Definitions")
class EnumTest {

  @ParameterizedTest
  @CsvSource({
      "A, Association",
      "B, First Nation Band",
      "C, Corporation",
      "F, Ministry of Forests and Range",
      "G, Government",
      "I, Individual",
      "L, Limited Partnership",
      "P, General Partnership",
      "R, First Nation Group",
      "S, Society",
      "T, First Nation Tribal Council",
      "U, Unregistered Company",
      "Z, Sole Proprietorship"
  })
  @DisplayName("Should resolve known ClientTypeCodeEnum")
  void shouldResolveClientTypeCodeEnum(String code, String expectedDescription) {
    ClientTypeCodeEnum typeCode = ClientTypeCodeEnum.fromCode(code);
    assertNotNull(typeCode);
    assertEquals(expectedDescription, typeCode.getDescription());
    assertEquals(expectedDescription, ClientTypeCodeEnum.getSafeDescription(code));
  }

  @Test
  @DisplayName("Should handle unknown or null ClientTypeCodeEnum safely")
  void shouldHandleUnknownOrNullClientTypeCode() {
    assertNull(ClientTypeCodeEnum.fromCode(null));
    assertNull(ClientTypeCodeEnum.fromCode("UNKNOWN"));
    assertEquals("", ClientTypeCodeEnum.getSafeDescription(null));
    assertEquals("UNKNOWN", ClientTypeCodeEnum.getSafeDescription("UNKNOWN"));
  }

  @ParameterizedTest
  @CsvSource({
      "ACT, Active",
      "DAC, Deactivated",
      "DEC, Deceased",
      "REC, Receivership",
      "SPN, Suspended"
  })
  @DisplayName("Should resolve known ClientStatusCodeEnum")
  void shouldResolveClientStatusCodeEnum(String code, String expectedDescription) {
    ClientStatusCodeEnum statusCode = ClientStatusCodeEnum.fromCode(code);
    assertNotNull(statusCode);
    assertEquals(expectedDescription, statusCode.getDescription());
    assertEquals(expectedDescription, ClientStatusCodeEnum.getSafeDescription(code));
  }

  @Test
  @DisplayName("Should handle unknown or null ClientStatusCodeEnum safely")
  void shouldHandleUnknownOrNullClientStatusCode() {
    assertNull(ClientStatusCodeEnum.fromCode(null));
    assertNull(ClientStatusCodeEnum.fromCode("INVALID"));
    assertEquals("", ClientStatusCodeEnum.getSafeDescription(null));
    assertEquals("INVALID", ClientStatusCodeEnum.getSafeDescription("INVALID"));
  }

  @ParameterizedTest
  @CsvSource({
      "Y, YES",
      "y, YES",
      "N, NO",
      "n, NO"
  })
  @DisplayName("Should resolve YesNoEnum from value")
  void shouldResolveYesNoEnum(String input, YesNoEnum expected) {
    assertEquals(expected, YesNoEnum.fromValue(input));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "   "})
  @DisplayName("Should return null for blank YesNoEnum value")
  void shouldReturnNullForBlankYesNoEnum(String input) {
    assertNull(YesNoEnum.fromValue(input));
  }

  @Test
  @DisplayName("Should return null for null YesNoEnum value")
  void shouldReturnNullForNullYesNoEnum() {
    assertNull(YesNoEnum.fromValue(null));
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException for invalid YesNoEnum value")
  void shouldThrowForInvalidYesNoEnum() {
    assertThrows(IllegalArgumentException.class, () -> YesNoEnum.fromValue("MAYBE"));
  }
}

