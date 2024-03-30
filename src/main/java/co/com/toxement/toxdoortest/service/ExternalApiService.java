package co.com.toxement.toxdoortest.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.dto.ResultadoValidacionEventoTransportadoraDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.Response;
import java.util.Optional;

@Service
public class ExternalApiService {

    @Value("${external-api-base-url}")
    private String BASE_URL; // = "http://10.10.10.6:8080/ToxementIntranetRestServices/TMS";
    @Value("${external-api-validar-endpoint}")
    private String ENDPOINT_VALIDAR_EVENTO; // = "/validarEventoTransportadora";
    @Value("${external-api-publicar-endpoint}")
    private String ENDPOINT_PUBLICAR_EVENTO; // = "/validarEventoTransportadora";

    @Autowired
    private RestTemplate restTemplate;

    public ResultadoValidacionEventoTransportadoraDTO validarEvento(Evento evento) {

        /* // Llamar al endpoint estadoNombre tipo GET
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + ENDPOINT_VALIDAR_EVENTO)
                .queryParam("idTransportadora", evento.getIdTransportadora())
                .queryParam("nombre", evento.getNombre())
                .queryParam("remision", evento.getNumeroDeEntrega())
                .queryParam("fecha", evento.getFecha())
                .queryParam("evidencias", evento.getEvidencias());
        String urlRequest = builder.toUriString();
        System.out.println("Enviando consulta: " + urlRequest);*/

        String urlRequest = BASE_URL + ENDPOINT_PUBLICAR_EVENTO;

        try {
            ResultadoValidacionEventoTransportadoraDTO responseValidacion = restTemplate.postForObject(urlRequest, evento, ResultadoValidacionEventoTransportadoraDTO.class);
            return responseValidacion;

        } catch (HttpStatusCodeException err) {
            ResultadoValidacionEventoTransportadoraDTO errorValidacion = new ResultadoValidacionEventoTransportadoraDTO();
            errorValidacion.setEvento(evento);
            errorValidacion.addError("ERROR_SERVER: " + err.getStatusCode() , err.getResponseBodyAsString());
            return errorValidacion;
        }
    }
     /*@POST
        public ResultadoValidacionEventoTransportadoraDTO validarEventoTransportadora
            (idTransportadora, nombre, remision, fecha, evidencias)*/



    public Response publicarEvento(Evento evento) {
        String urlRequest = BASE_URL + ENDPOINT_PUBLICAR_EVENTO;

        try {
            String responsePublicacion = restTemplate.postForObject(urlRequest, evento, String.class);
            return Response
                    .status(Response.Status.OK.getStatusCode())
                    .entity(responsePublicacion)
                    .build();

        } catch (HttpStatusCodeException err) {
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Falla al publicar solicitud, " + err.getStatusCode() + ": " + err.getResponseBodyAsString())
                    .build();

        }

    }
    /*@POST
	public Response publicar(idTransportadora, nombre, remision, fecha, evidencias)
	 */

}



