package co.com.toxement.toxdoortest.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.toxdoortest.dto.SolicitudDTO;
import co.com.toxement.toxdoortest.entity.Solicitud;
import co.com.toxement.toxdoortest.entity.Transportadora;
import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SolicitudService {

    public Optional<String> validarSolicitud(SolicitudDTO solicitudDTO, TransportadoraCredencial credencial);

    public Solicitud createSolicitud(Solicitud solicitud);

    public boolean publicarSolicitud(Evento evento);


}
