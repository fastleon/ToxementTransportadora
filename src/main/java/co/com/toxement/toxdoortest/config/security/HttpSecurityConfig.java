package co.com.toxement.toxdoortest.config.security;

/*
import co.com.toxement.toxdoorTest.util.Permisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
*/

/*
@Configuration
@EnableWebSecurity*/
public class HttpSecurityConfig {

    /*
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

        /*httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider);*/

//                .authorizeRequests()
//                .mvcMatchers(HttpMethod.POST, "/api/v1/auth/authenticate").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/api/v1/auth/public-test-access").permitAll()
//                .mvcMatchers("/error").permitAll()
//
//                .mvcMatchers(HttpMethod.GET, "/api/v1/productos").hasAuthority(Permisos.READ_ALL_PRODUCTOS.name())
//                .mvcMatchers(HttpMethod.POST, "/api/v1/productos").hasAuthority(Permisos.SAVE_ONE_PRODUCTO.name())
//
//                .anyRequest().denyAll();
/*


        return httpSecurity.build();
    }*/

}
