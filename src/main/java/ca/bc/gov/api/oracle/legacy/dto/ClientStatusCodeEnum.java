package ca.bc.gov.api.oracle.legacy.dto;

import lombok.Getter;

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
}
