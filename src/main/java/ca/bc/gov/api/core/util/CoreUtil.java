package ca.bc.gov.api.core.util;

import java.util.Date;

public interface CoreUtil {

	String BEAN_NAME = "coreUtil";

	Date getCurrentTime();
	
	boolean isNumber(String str);

    <T> T jsonStringToObj(String jsonInString, Class<T> valueType);
	
}
