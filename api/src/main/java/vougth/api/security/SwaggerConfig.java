package vougth.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
@Order(100)
public class SwaggerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs")
            .permitAll().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.vought"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.any())
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Documentation",
                "API documentation for your Vought application",
                "1.0",
                "Terms of service",
                new Contact("Your Name", "your-website.com", "your-email@example.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    private Filter securityFilter() throws Exception {
        return new BasicAuthenticationFilter(authenticationManager());
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getPrincipal().toString();
                String password = authentication.getCredentials().toString();

                // Verificação do usuário e senha
                if (isValidCredentials(username, password)) {
                    return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
                } else {
                    throw new BadCredentialsException("Invalid username or password");
                }
            }
        };
    }

    private boolean isValidCredentials(String username, String password) {
        System.out.println("método do login");

        // Exemplo simples para fins de demonstração
        boolean loginResult = "admin".equals(username) && "admin".equals(password);
        System.out.println("login = " + loginResult );
        return loginResult;
    }

}
