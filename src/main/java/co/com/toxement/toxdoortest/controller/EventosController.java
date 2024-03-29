package co.com.toxement.toxdoortest.controller;

import co.com.toxement.toxdoortest.dto.SolicitudDTO;
import co.com.toxement.toxdoortest.entity.Solicitud;
import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import co.com.toxement.toxdoortest.repository.SolicitudRepository;
import co.com.toxement.toxdoortest.repository.TransportadoraCredencialRepository;
import co.com.toxement.toxdoortest.service.SolicitudService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventosController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    TransportadoraCredencialRepository credencialRepository;

    @PostMapping("/registrarEvento") //@RequestBody @Valid SolicitudDTO solicitudDTO,
    public String registrarEvento(Principal principal, @RequestBody JsonNode json) {

        ObjectMapper objectMapper = new ObjectMapper();
        SolicitudDTO solicitudDTO;

        try {
            System.out.println("json recibido: " + json);
            solicitudDTO = objectMapper.treeToValue(json, SolicitudDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error creando la solicitudDTO";
        }

        String username = principal.getName();
        TransportadoraCredencial credencial = credencialRepository.findByUsuario(username).get();
        Integer idTransportadora = credencial.getTransportadora().getId();

        Optional<String> response = solicitudService.validarSolicitud(solicitudDTO, idTransportadora, json);

        return "accediendo al servicio para registrar eventos" + "Nombre de usuario: " + username +
                " transportadora_id: " + credencial.getTransportadora().getId() +
                " response: " + solicitudDTO.toString() +
                " fecha: " + solicitudDTO.getFecha() +
                " respuestaValidacion: " + response.get();
    }
    /*public String registrarEvento(Principal principal, @RequestBody @Valid SolicitudDTO solicitudDTO) {
        String username = principal.getName();
        TransportadoraCredencial credencial = credencialRepository.findByUsuario(username).get();

        Optional<String> response = solicitudService.validarSolicitud(solicitudDTO, credencial);

        return "accediendo al servicio para registrar eventos" + "Nombre de usuario: " + username +
                " transportadora_id: " + credencial.getTransportadora().getId() +
                " response: " + response.get();
    }*/

}
