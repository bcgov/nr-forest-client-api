package ca.bc.gov.api.oracle.legacy.dto;

/**
 * @author Maria Martinez, Government of BC
 * @version 1.0.0
 * @created 2022-11-03
 */

public class ClientPublicFilterObjectVO {

  public String clientName;
  public String clientFirstName;
  public String clientMiddleName;
  public String clientTypeCodesAsCsv;
  public ClientTypeHelper clientType;
  public int currentPage;
  public int itemsPerPage;
  public String sortedColumnName;
  public String sortedColumnDirection;

  public static class ClientTypeHelper {
    public String clientTypeCode;
    public boolean equalsInd;
  }

}
