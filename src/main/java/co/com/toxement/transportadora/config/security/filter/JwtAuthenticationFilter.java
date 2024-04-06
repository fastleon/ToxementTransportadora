package co.com.toxement.transportadora.config.security.filter;

import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.RespuestaSolicitudDTO;
import co.com.toxement.transportadora.dto.SolicitudDTO;
import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import co.com.toxement.transportadora.repository.TransportadoraCredencialRepository;
import co.com.toxement.transportadora.service.JwtService;
import co.com.toxement.transportadora.util.Conversion;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TransportadoraCredencialRepository transportadoraCredencialRepository;

    @Autowired
    private RespuestaSolicitudDTO rtaUsuario;

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

        String username = "";
        try {
            username = jwtService.extractUsername(jwt);
            if (username == null){
                throw new MalformedJwtException("");
            }
        }
        catch (JwtException e) {
            if (e instanceof ExpiredJwtException) {
                rtaUsuario.addError(Constantes.ERROR_TOKEN_VENCIDO, HttpStatus.UNAUTHORIZED);
            } else if (e instanceof MalformedJwtException) {
                rtaUsuario.addError(Constantes.ERROR_TOKEN_MAL_HEADER, HttpStatus.BAD_REQUEST);
            } else {
                rtaUsuario.addError(Constantes.ERROR_TOKEN_DANADO, HttpStatus.BAD_REQUEST);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = Conversion.obtenerSolicitud(request);
            SolicitudDTO solicitudDTO = objectMapper.readValue(json, SolicitudDTO.class);
            rtaUsuario.setSolicitud(solicitudDTO);
            response.setStatus(rtaUsuario.getHttpStatus().value());
            response.setContentType("application/json");
            response.getWriter().write(rtaUsuario.respuestaToJson());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (username != "" && username != null) {
            TransportadoraCredencial credencial = transportadoraCredencialRepository.findByUsuario(username).get();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username, null, credencial.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);

            //Seguir con los demas filtros en FilterChain
            filterChain.doFilter(request, response);
        }
    }

}
