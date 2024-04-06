package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.dto.SolicitudDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SolicitudService {

    public ResponseEntity validarSolicitud(SolicitudDTO solicitudDTO, Integer idTransportadora, JsonNode json);

}
