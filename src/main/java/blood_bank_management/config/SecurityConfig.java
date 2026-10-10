package blood_bank_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;

        public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
                this.jwtAuthFilter = jwtAuthFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(
                        HttpSecurity http) throws Exception {

                http
                                .csrf(csrf -> csrf.disable())

                                .sessionManagement(session -> session.sessionCreationPolicy(
                                                SessionCreationPolicy.STATELESS))

                                .authorizeHttpRequests(auth -> auth

                                                // Allow browser preflight requests
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                                // Admin login
                                                .requestMatchers("/api/login").permitAll()

                                                // Allow creating an admin account
                                                .requestMatchers(
                                                                HttpMethod.POST,
                                                                "/api/admins")
                                                .permitAll()

                                                // Donor, patient, donation and blood request registration
                                                .requestMatchers(
                                                                HttpMethod.POST,
                                                                "/api/donors",
                                                                "/api/patients",
                                                                "/api/donations",
                                                                "/api/blood-requests")
                                                .permitAll()

                                                // Blood availability
                                                .requestMatchers(
                                                                HttpMethod.GET,
                                                                "/api/inventory",
                                                                "/api/inventory/search/**")
                                                .permitAll()

                                                // Everything else requires login
                                                .anyRequest().authenticated())

                                .addFilterBefore(
                                                jwtAuthFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}