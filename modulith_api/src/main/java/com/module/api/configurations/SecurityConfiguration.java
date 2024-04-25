package com.module.api.configurations;

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


        // TODO : 로그아웃 기능 구현의 흔적,,
//                .logout(httpSecurityLogoutConfigurer ->
//                        .logoutUrl("LOGOUT_URL")
//                        .logoutSuccessUrl("LOGOUT_SUCCES_URL")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JESSIONID")
//                        .permitAll());

        // TODO : tokenValiditySeconds 값은 어떻게 할지...?
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .rememberMe()
//                .tokenValiditySeconds(60 * 60)
//                .userDetailsService(userDetailsService);

        return http.build();

    }
}
