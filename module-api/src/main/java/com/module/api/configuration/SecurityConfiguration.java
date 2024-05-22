package com.module.api.configuration;

import com.module.api.authentication.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    // TODO : 나중에 코드 한 번 정리하기...

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable) //http의 기본 인증 방식 disable
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests((request) ->
                        request.requestMatchers(
                                        "**"
                                )
                                .permitAll()
                )
                .authorizeHttpRequests((request) ->
                        request.anyRequest().authenticated()
                );


        return http.build();

    }
}
