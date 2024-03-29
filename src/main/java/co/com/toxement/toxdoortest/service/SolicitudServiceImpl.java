package co.com.toxement.toxdoortest.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.dto.ResultadoValidacionEventoTransportadoraDTO;
import co.com.toxement.toxdoortest.dto.SolicitudDTO;
import co.com.toxement.toxdoortest.entity.Solicitud;
import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService{
    @Override
    public Optional<String> validarSolicitud(SolicitudDTO solicitudDTO, TransportadoraCredencial credencial) {

        Evento evento = new Evento(
                null,
                credencial.getTransportadora().getId(),
                solicitudDTO.getNumeroDeEntrega(),
                solicitudDTO.getFecha().toString(),
                solicitudDTO.getNombre(),
                solicitudDTO.getEvidencias()
        );

        ResultadoValidacionEventoTransportadoraDTO responseValidacion = validarEvento(evento);
        if (!responseValidacion.isValidacionExitosa()) {
            //TODO: Agregar DTO para respuestas al usuario
            return Optional.of("Error en Validacion");
        }

        Response responsePublicacion = publicarEvento(evento);
        if (responsePublicacion.getStatus() != Response.Status.OK.getStatusCode()) {
            //Codigos 404 o 500
            //TODO: Agregar DTO para respuestas al usuario
            return Optional.of("Error en publicacion");
        }

        //TODO: guardar la solicitud

        //TODO: responder positivamente al usuario con la respuesta

        //ACA +++++++++++++++++++++++++++++++++++++++++++++++++++++



        return Optional.empty();
    }

    private Response publicarEvento(Evento evento) {
        //TODO: llamar al serivio /publicar
        return Response.ok().entity("ok").build();
    }

    private ResultadoValidacionEventoTransportadoraDTO validarEvento(Evento evento) {
        ResultadoValidacionEventoTransportadoraDTO response = new ResultadoValidacionEventoTransportadoraDTO();
        //TODO: llamar al servicio /validarEventoTransportadora
        response.setEvento(evento);
        response.setValidacionExitosa(true);
        return response;
    }


        /*@GET
        @Path("/validarEventoTransportadora")
        @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8" })
        public ResultadoValidacionEventoTransportadoraDTO validarEventoTransportadora(@QueryParam("idTransportadora") Integer idTransportadora, @QueryParam("nombre") String nombre,	@QueryParam("remision") String remision, @QueryParam("fecha") String fecha,  @QueryParam("evidencias") String evidencias) {
            return  serviciosEnviosTransportadora.validarEventoTransportadora(idTransportadora, nombre, remision, fecha, evidencias);
        }*/



    @Override
    public Solicitud createSolicitud(Solicitud solicitud) {
        return null;
    }

    @Override
    public boolean publicarSolicitud(Evento evento) {
        return false;
    }

    /*     @GET
	@Path("/publicar")
	@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Response publicar(@QueryParam("idTransportadora") Integer idTransportadora, @QueryParam("nombre") String nombre,	@QueryParam("remision") String remision, @QueryParam("fecha") String fecha, @QueryParam("evidencias") String evidencias) {
		String message="Publicando en la cola EntregasQueue con idTransportadora "+idTransportadora+" y nombre "+nombre;
		serviciosEnviosTransportadora.publicarEnLaCola(idTransportadora, nombre, remision, fecha, evidencias);
		return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.APPLICATION_JSON).build();
	}     */

}
