package co.com.toxement.transportadora.controller;

import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.RespuestaSolicitudDTO;
import co.com.toxement.transportadora.dto.SolicitudDTO;
import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import co.com.toxement.transportadora.repository.TransportadoraCredencialRepository;
import co.com.toxement.transportadora.service.SolicitudService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class EventosController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    TransportadoraCredencialRepository credencialRepository;

    @PostMapping("/registrarEvento") //@RequestBody @Valid SolicitudDTO solicitudDTO,
    public ResponseEntity<RespuestaSolicitudDTO> registrarEvento(Principal principal, @RequestBody JsonNode json) {

        ObjectMapper objectMapper = new ObjectMapper();
        SolicitudDTO solicitudDTO;

        try {
            System.out.println("json recibido: " + json);
            solicitudDTO = objectMapper.treeToValue(json, SolicitudDTO.class);
            if (solicitudDTO.anyNull()) {
                throw new RuntimeException("falta algun dato en la consulta");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            RespuestaSolicitudDTO respuesta = new RespuestaSolicitudDTO();
            respuesta.addError(Constantes.ERROR_CONSUMO_PARAMETROS_JSON, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        String username = principal.getName();
        TransportadoraCredencial credencial = credencialRepository.findByUsuario(username).get();
        Integer idTransportadora = credencial.getTransportadora().getId();

        ResponseEntity<RespuestaSolicitudDTO> respuestaSolicitud = solicitudService.validarSolicitud(solicitudDTO, idTransportadora, json);

        return respuestaSolicitud;

    }


}
