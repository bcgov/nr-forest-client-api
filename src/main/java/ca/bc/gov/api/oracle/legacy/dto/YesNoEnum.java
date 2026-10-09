package ca.bc.gov.api.oracle.legacy.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Represents a yes-or-no flag stored as {@code Y} or {@code N}. */
public enum YesNoEnum {
  YES("Y"),
  NO("N");

  private final String value;

  YesNoEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String value() {
    return this.value;
  }

  /**
   * Creates an enum value from the serialized representation.
   *
   * @param value the serialized value
   * @return the matching enum constant, or null if input is null or blank
   */
  @JsonCreator
  public static YesNoEnum fromValue(String value) {
    if (value == null || value.trim().isEmpty()) {
      return null;
    }
    for (YesNoEnum candidate : values()) {
      if (candidate.value().equalsIgnoreCase(value.trim())) {
        return candidate;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
