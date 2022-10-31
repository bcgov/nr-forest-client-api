package ca.bc.gov.api.core.util.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.bc.gov.api.core.util.CoreUtil;

@Component
@Qualifier(CoreUtil.BEAN_NAME)
public class CoreUtilImpl implements CoreUtil {

    @Override
    public Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    @Override
    public boolean isNumber(String str) {
        return str.matches("^\\d+$");
    }
    
    @Override
    public <T> T jsonStringToObj(String jsonInString, Class<T> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(jsonInString, valueType);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string: " + jsonInString + " to object of type: " + valueType.getCanonicalName(), e);
        }
    }

}
