package co.com.toxement.transportadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        // Crear una respuesta de error con código 500 y el mensaje de la excepción
        ErrorMessage errorMessage = new ErrorMessage("Error interno", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Crear una respuesta de error con código 400 y el mensaje de la excepción
        ErrorMessage errorMessage = new ErrorMessage("Argumento inválido", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // Otros manejadores de excepciones aquí...

    // Clase para representar el mensaje de error
    public static class ErrorMessage {
        private String error;
        private String message;

        public ErrorMessage(String error, String message) {
            this.error = error;
            this.message = message;
        }

    }
}