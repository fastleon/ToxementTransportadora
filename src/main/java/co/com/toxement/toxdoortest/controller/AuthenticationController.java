package co.com.toxement.toxdoortest.controller;

import co.com.toxement.toxdoortest.dto.AuthencationRequest;
import co.com.toxement.toxdoortest.dto.AuthenticationResponse;
import co.com.toxement.toxdoortest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthencationRequest authRequest) {

        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
        //return ResponseEntity.ok(new AuthenticationResponse("OK"));
    }

    @GetMapping("/public-test-access")
    public String publicTestAcess() {
        return "Acceso al endpoint realizado de forma correcta";
    }

}
