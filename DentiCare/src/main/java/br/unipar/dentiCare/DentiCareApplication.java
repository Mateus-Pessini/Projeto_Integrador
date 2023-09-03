package br.unipar.dentiCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class DentiCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentiCareApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.unipar.dentiCare"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));
	}

	public ApiInfo apiInfo() {

		return new ApiInfo("DentiCare",
				"Criado no Terceiro Ano de ADS 2023 - Unipar",
				"1.0", null, "Felipe José Leite e Mateus Pessini Scherer", null, null);
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Collections.singletonList(defaultAuth()))
				.build();
	}

	private SecurityReference defaultAuth() {
		return SecurityReference.builder()
				.scopes(new springfox.documentation.service.AuthorizationScope[0])
				.reference("Authorization")
				.build();
	}

}
