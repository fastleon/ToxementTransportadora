package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.AuthencationRequest;
import co.com.toxement.transportadora.dto.AuthenticationResponse;
import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import co.com.toxement.transportadora.repository.TransportadoraCredencialRepository;
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

        AuthenticationResponse authResponse = new AuthenticationResponse();
        try {
            if (authRequest.getUsername() == null || authRequest.getPassword() == null){
                authResponse.addError(Constantes.ERROR_LOGIN_JSON);
            } else {
                authenticationManager.authenticate(authToken);
            }

        } catch (DisabledException dis) {
            System.out.println("Usuario se encuentra deshabilitado");
            authResponse.addError(Constantes.ERROR_LOGIN_USUARIO_DESHABILITADO);

        } catch (LockedException loc) {
            System.out.println("Usuario se encuentra bloqueado");
            authResponse.addError(Constantes.ERROR_LOGIN_USUARIO_BLOQUEADO);

        } catch (InternalAuthenticationServiceException | BadCredentialsException bad) {
            System.out.println("Usuario o contraseña equivocada");
            authResponse.addError(Constantes.ERROR_LOGIN_CREDENCIALES_ERRONEAS);

        } catch (Exception e) {
            System.out.println("Error en el autentication manager " + e.getMessage());
            authResponse.addError(Constantes.ERROR_LOGIN_PROCESO_AUTENTICACION); //En teoria no debería efectuarse
            e.printStackTrace();
        } finally {
            if (!authResponse.isExitosa()) {
                return authResponse;
            }
        }

        try {
            TransportadoraCredencial credencial = transportadoraCredencialRepository.findByUsuario(authRequest.getUsername()).get();
            authResponse.setJwt( jwtService.generateToken(credencial, generateExtraClaims(credencial)) );
            System.out.println("credenciales ok");

        } catch (NoSuchElementException e) {
            System.out.println("Usuario no encontrado");
            authResponse.addError(Constantes.ERROR_LOGIN_CREDENCIALES_ERRONEAS);

        } catch (Exception gen) {
            System.out.println("Error Logueo");
            authResponse.addError(Constantes.ERROR_LOGIN_PROCESO_AUTENTICACION);
        } finally {
            return authResponse;
        }

    }

    private Map<String, Object> generateExtraClaims(TransportadoraCredencial credencial) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", credencial.getUsuario());
        extraClaims.put("rol", credencial.getRole().name());

        return extraClaims;

    }


}

