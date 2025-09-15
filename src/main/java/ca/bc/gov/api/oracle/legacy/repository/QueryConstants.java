package ca.bc.gov.api.oracle.legacy.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryConstants {

  public static final String SEARCH_NUMBER_NAME_ACRONYM = """
      SELECT
        CLIENT_NUMBER,
        (
          CASE WHEN CLIENT_ACRONYM = :acronym THEN 800 ELSE 0 END +
          CASE WHEN CLIENT_NUMBER = :clientNumber THEN 1000 ELSE 0 END +
          UTL_MATCH.JARO_WINKLER_SIMILARITY(TRIM(COALESCE(LEGAL_FIRST_NAME || ' ', '') 
                || TRIM(COALESCE(LEGAL_MIDDLE_NAME || ' ', '')) 
                || COALESCE(CLIENT_NAME, '')), :clientName)
        ) AS score
      FROM THE.FOREST_CLIENT
      WHERE
        UTL_MATCH.JARO_WINKLER_SIMILARITY(TRIM(COALESCE(LEGAL_FIRST_NAME || ' ', '') 
              || TRIM(COALESCE(LEGAL_MIDDLE_NAME || ' ', '')) 
              || COALESCE(CLIENT_NAME, '')), :clientName) >= 80
        OR CLIENT_ACRONYM = :acronym
        OR CLIENT_NUMBER = :clientNumber
      ORDER BY score DESC
      OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY""";

  public static final String SEARCH_BY_NUMBER_AND_NAME = """
      WITH ResultSet AS (
        SELECT
          *
        FROM THE.FOREST_CLIENT
        WHERE
          CLIENT_NUMBER IN(:clientNumber)
          AND
          (
            NVL(:clientName,'NOVALUE') = 'NOVALUE' OR
                UTL_MATCH.JARO_WINKLER_SIMILARITY(TRIM(COALESCE(LEGAL_FIRST_NAME || ' ', '')
                      || TRIM(COALESCE(LEGAL_MIDDLE_NAME || ' ', ''))
                      || COALESCE(CLIENT_NAME, '')), :clientName) >= 80
            )
        )
        SELECT
        COUNT(*) OVER() AS COUNT,
        rs.*
        FROM ResultSet rs
        OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY""";
}
