package co.com.toxement.toxdoortest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitudDTO {

    /*Numero de la entrega*/
    @NotNull
    private String numeroDeEntrega;

    /*fecha del evento*/
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date fecha;

    /*nombre del evento*/
    @NotNull
    private String nombre;

    /*ubicacion de las evidencias*/
    private Set<String> evidencias;

    @JsonCreator
    public static SolicitudDTO create(
            @JsonProperty("evidencias") Set<String> evidencias,
            @JsonProperty("numeroDeEntrega") String numeroDeEntrega,
            @JsonProperty("fecha") Date fecha,
            @JsonProperty("nombre") String nombre
    ) {
        return new SolicitudDTO(numeroDeEntrega, fecha, nombre, evidencias);
    }

}
