package co.com.toxement.transportadora.config;

import co.com.toxement.transportadora.dto.ErrorUsuarioDTO;

public class Constantes {
    //ERRORES CODIGO, VALOR
    public static final ErrorUsuarioDTO ERROR_LOGIN_USUARIO_DESHABILITADO =
            new ErrorUsuarioDTO("010", "El usuario aparece deshabilitado en el sistema");
    public static final ErrorUsuarioDTO ERROR_LOGIN_USUARIO_BLOQUEADO =
            new ErrorUsuarioDTO("011", "El usuario aparece bloqueado en el sistema");
    public static final ErrorUsuarioDTO ERROR_LOGIN_CREDENCIALES_ERRONEAS =
            new ErrorUsuarioDTO("012", "Usuario y/o contraseñas erróneas");
    public static final ErrorUsuarioDTO ERROR_LOGIN_PROCESO_AUTENTICACION =
            new ErrorUsuarioDTO("013", "Error en el proceso de autenticación");
    public static final ErrorUsuarioDTO ERROR_LOGIN_JSON =
            new ErrorUsuarioDTO("014", "Campos en json de autenticación contienen errores");
    public static final ErrorUsuarioDTO ERROR_TOKEN_VENCIDO =
            new ErrorUsuarioDTO("101", "El tiempo de validez del token ya finalizó");
    public static final ErrorUsuarioDTO ERROR_TOKEN_MAL_HEADER =
            new ErrorUsuarioDTO("102", "No se puede procesar la información del usuario en el token actual");
    public static final ErrorUsuarioDTO ERROR_TOKEN_DANADO =
            new ErrorUsuarioDTO("103", "Error al procesar el token jwt");
    public static final ErrorUsuarioDTO ERROR_CONSUMO_ERROR_PUBLICACION =
            new ErrorUsuarioDTO("251", "Error al generar el evento en la cola de procesos.\n" +
            "inténtelo nuevamente, si se repite este error consulte a Toxement");
    public static final ErrorUsuarioDTO ERROR_CONSUMO_ERROR_RADICADO =
            new ErrorUsuarioDTO("252", "Error al generar el radicado, la solicitud fue procesada con éxito");
    public static final ErrorUsuarioDTO ERROR_CONSUMO_PARAMETROS_JSON =
            new ErrorUsuarioDTO("253", "Error en la estructura del json recibido o falta algún parámetro.");
    public static final ErrorUsuarioDTO ERROR_CONSUMO_ERROR_SERVIDOR =
            new ErrorUsuarioDTO("254", "No es posible registrar el evento en el sistema, consulte al administrador del sistema.");
}
