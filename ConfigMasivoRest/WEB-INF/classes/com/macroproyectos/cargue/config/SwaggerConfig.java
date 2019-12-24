package com.macroproyectos.cargue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author alcides.sanchez
 *
 */
@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docketApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getAPIInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.macroproyectos.cargue.web"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo getAPIInfo() {
		return new ApiInfoBuilder().title("Config Masivo").description("Cargue masivo a config. ")
				.license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}

}
