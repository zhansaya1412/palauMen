package kz.batyrbek.palaumen.palaumen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/forbidden");

        http.formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/auth")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signin?error")
                .usernameParameter("user_email")
                .passwordParameter("user_password");

        http.logout()
                .logoutUrl("/exit")
                .logoutSuccessUrl("/signin");
        http.csrf().disable();

        return http.build();
    }
}
