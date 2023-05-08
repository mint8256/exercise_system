package com.five.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:swagger.properties",encoding = "UTF-8",ignoreResourceNotFound = true)
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private boolean enable;
    private String title;
    private String description;
    private String version;
    private String contactName;
    private String contactUrl;
    private String contactEmail;

}
