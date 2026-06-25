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
   * @return the matching enum constant
   */
  @JsonCreator
  public static YesNoEnum fromValue(String value) {
    for (YesNoEnum candidate : values()) {
      if (candidate.value().equalsIgnoreCase(value)) {
        return candidate;
      }
    }
    throw new IllegalArgumentException(value);
  }
}
