package co.com.toxement.transportadora.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthencationRequest {

    private String username;
    private String password;

}
