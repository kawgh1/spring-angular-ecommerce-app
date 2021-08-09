package com.kwgdev.ecommerce.Config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * created by kw on 8/9/2021 @ 2:37 AM
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // protect endpoint /api/orders
        http.authorizeRequests()
                .antMatchers("/api/orders/**") // protect the endpoint... only accessible to authenticated users
                .authenticated()
                .and()
                .oauth2ResourceServer() // Configures OAuth2 Resource Server support
                .jwt(); // enables JWT-encoded bearer token support

        // add CORS filter
        http.cors();

        // force a non-empty response body for (Unauthorized access) 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // disable CSRF since we are not using Cookies for session tracking
        http.csrf().disable();
    }
}
