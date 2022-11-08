package ca.bc.gov.api.m.oracle.legacyclient.dao;

import java.util.List;

import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicFilterObjectVO;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

/**
 * @author Maria Martinez, Government of BC
 * @created 2022-11-03
 * @version 1.0.0
 */

public interface ClientPublicViewDao {

	List<ClientPublicViewVO> retrieveSearchResultItems(ClientPublicFilterObjectVO filterObject);

}
