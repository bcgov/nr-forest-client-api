package ca.bc.gov.api.core.misc.scope;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.web.util.TagUtils;

public class ScopeConstant {
	
	/** Constant identifying the request scope */
	public static final String	REQUEST		= TagUtils.SCOPE_REQUEST;
	
	/** Constant identifying the session scope */
	public static final String	SESSION		= TagUtils.SCOPE_SESSION;
	
	/** Constant identifying the application scope */
	public static final String	APPLICATION	= TagUtils.SCOPE_APPLICATION;
	
	/**
	 * Scope identifier for the standard singleton scope: "singleton".
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 */
	public static final String	SINGLETON	= BeanDefinition.SCOPE_SINGLETON;
	
	/**
	 * Scope identifier for the standard prototype scope: "prototype".
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 */
	public static final String	PROTOTYPE	= BeanDefinition.SCOPE_PROTOTYPE;
	
	/** Constant identifying the view scope as used in JSF2 */
	public static final String	VIEW		= "view";
	
}
