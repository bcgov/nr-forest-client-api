package ca.bc.gov.api.oracle.legacy.dto;

/** Projection that exposes a client number with its calculated search score. */
public interface SearchNumberScoreProjection {

  String getClientNumber();

  Integer getScore();
}
