package co.com.toxement.toxdoortest.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.dto.ResultadoValidacionEventoTransportadoraDTO;
import co.com.toxement.toxdoortest.dto.SolicitudDTO;
import co.com.toxement.toxdoortest.entity.Solicitud;
import co.com.toxement.toxdoortest.repository.SolicitudRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService{

    @Autowired
    SolicitudRepository solicitudRepository;

    @Autowired
    ExternalApiService externalApiService;

    @Override
    public Optional<String> validarSolicitud(SolicitudDTO solicitudDTO, Integer idTransportadora, JsonNode json) {

        //Validar Solicitud
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Iniciando evento: " +
                solicitudDTO.getNombre() + ", " +
                solicitudDTO.getFecha() + ", " +
                solicitudDTO.getNumeroDeEntrega() + ", ");
        Evento evento = new Evento();
        evento.setIdTransportadora(idTransportadora);
        evento.setNumeroDeEntrega( solicitudDTO.getNumeroDeEntrega());
        evento.setFecha(sdf.format(solicitudDTO.getFecha()));
        evento.setNombre(solicitudDTO.getNombre());
        evento.setEvidencias(solicitudDTO.getEvidencias());

        ResultadoValidacionEventoTransportadoraDTO responseValidacion = externalApiService.validarEvento(evento);
        if (!responseValidacion.isValidacionExitosa()) {
            System.out.println("Error en la validacion");
            //TODO: Agregar DTO para respuestas al usuario
            //return Optional.of("Error en Validacion");

        }

        Response responsePublicacion = externalApiService.publicarEvento(evento);
        if (responsePublicacion.getStatus() != Response.Status.OK.getStatusCode()) {
            System.out.println("Error en la publicacion");
            //return Optional.of("Error en la publicacion, status: " + responsePublicacion.getStatus());
        }

        //Guardar Solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setFechaCreacion(new Date());
        solicitud.setTransportadoraId(idTransportadora);
        solicitud.setNumeroEntrega(solicitudDTO.getNumeroDeEntrega());
        solicitud.setJsonData(json.toString());
        Solicitud solicitudGuardada = solicitudRepository.save(solicitud);
        if (solicitudGuardada.getId() != null) {
            System.out.println("Solicitud guardada ID: " + solicitudGuardada.getId());
        } else {
            return Optional.of("Error al guardar la solicitud en db");
        }

        //TODOS LOS PROCESOS FUERON EFECTUADOS POSITIVAMENTE
        return Optional.of("la Solicitud fue generada exitosamente");

    }




}
