package co.com.toxement.toxdoortest.config.security.filter;

import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import co.com.toxement.toxdoortest.repository.TransportadoraCredencialRepository;
import co.com.toxement.toxdoortest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TransportadoraCredencialRepository transportadoraCredencialRepository;

    public JwtAuthenticationFilter(TransportadoraCredencialRepository transportadoraCredencialRepository) {
        this.transportadoraCredencialRepository = transportadoraCredencialRepository;
    }

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //procesar el header recibido desde la solicitud a un endpoint privado (token)
        String authHeader = "";
        try {
            authHeader = request.getHeader("Authorization").trim();
        } catch (NullPointerException nul) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.split(" ")[1];
        String username = jwtService.extractUsername(jwt);

        TransportadoraCredencial credencial = transportadoraCredencialRepository.findByUsuario(username).get();        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, credencial.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        //Seguir con los demas filtros en FilterChain
        filterChain.doFilter(request, response);
    }
}
