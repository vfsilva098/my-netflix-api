package br.com.mynetflix.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {

    @Autowired
    private BuildProperties properties;

    @Bean
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage("br.com.mynetflix.api.controller"))
                .paths(any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.properties.get("title"))
                .description(this.properties.get("description"))
                .version(this.properties.get("version"))
                .build();
    }
}
