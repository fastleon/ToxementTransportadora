package co.com.toxement.transportadora.service;

import co.com.toxement.bo.Evento;
import co.com.toxement.dto.ResultadoValidacionEventoTransportadoraDTO;
import co.com.toxement.transportadora.config.Constantes;
import co.com.toxement.transportadora.dto.ErrorUsuarioDTO;
import co.com.toxement.transportadora.dto.RespuestaSolicitudDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

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

    @Autowired
    private RespuestaSolicitudDTO rtaUsuario;

    public ResponseEntity<ResultadoValidacionEventoTransportadoraDTO> validarEvento(Evento evento) {

        /* // Llamar al endpoint estadoNombre tipo GET
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + ENDPOINT_VALIDAR_EVENTO)
                .queryParam("idTransportadora", evento.getIdTransportadora()) ...

        String urlRequest = builder.toUriString();*/

        String urlRequest = BASE_URL + ENDPOINT_VALIDAR_EVENTO;

        try {
            ResponseEntity<ResultadoValidacionEventoTransportadoraDTO> responseValidacion = restTemplate.postForEntity(urlRequest, evento, ResultadoValidacionEventoTransportadoraDTO.class);
            if (!responseValidacion.getBody().isValidacionExitosa()) {
                System.out.println("Errores encontradas en validacion");
                responseValidacion.getBody().getErrores().stream()
                        .map(error -> "codigo: " + error.getCode() + ", mensaje" + error.getMessage())
                        .forEach(System.out::println);
            }
            return responseValidacion;

        } catch (ResourceAccessException | HttpStatusCodeException err) {
            ResultadoValidacionEventoTransportadoraDTO errorValidacion = new ResultadoValidacionEventoTransportadoraDTO();
            errorValidacion.setEvento(evento);
            ErrorUsuarioDTO error = (Constantes.ERROR_CONSUMO_ERROR_SERVIDOR);
            errorValidacion.addError(error.getCode(), error.getMessage());
            System.out.println("No hay comunicacion con el servicio de validación: "+ err.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorValidacion);
        }
    }
     /*@POST que se consume:
       public ResultadoValidacionEventoTransportadoraDTO validarEventoTransportadora
            (idTransportadora, nombre, remision, fecha, evidencias)*/



    public Response publicarEvento(Evento evento) {
        String urlRequest = BASE_URL + ENDPOINT_PUBLICAR_EVENTO;

        try {
            String responsePublicacion = restTemplate.postForObject(urlRequest, evento, String.class);
            System.out.println("exito en la publicacion");
            return Response
                    .status(Response.Status.OK.getStatusCode())
                    .entity(responsePublicacion)
                    .build();

        } catch (HttpStatusCodeException err) {
            System.out.println("Falla en la publicacion");
            System.out.println("Error " + err.getStatusCode() + ": " + err.getResponseBodyAsString());
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Falla al publicar solicitud, " + err.getStatusCode() + ": " + err.getResponseBodyAsString())
                    .build();

        }

    }
    /*@POST que se consume:
	public Response publicar(idTransportadora, nombre, remision, fecha, evidencias)
	 */

}



