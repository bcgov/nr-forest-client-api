package ca.bc.gov.api.core.util.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ca.bc.gov.api.core.util.CoreUtil;

@Component
@Qualifier(CoreUtil.BEAN_NAME)
public class CoreUtilImpl implements CoreUtil {

    @Override
    public Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * Check if given string is a number (digits only)
     * 
     * @param str
     * @return true if this string is a number
     */
    @Override
    public boolean isNumber(String str) {
        return str.matches("^\\d+$");
    }

}
