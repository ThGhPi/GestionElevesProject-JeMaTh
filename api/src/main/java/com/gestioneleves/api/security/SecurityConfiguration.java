package com.gestioneleves.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private RequestMatcher childEndPoints(String... methods) {
        return new OrRequestMatcher(
            Arrays.stream(methods)
                .flatMap(method -> Stream.of
                    (
                        new AntPathRequestMatcher("/api/evaluations/child/**", method),
                        new AntPathRequestMatcher("/api/class-groups/child/**", method),
                        new AntPathRequestMatcher("/api/evaluations/child/**", method),
                        new AntPathRequestMatcher("/api/regitrations/child/**", method),
                        new AntPathRequestMatcher("/api/school-report-lines/child/**" + method),
                        new AntPathRequestMatcher("/api/school-reports/child/**" + method),
                        new AntPathRequestMatcher("/api/students/child/**", method),
                        new AntPathRequestMatcher("/api/teahings/child/**", method),
                        new AntPathRequestMatcher("/api/users/child/**", method)
                    )
                ).toArray(RequestMatcher[]::new)
        );
    }
    private RequestMatcher studentEndPoints(String... methods) {
        return new OrRequestMatcher(
            Arrays.stream(methods)
                .flatMap(method -> Stream.of
                    (
                        new AntPathRequestMatcher("/api/class-group/student/**", method),
                        new AntPathRequestMatcher("/api/evaluations/student/**", method),
                        new AntPathRequestMatcher("/api/regitrations/student/**", method),
                        new AntPathRequestMatcher("/api/school-report-lines/student/**", method),
                        new AntPathRequestMatcher("/api/school-reports/student/**" + method),
                        new AntPathRequestMatcher("/api/teahings/student/**", method),
                        new AntPathRequestMatcher("/api/users/student/**", method)
                    )
                ).toArray(RequestMatcher[]::new)
        );
    }
    
    private RequestMatcher evalAndSchoolReportStudentEndPoints(String... methods) {
        return new OrRequestMatcher(
            Arrays.stream(methods)
                .flatMap(method -> Stream.of
                    (
                        new AntPathRequestMatcher("/api/evaluations/student/**", method),
                        new AntPathRequestMatcher("/api/school-report-lines/student/**", method),
                        new AntPathRequestMatcher("/api/school-reports/student/**" + method)
                    )
                ).toArray(RequestMatcher[]::new)
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                .disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/login").permitAll()

                        .requestMatchers("/api/users").hasRole("ADMIN")

                        .requestMatchers("/api/evaluations/class-group/**").hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers("/api/evaluations/teaching/**").hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers(childEndPoints("GET")).hasAnyRole("LEGAL_GUARDIAN", "TEACHER")
                        
                        .requestMatchers(studentEndPoints("GET")).hasRole("TEACHER")
                        
                        .requestMatchers(evalAndSchoolReportStudentEndPoints("POST", "PUT", "DELETE")).hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/class-groups/**").hasAnyRole("TEACHER", "ADMIN")
                        
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                        )
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8081"));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
