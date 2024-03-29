package co.com.toxement.toxdoortest.config.security;

import co.com.toxement.toxdoortest.config.security.filter.JwtAuthenticationFilter;
import co.com.toxement.toxdoortest.util.Permisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/auth/public-test-access").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/productos").hasAuthority(Permisos.READ_ALL_PRODUCTOS.name())
                .antMatchers(HttpMethod.POST, "/api/v1/productos").hasAuthority(Permisos.SAVE_ONE_PRODUCTO.name())
                .antMatchers(HttpMethod.POST, "/api/v1/eventos/**").hasAuthority(Permisos.READ_ALL_PRODUCTOS.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

        /*httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/api/v1/auth/authenticate").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/auth/public-test-access").permitAll()
                .mvcMatchers("/error").permitAll()

                .mvcMatchers(HttpMethod.GET, "/api/v1/productos").hasAuthority(Permisos.READ_ALL_PRODUCTOS.name())
                .mvcMatchers(HttpMethod.POST, "/api/v1/productos").hasAuthority(Permisos.SAVE_ONE_PRODUCTO.name())

                .anyRequest().denyAll();

        return httpSecurity.build();
    }*/

}
