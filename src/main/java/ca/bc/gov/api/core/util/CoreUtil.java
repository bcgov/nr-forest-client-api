package ca.bc.gov.api.core.util;

import java.util.Date;
import java.util.List;

public interface CoreUtil {

	String BEAN_NAME = "coreUtil";

	Date getCurrentTime();
	
	boolean isNumber(String str);

    <T> T jsonStringToObj(String jsonInString, Class<T> valueType);

	String objToJsonString(Object obj);

	boolean isNullOrBlank(String str);

	List<String> fromCsvToStringList(String csvString);

	String fromStringListToCsvWithAposthrophe(String csvString);
	
}
