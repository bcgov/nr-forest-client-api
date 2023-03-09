package ca.bc.gov.api.oracle.legacy.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

  @JsonCreator
  public static YesNoEnum fromValue(String value) {
    for (YesNoEnum c : values()) {
      if (c.value().equalsIgnoreCase(value)) {
        return c;
      }
    }
    throw new IllegalArgumentException(value);
  }
}
