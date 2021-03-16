package com.doemais.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// Link de acesso ao Swagger
	// https://localhost:8081/swagger-ui/

//	@Autowired
//	private BuildProperties buildProperties;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfoV1())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
//          .apis(RequestHandlerSelectors.any())   
				.apis(RequestHandlerSelectors.basePackage("com.doemais.api")).paths(PathSelectors.any()).build()
				.tags(new Tag("Anuncio", "Gerencia os anúncios"),
						new Tag("Usuario", "Gerencia os usuários pessoa física"),
						new Tag("Categoria", "Gerencia as categorias"), 
						new Tag("Auth", "Gerencia a autenticação"),
						new Tag("Endereco", "Gerencia os endereços"));
//				.ignoredParameterTypes(Auth.class);	

	}

	public ApiInfo apiInfoV1() {
		return new ApiInfoBuilder().description("API Rest da aplicação Doe+").title("Doe+").version("1.0.0")
//				.version(buildProperties.getVersion())
				.contact(new Contact("Doe+", "https://doemais-ifsp.blogspot.com/", "doemais123@gmail.com")).build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}
