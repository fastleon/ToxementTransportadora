package co.com.toxement.toxdoortest.service;

import co.com.toxement.toxdoortest.dto.SolicitudDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SolicitudService {

    public Optional<String> validarSolicitud(SolicitudDTO solicitudDTO, Integer idTransportadora, JsonNode json);

}
