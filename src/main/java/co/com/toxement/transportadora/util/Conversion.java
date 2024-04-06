package co.com.toxement.transportadora.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class Conversion {

    @Value("${date.format}")
    private static String dateFormat;

    public static String obtenerSolicitud(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    public static Date stringToDate(String dateString) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error convirtiendo cadena a fecha");
            throw new RuntimeException();
        }

    }

    public static <T> String convertirAJson(T objeto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(objeto);
    }

}
