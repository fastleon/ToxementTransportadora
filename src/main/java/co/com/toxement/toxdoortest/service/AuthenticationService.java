package co.com.toxement.toxdoortest.service;

import co.com.toxement.toxdoortest.dto.AuthencationRequest;
import co.com.toxement.toxdoortest.dto.AuthenticationResponse;
import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import co.com.toxement.toxdoortest.repository.TransportadoraCredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TransportadoraCredencialRepository transportadoraCredencialRepository;

    public AuthenticationResponse login(AuthencationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        try {
            authenticationManager.authenticate(authToken);

        } catch (DisabledException dis) {
            System.out.println("Usuario se encuentra deshabilitado");
            return new AuthenticationResponse("EL USUARIO APARECE DESHABILITADO EN EL SISTEMA");

        } catch (LockedException loc) {
            System.out.println("Usuario se encuentra bloqueado");
            return new AuthenticationResponse("EL USUARIO APARECE BLOQUEADO EN EL SISTEMA");

        } catch (BadCredentialsException bad) {
            System.out.println("Usuario o contraseña equivocada");
            return new AuthenticationResponse("USUARIO Y/O CONTRASEÑA ERRONEAS");

        }catch (Exception e) {
            System.out.println("Error en el autentication manager "+e.getMessage());
            return new AuthenticationResponse("ERROR EN EL PROCESO DE AUTENTICACION"); //En teoria no debería efectuarse
        }

        try {
            TransportadoraCredencial credencial = transportadoraCredencialRepository.findByUsuario(authRequest.getUsername()).get();
            String jwt = jwtService.generateToken(credencial, generateExtraClaims(credencial));
            System.out.println("credenciales ok");
            return new AuthenticationResponse(jwt);

        } catch (NoSuchElementException e) {
            System.out.println("usuario no encontrado");
            return new AuthenticationResponse("Usuario no encontrado en el sistema");

        } catch (Exception gen) {
            System.out.println("Error Logueo");
            return new AuthenticationResponse("Error en el proceso de logueo");
        }

    }

    private Map<String, Object> generateExtraClaims(TransportadoraCredencial credencial) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", credencial.getUsuario());
        extraClaims.put("rol", credencial.getRole().name());


        return extraClaims;

    }


}

