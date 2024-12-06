package io.github.untalsanders.contacts.infrastructure.rest.config;

import io.github.untalsanders.contacts.infrastructure.rest.interceptor.MyCustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final MyCustomInterceptor myCustomInterceptor;

    @Autowired
    public AppConfig(MyCustomInterceptor myCustomInterceptor) {
        this.myCustomInterceptor = myCustomInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myCustomInterceptor);
    }
}
