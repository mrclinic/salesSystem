package com.example.salessystem.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicates;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	static final String detailDescription = "The `SalesSystem Microservice` is a RESTful API that provides \n"
			+ "the functionality for manging products,client,sales. \n"
			+ "Below is a list of available REST API calls for SalesSystem resources.";

	@Bean
	public Docket api() {
		// @formatter:off
		// Register the controllers to swagger
		// Also it is configuring the Swagger Docket
		return new Docket(DocumentationType.SWAGGER_2).select()
				// .apis(RequestHandlerSelectors.any())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				// .paths(PathSelectors.any())
				// .paths(PathSelectors.ant("/swagger2-demo"))
				.build().apiInfo(apiInfo());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// enabling swagger-ui part for visual documentation
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SalesSystem Rest Service Overview").description(detailDescription)
				.termsOfServiceUrl("http://springfox.io").contact("MR").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}
}