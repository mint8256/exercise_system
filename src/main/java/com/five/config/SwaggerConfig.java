package com.five.config;

import com.five.config.properties.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * description:
 *
 * @author fly
 * @since 2023/3/2 13:13
 */
@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Bean
    public GroupedOpenApi baseRestApi() {
        return GroupedOpenApi.builder()
                .group("dev")
                .packagesToScan("com.fly")
                .build();

    }

    @Bean
    public OpenAPI OpenApi() {

        OpenAPI openAPI = new OpenAPI().info(info());
        // oauth2.0 password
        openAPI.schemaRequirement(HttpHeaders.AUTHORIZATION, this.securityScheme());
        //全局安全校验项
        openAPI.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
        return openAPI;
    }
    private SecurityScheme securityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        //类型
        securityScheme.setType(SecurityScheme.Type.APIKEY);
        //请求头的name
        securityScheme.setName(HttpHeaders.AUTHORIZATION);
        //token所在位置
        securityScheme.setIn(SecurityScheme.In.HEADER);
        return securityScheme;
    }


    private Info info(){
        return  new Info()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription())
                .contact(new Contact().name(swaggerProperties.getContactName())
                        .url(swaggerProperties.getContactUrl())
                        .email(swaggerProperties.getContactEmail()));

    }

}
