package com.example.helloWorld.error;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errPage = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
                factory.addErrorPages(errPage);
            }
        };
    }
}
