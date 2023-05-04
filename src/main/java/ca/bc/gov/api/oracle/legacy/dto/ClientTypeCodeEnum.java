package ca.bc.gov.api.oracle.legacy.dto;

import lombok.Getter;

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
}
