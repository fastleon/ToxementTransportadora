package co.com.toxement.toxdoortest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SolicitudDTO {

    /*Numero de la entrega*/
    @NotNull
    private String numeroDeEntrega;

    /*fecha del evento*/
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime fecha;

    /*nombre del evento*/
    @NotNull
    private String nombre;

    /*ubicacion de las evidencias*/
    private Set<String> evidencias;

}
