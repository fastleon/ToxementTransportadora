package co.com.toxement.transportadora.test;

import co.com.toxement.transportadora.entity.Transportadora;
import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import co.com.toxement.transportadora.service.TransportadoraCredencialService;
import co.com.toxement.transportadora.service.TransportadoraCredencialServiceImpl;
import co.com.toxement.transportadora.service.TransportadoraService;
import co.com.toxement.transportadora.service.TransportadoraServiceImpl;
import co.com.toxement.transportadora.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class TransportadoraCredencialTest {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static void testGuardarYBuscarTransportadora() {

        TransportadoraService transportadoraService = new TransportadoraServiceImpl();
        TransportadoraCredencialService credencialService = new TransportadoraCredencialServiceImpl();

        //GUARDAR PRIMER FAKE TRANSPORTADORA
        Transportadora transportadora = new Transportadora();
        transportadora.setNombre("TransTest1");
        transportadora.setStatus(true);
        transportadora.setFechaCreacion(new Date());
        transportadora.setUsuarioCreacion(1L);

        Integer id1 = 0;
        Integer id2 = 0;
        Transportadora transportadoraSaved = transportadoraService.createTransportadora(transportadora);
        if (transportadoraSaved.getNombre().equals(transportadora.getNombre())) {
            System.out.println("Transportadora TransTest1 Creada");
            id1 = transportadoraSaved.getId();
        }

        //GUARDAR segunda FAKE TRANSPORTADORA
        transportadora = new Transportadora();
        transportadora.setNombre("TransTest2");
        transportadora.setStatus(true);
        transportadora.setFechaCreacion(new Date());
        transportadora.setUsuarioCreacion(1L);

        transportadoraSaved = transportadoraService.createTransportadora(transportadora);

        if (transportadoraSaved.getNombre().equals(transportadora.getNombre())) {
            System.out.println("Transportadora TransTest2 Creada");
            id2 = transportadoraSaved.getId();
        }

        TransportadoraCredencial credencialSaved = new TransportadoraCredencial();

        //CREAR CREDENCIAL TEST CUSTOMER
        String passEnc = passwordEncoder.encode("password1");
        Transportadora transportadora1 = transportadoraService.getTransportadoraById(id1);

        if (id1 != 0) {
            TransportadoraCredencial credencial = new TransportadoraCredencial();
            credencial.setUsuario("TransUser");
            credencial.setPassword(passEnc);
            credencial.setTransportadora(transportadora1);
            credencial.setStatus(true);
            credencial.setRole(Role.CUSTOMER);
            credencial.setFechaCreacion(new Date());

            credencialSaved = credencialService.createCredencial(credencial);
            if (credencialSaved.getUsuario().equals(credencial.getUsuario())) {
                System.out.println("Credenciales para CUSTOMER guardadas exitosamente");
            }
        }

        //CREAR CREDENCIAL TEST ADMINISTRATOR
        passEnc = passwordEncoder.encode("password2");
        Transportadora transportadora2 = transportadoraService.getTransportadoraById(id2);

        if (id2 != 0) {
            TransportadoraCredencial credencial2 = new TransportadoraCredencial();
            credencial2.setUsuario("TransAdmin");
            credencial2.setPassword(passEnc);
            credencial2.setTransportadora(transportadora2);
            credencial2.setStatus(true);
            credencial2.setRole(Role.ADMINISTRATOR);
            credencial2.setFechaCreacion(new Date());

            credencialSaved = credencialService.createCredencial(credencial2);
            if (credencialSaved.getUsuario().equals(credencial2.getUsuario())) {
                System.out.println("Credenciales para ADMINSITRATOR guardadas exitosamente");
            }
        }
    }

}
