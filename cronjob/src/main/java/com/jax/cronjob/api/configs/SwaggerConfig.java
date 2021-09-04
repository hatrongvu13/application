package com.jax.cronjob.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Autowired
    ServletContext servletContext;

//	@Value("${swagger.host}")
//	private String host;

    @Value("${swagger.basePath:/}")
    private String basePath;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(getApiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.jax.authen.api.controllers"))
                .paths(PathSelectors.any()).build().securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    private ApiInfo getApiInfo() {
        Contact contact = new Contact("HTV", "fb.com/iamhatrongvu", "hatrongvu13@gmail.com");
        return new ApiInfoBuilder().title("Spring Boot Swagger").description("HTV").version("1.0.0")
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").contact(contact)
                .build();
    }

//    private Set<String> protocols() {
//		Set<String> protocols = new HashSet<>();
//		protocols.add("http");
//		protocols.add("https");
//		return protocols;
//	}

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> authorizationTypes = Arrays.asList(new ApiKey("token", "Authorization", "header"));
        return authorizationTypes;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = Arrays.asList(SecurityContext.builder()
                .securityReferences(defaultAuth()).build());
        return securityContexts;
    }

//	private List<SecurityReference> securityReferences() {
//		List<SecurityReference> securityReferences = Arrays
//				.asList(SecurityReference.builder().reference("token").scopes(new AuthorizationScope[0]).build());
//		return securityReferences;
//	}

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("token",
                authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }


    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .build();
    }
}