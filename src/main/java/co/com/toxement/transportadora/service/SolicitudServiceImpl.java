package co.com.toxement.transportadora.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.dto.ErrorDTO;
import co.com.toxement.dto.ResultadoValidacionEventoTransportadoraDTO;
import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.RespuestaSolicitudDTO;
import co.com.toxement.transportadora.dto.SolicitudDTO;
import co.com.toxement.transportadora.entity.Solicitud;
import co.com.toxement.transportadora.repository.SolicitudRepository;
import co.com.toxement.transportadora.util.Conversion;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Date;

@Service
public class SolicitudServiceImpl implements SolicitudService{

    @Autowired
    SolicitudRepository solicitudRepository;

    @Autowired
    ExternalApiService externalApiService;

    @Override
    public ResponseEntity validarSolicitud(SolicitudDTO solicitudDTO, Integer idTransportadora, JsonNode json) {

        //Validar Solicitud
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Evento evento = new Evento();
        evento.setIdTransportadora(idTransportadora);
        evento.setNumeroDeEntrega(solicitudDTO.getNumeroDeEntrega());
        evento.setFecha(solicitudDTO.getFecha()); //(sdf.format(solicitudDTO.getFecha())));
        evento.setNombre(solicitudDTO.getNombreEvento());
        evento.setEvidencias(solicitudDTO.getEvidencias());

        //Visualizar Json a enviar a Intranet
        try {
            System.out.println( Conversion.convertirAJson(evento) );
        } catch (Exception e) {
            e.printStackTrace();
        }

        RespuestaSolicitudDTO respuestaSolicitud = new RespuestaSolicitudDTO();
        respuestaSolicitud.setSolicitud(solicitudDTO);

        ResponseEntity<ResultadoValidacionEventoTransportadoraDTO> responseValidacion = externalApiService.validarEvento(evento);
        if (responseValidacion.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            respuestaSolicitud.addError(
                    Constantes.ERROR_CONSUMO_ERROR_SERVIDOR.getCode(),
                    Constantes.ERROR_CONSUMO_ERROR_SERVIDOR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaSolicitud);
        }
        if (!responseValidacion.getBody().isValidacionExitosa()) {
                for (ErrorDTO errorDTO : responseValidacion.getBody().getErrores()) {
                    String newCode = "2" + errorDTO.getCode().substring(1);
                    respuestaSolicitud.addError(newCode, errorDTO.getMessage(), HttpStatus.BAD_REQUEST);
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaSolicitud);
        }

        //TODO: Habilitar esta sección cuando Norbert termine de implementarlo
        /*Response responsePublicacion = externalApiService.publicarEvento(evento);
        if (responsePublicacion.getStatus() != Response.Status.OK.getStatusCode()) {
            System.out.println("Error en la publicacion");
            respuestaSolicitud.addError(Constantes.ERROR_CONSUMO_ERROR_PUBLICACION, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        if ( respuestaSolicitud.isExitosa() ) {
            //Guardar Solicitud
            Solicitud solicitud = new Solicitud();
            solicitud.setFechaCreacion(new Date());
            solicitud.setTransportadoraId(idTransportadora);
            solicitud.setNumeroEntrega(solicitudDTO.getNumeroDeEntrega());
            solicitud.setJsonData(json.toString());
            Solicitud solicitudGuardada = solicitudRepository.save(solicitud);
            if (solicitudGuardada.getId() != null) {
                System.out.println("Solicitud guardada ID: " + solicitudGuardada.getId());
                respuestaSolicitud.setRadicado(solicitudGuardada.getId().toString());
            } else {
                respuestaSolicitud.addError(Constantes.ERROR_CONSUMO_ERROR_RADICADO, HttpStatus.ACCEPTED);
            }
        }

        //TODOS LOS PROCESOS FUERON EFECTUADOS POSITIVAMENTE
        return ResponseEntity.status(respuestaSolicitud.getHttpStatus()).body(respuestaSolicitud);

    }


}
