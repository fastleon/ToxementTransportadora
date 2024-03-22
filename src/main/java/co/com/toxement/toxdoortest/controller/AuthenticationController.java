package co.com.toxement.toxdoortest.controller;

import co.com.toxement.toxdoortest.dto.AuthencationRequest;
import co.com.toxement.toxdoortest.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthencationRequest authRequest) {
        return ResponseEntity.ok(new AuthenticationResponse("ok"));
    }

    @GetMapping("/public-test-access")
    public String publicTestAcess() {
        return "Acceso al endpoint realizado de forma correcta";
    }

}
