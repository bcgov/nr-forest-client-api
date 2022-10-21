package ca.bc.gov.api.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
	    registry.viewResolver("/view/", ".jsp");
	} 
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	//registry.addRedirectViewController("/", "/swagger-ui.html");
        //registry.addViewController("/").setViewName("/swagger-ui/index.html");
        registry.addViewController("/").setViewName("/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
}