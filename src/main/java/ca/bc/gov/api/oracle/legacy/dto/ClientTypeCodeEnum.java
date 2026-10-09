package ca.bc.gov.api.oracle.legacy.dto;

import lombok.Getter;

/** Enumerates supported client type codes. */
@Getter
public enum ClientTypeCodeEnum {
  A("Association"),
  B("First Nation Band"),
  C("Corporation"),
  F("Ministry of Forests and Range"),
  G("Government"),
  I("Individual"),
  L("Limited Partnership"),
  P("General Partnership"),
  R("First Nation Group"),
  S("Society"),
  T("First Nation Tribal Council"),
  U("Unregistered Company");

  private final String description;

  ClientTypeCodeEnum(String description) {
    this.description = description;
  }

  /**
   * Safely retrieves the enum matching the code, or null if not found.
   *
   * @param code the client type code
   * @return the matching enum, or null if code is null or unrecognized
   */
  public static ClientTypeCodeEnum fromCode(String code) {
    if (code == null) {
      return null;
    }
    for (ClientTypeCodeEnum typeCode : values()) {
      if (typeCode.name().equalsIgnoreCase(code.trim())) {
        return typeCode;
      }
    }
    return null;
  }

  /**
   * Safely retrieves the description for a given client type code.
   *
   * @param code the client type code
   * @return the description, or the code itself / empty string if unrecognized
   */
  public static String getSafeDescription(String code) {
    ClientTypeCodeEnum found = fromCode(code);
    return found != null ? found.getDescription() : (code != null ? code : "");
  }
}
