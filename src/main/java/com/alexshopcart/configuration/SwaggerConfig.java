package com.alexshopcart.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Value("${openapi.project-title}")
	private String projectTitle;

	@Value("${openapi.project-description}")
	private String projectDescription;

	@Value("${openapi.project-version}")
	private String projectVersion;

	@Bean
	public OpenAPI customOpenAPI() {
		OpenApiProperties properties = infoGet();

		return new OpenAPI().info(getInfo(properties));
	}

	private OpenApiProperties infoGet() {
		OpenApiProperties prop = new OpenApiProperties();
		prop.setProjectTitle(projectTitle);
		prop.setProjectVersion(projectVersion);
		prop.setProjectDescription(projectDescription);

		return prop;
	}

	private Info getInfo(OpenApiProperties properties) {
		return new Info().title(properties.getProjectTitle()).description(properties.getProjectDescription())
				.version(properties.getProjectVersion()).license(getLicense());
	}

	private License getLicense() {
		return new License().name("Unlicense").url("https://unlicense.org/");
	}

}
