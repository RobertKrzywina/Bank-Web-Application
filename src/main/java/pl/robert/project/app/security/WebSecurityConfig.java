package pl.robert.project.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register", "/login").not().authenticated()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/user-panel").authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login")
                .usernameParameter("login")
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
