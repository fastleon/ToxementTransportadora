package co.com.toxement.transportadora.dto;

import co.com.toxement.transportadora.util.Conversion;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@ToString
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RespuestaSolicitudDTO {
    private List<ErrorUsuarioDTO> errores = new ArrayList<>();
    private boolean exitosa = true;
    private HttpStatus httpStatus = HttpStatus.OK;
    private SolicitudDTO solicitud;
    private String radicado;

    public void addError(String codigo, String mensaje, HttpStatus httpStatus) {
        ErrorUsuarioDTO error = new ErrorUsuarioDTO();
        error.setCode(codigo);
        error.setMessage(mensaje);
        this.httpStatus = httpStatus;
        this.setExitosa(false);
        this.errores.add(error);
    }
    public void addError(ErrorUsuarioDTO error, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.setExitosa(false);
        this.errores.add(error);
    }

    public ResponseEntity<List<ErrorUsuarioDTO>> responderUsuario() {
        System.out.println("entrando a entregar respuesta");
        return ResponseEntity
                .status(this.httpStatus)
                .body(errores);
    }

    public String respuestaToJson() {

        try {
            return Conversion.convertirAJson(this);
        }
        catch(JsonProcessingException e) {
            return "Error generando Json de respuesta";
        }

    }


}
