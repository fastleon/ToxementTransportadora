package co.com.toxement.transportadora.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {

    private String jwt;
    private ErrorUsuarioDTO error;
    private boolean exitosa = true;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    public void addError(String code, String message){
        this.error = new ErrorUsuarioDTO(code, message);
        this.exitosa = false;
        this.jwt = "";
    }
    public void addError(ErrorUsuarioDTO error){
        this.error = error;
        this.exitosa = false;
        this.jwt = "";
    }

}
