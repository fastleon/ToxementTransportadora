package co.com.toxement.transportadora.controller;

import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.AuthencationRequest;
import co.com.toxement.transportadora.dto.AuthenticationResponse;
import co.com.toxement.transportadora.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthencationRequest authRequest) {
        if (authRequest == null) { //Al activar JWT no pasa por este filtro
            AuthenticationResponse noContent = new AuthenticationResponse(
                    "error",
                    Constantes.ERROR_LOGIN_JSON,
                    false
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(noContent);
        }

        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        if ( jwtDto.isExitosa() ) {
            return ResponseEntity.ok(jwtDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jwtDto);
        }
    }

    /*@GetMapping("/public-test-access")
    public String publicTestAcess() {
        return "Acceso al endpoint realizado de forma correcta";
    }*/

}
