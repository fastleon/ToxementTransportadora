package co.com.toxement.transportadora.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitudDTO {

    /*Numero de la entrega*/
    @NotNull
    @JsonProperty("numeroDeEntrega")
    private String numeroDeEntrega;

    /*fecha del evento*/
    //@PastOrPresent
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @JsonProperty("fecha")
    private String fecha;

    /*nombre del evento*/
    @NotNull
    @JsonProperty("nombre")
    private String nombreEvento;

    /*ubicacion de las evidencias*/
    @JsonProperty("evidencias")
    private Set<String> evidencias;

    @JsonCreator
    public SolicitudDTO create(
            @JsonProperty("evidencias") Set<String> evidencias,
            @JsonProperty("numeroDeEntrega") String numeroDeEntrega,
            @JsonProperty("fecha") String fecha,
            @JsonProperty("nombreEvento") String nombreEvento
    ) {
        return new SolicitudDTO(numeroDeEntrega, fecha, nombreEvento, evidencias);
    }

    public boolean anyNull() {
        if (this.numeroDeEntrega == null || this.fecha == null || this.nombreEvento == null || this.evidencias == null) {
            return true;
        } else {
            return false;
        }
    }

}
