package co.com.toxement.toxdoortest.config.security;


import co.com.toxement.toxdoortest.repository.TransportadoraCredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInjector {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private TransportadoraCredencialRepository transportadoraCredencialRepository;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //Provider Manager
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return usuario -> {
            return transportadoraCredencialRepository.findByUsuario(usuario)
                    .orElseThrow(()->new RuntimeException("User not Found"));
        };
    }

//    private final static class CustomUserDetailsService implements UserDetailsService {
//
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            return null;
//        }
//    }*/
}
