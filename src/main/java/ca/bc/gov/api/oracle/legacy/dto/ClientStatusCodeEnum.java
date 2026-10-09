package ca.bc.gov.api.oracle.legacy.dto;

import lombok.Getter;

/** Enumerates supported client status codes. */
@Getter
public enum ClientStatusCodeEnum {
  ACT("Active"),
  DAC("Deactivated"),
  DEC("Deceased"),
  REC("Receivership"),
  SPN("Suspended");

  private final String description;

  ClientStatusCodeEnum(String description) {
    this.description = description;
  }

  /**
   * Safely retrieves the enum matching the code, or null if not found.
   *
   * @param code the client status code
   * @return the matching enum, or null if code is null or unrecognized
   */
  public static ClientStatusCodeEnum fromCode(String code) {
    if (code == null) {
      return null;
    }
    for (ClientStatusCodeEnum statusCode : values()) {
      if (statusCode.name().equalsIgnoreCase(code.trim())) {
        return statusCode;
      }
    }
    return null;
  }

  /**
   * Safely retrieves the description for a given client status code.
   *
   * @param code the client status code
   * @return the description, or the code itself / empty string if unrecognized
   */
  public static String getSafeDescription(String code) {
    ClientStatusCodeEnum found = fromCode(code);
    return found != null ? found.getDescription() : (code != null ? code : "");
  }
}
