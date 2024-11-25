package app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((requests) -> requests
			//event permissions
			.requestMatchers("/api/rdp/event/save").hasAnyAuthority("MODERADOR", "VETERANO")
			.requestMatchers("/api/rdp/event/update/{id}").hasAnyAuthority("MODERADOR", "VETERANO")
			.requestMatchers("/api/rdp/event/delete/{id}").hasAnyAuthority("MODERADOR", "VETERANO")
			
			//guides permissions
			.requestMatchers("/api/rdp/guide/save").hasAnyAuthority("MODERADOR", "VETERANO")
			.requestMatchers("/api/rdp/guide/update/{id}").hasAnyAuthority("MODERADOR", "VETERANO")
			.requestMatchers("/api/rdp/guide/delete/{id}").hasAnyAuthority("MODERADOR", "VETERANO")
			
			//game permissions
			.requestMatchers("/api/rdp/game/save").hasAuthority("MODERADOR")
			.requestMatchers("/api/rdp/game/update/{id}").hasAuthority("MODERADOR")
			.requestMatchers("/api/rdp/game/delete/{id}").hasAuthority("MODERADOR")
			
			//fighter permissions
			.requestMatchers("/api/rdp/fighter/save").hasAuthority("MODERADOR")
			.requestMatchers("/api/rdp/fighter/update/{id}").hasAuthority("MODERADOR")
			.requestMatchers("/api/rdp/fighter/delete/{id}").hasAuthority("MODERADOR")
			
			//login permissions
			.requestMatchers("/api/rdp/login/logar").permitAll()
			.requestMatchers("/api/rdp/user/save").permitAll()
			
			//view permissions
			.requestMatchers("/api/rdp/game/findAll").permitAll()
			.requestMatchers("/api/rdp/event/findAll").permitAll()
			.requestMatchers("/api/rdp/guide/findAll").permitAll()
			.requestMatchers("/api/rdp/user/findAll").permitAll()
			.requestMatchers("/api/rdp/fighter/findAll").permitAll()
			.requestMatchers("/api/rdp/event/findLast5").permitAll()
			.requestMatchers("/api/rdp/fighter/findByNomeJogo/{Gamenome}").permitAll()
			
			.anyRequest().authenticated())
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	return http.build();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	config.addAllowedOrigin("http://localhost:4200");
	config.setAllowedHeaders(
		Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
	config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
		HttpMethod.DELETE.name()));
	config.setMaxAge(3600L);
	source.registerCorsConfiguration("/**", config);
	FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	bean.setOrder(-102);
	return bean;
    }

}