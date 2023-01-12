package ca.bc.gov.api.m.oracle.legacyclient.dao.impl;

import ca.bc.gov.api.core.configuration.OraclePersistenceConfiguration;
import ca.bc.gov.api.core.util.CoreUtil;
import ca.bc.gov.api.m.oracle.legacyclient.dao.ClientPublicViewDao;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicFilterObjectVO;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 * @author Maria Martinez, Government of BC
 * @created 2022-11-03
 * @version 1.0.0
 */

@Repository("clientPublicViewDao")
public class ClientPublicViewDaoImpl implements ClientPublicViewDao {

	public static final Logger logger = LoggerFactory.getLogger(ClientPublicViewDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private CoreUtil coreUtil;
	

	@Override
	public List<ClientPublicViewVO> retrieveSearchResultItems(ClientPublicFilterObjectVO filterObject) {
		
		if (coreUtil.isNullOrBlank(filterObject.sortedColumnName)) {
			filterObject.sortedColumnName = "clientNumber";	
		}
		
		if (coreUtil.isNullOrBlank(filterObject.sortedColumnDirection)) {
			filterObject.sortedColumnDirection = "asc";	
		} 
		
		String sql = buildSearchItemsSql(filterObject);
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ClientPublicViewVO>>() {

			@Override
			public List<ClientPublicViewVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ClientPublicViewVO> resultItems = new ArrayList<ClientPublicViewVO>();
				while (rs.next()) {
					ClientPublicViewVO resultItem = toSearchItem(rs);
					resultItems.add(resultItem);
				}
				return resultItems;
			}

			private ClientPublicViewVO toSearchItem(ResultSet rs) throws SQLException {
				ClientPublicViewVO resultItem = new ClientPublicViewVO();
				resultItem.clientNumber = rs.getString("client_number");
				resultItem.clientName = rs.getString("client_name");
				resultItem.legalFirstName = rs.getString("legal_first_name");
				resultItem.legalMiddleName = rs.getString("legal_middle_name");
				resultItem.clientStatusCode = rs.getString("client_status_code");
				resultItem.clientTypeCode = rs.getString("client_type_code");
				return resultItem;
			}

		});
	}

	@SuppressWarnings("removal")
	private String buildSearchItemsSql(ClientPublicFilterObjectVO filterObject) {
		
        Long fromRowNum;
        if (filterObject.currentPage == 1)
            fromRowNum = 1L;
        else
            fromRowNum = new Long(filterObject.currentPage - 1) * filterObject.itemsPerPage + 1;
        Long toRowRowNum = fromRowNum + filterObject.itemsPerPage - 1;

		StringBuilder sb = new StringBuilder();
		sb.append("select * ");
		sb.append("from (");
		sb.append("		select qry1.*, row_number() over (" +
				  "							order by " + 
				  							toSqlColumnName(filterObject.sortedColumnName) + " " + 
				  							filterObject.sortedColumnDirection + ") rn ");
		sb.append("		from (");
		buildMainQuery(sb);
		sb.append("		) qry1 ");
		sb.append("		where 1=1 ");
		buildConditions(sb, filterObject);		
		sb.append(") qry2 ");
		sb.append("where rn between " + fromRowNum + " and " + toRowRowNum);
		
		logger.info("SQL - Search Items: " + sb.toString());
		return sb.toString();
	}

	private void buildMainQuery(StringBuilder sb) {
		String select = "select * " + 
						"from " + OraclePersistenceConfiguration.ORACLE_ATTRIBUTE_SCHEMA_QUALIFIER + "v_client_public ";
		sb.append(select);
	}

	private void buildConditions(StringBuilder sb, ClientPublicFilterObjectVO filterObject) {

		if (!coreUtil.isNullOrBlank(filterObject.clientFirstName)) {
			sb.append("and upper(legal_first_name) like upper('%" + filterObject.clientFirstName + "%') ");
		}
		
		if (!coreUtil.isNullOrBlank(filterObject.clientMiddleName)) {
			sb.append("and upper(legal_middle_name) like upper('%" + filterObject.clientMiddleName + "%') ");
		}

		if (!coreUtil.isNullOrBlank(filterObject.clientName)) {
			sb.append("and upper(client_name) like upper('%" + filterObject.clientName + "%') ");
		}
		
		if (!coreUtil.isNullOrBlank(filterObject.clientTypeCodesAsCsv)) {
			sb.append("and client_type_code in (" + coreUtil.fromStringListToCsvWithAposthrophe(filterObject.clientTypeCodesAsCsv) + ") ");
		}
		
		if (null != filterObject.clientType) {
			if (filterObject.clientType.equalsInd)
				sb.append("and upper(client_type_code) = upper('" + filterObject.clientType.clientTypeCode + "') ");
			else
				sb.append("and upper(client_type_code) != upper('" + filterObject.clientType.clientTypeCode + "') ");
		}

	}
	
	private String toSqlColumnName(String sortedColumnName) {
		if ("clientName".equals(sortedColumnName)) {
			return "client_name";
		}
		else if ("clientNumber".equals(sortedColumnName)) {
			return "client_number";	
		}
		else {
			throw new IllegalArgumentException("Unexpected 'sortedColumnValue' arg was passed into call to method 'toSqlColumnName': " + sortedColumnName);
		}	
	}

}
