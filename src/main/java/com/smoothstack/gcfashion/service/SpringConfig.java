package com.smoothstack.gcfashion.service;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring configuration class that sets up beans used in the application
 * 
 * @author jalveste
 *
 */
@EnableSwagger2
@Configuration
public class SpringConfig {

	@Bean
	public StoreService storeServiceBean() {
		return new StoreService();
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("shop")
				.select()
				.paths(PathSelectors.ant("/gcfashions/**"))
				.apis(RequestHandlerSelectors.basePackage("com.smoothstack"))
				.build()
				.apiInfo(apiInfo());
	}
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "GC Fashion Shop API", 
	      "This API manages online store operations for the GC Fashion website.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Team Smoothies A", "http://www.smoothstack.com", "myeaddress@smoothstack.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}
