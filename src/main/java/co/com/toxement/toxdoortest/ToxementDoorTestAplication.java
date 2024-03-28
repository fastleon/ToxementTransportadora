package co.com.toxement.toxdoortest;

import co.com.toxement.toxdoortest.entity.Producto;
import co.com.toxement.toxdoortest.entity.Transportadora;
import co.com.toxement.toxdoortest.entity.TransportadoraCredencial;
import co.com.toxement.toxdoortest.repository.ProductoRepository;
import co.com.toxement.toxdoortest.repository.TransportadoraRepository;
import co.com.toxement.toxdoortest.service.TransportadoraCredencialService;
import co.com.toxement.toxdoortest.service.TransportadoraService;
import co.com.toxement.toxdoortest.service.TransportadoraServiceImpl;
import co.com.toxement.toxdoortest.test.TransportadoraCredencialTest;
import co.com.toxement.toxdoortest.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "co.com.toxement.toxdoorTest")
public class ToxementDoorTestAplication {

	@Autowired
	static
	ProductoRepository repository;
	@Autowired
	static
	Producto producto;

	public static void main(String[] args) {
		SpringApplication.run(ToxementDoorTestAplication.class, args);

	}

	//Poblar la tabla productos con unos basicos
	/*@Component
	public class DataLoader implements CommandLineRunner {
		@Autowired
		private ProductoRepository productoRepository;
		@Override
		public void run(String... args) throws Exception {
			// Crea algunos productos
			Producto producto2 = new Producto(null, "Producto 2", new BigDecimal("20.00"));
			Producto producto3 = new Producto(null, "Producto 3", new BigDecimal("30.00"));
			Producto producto4 = new Producto(null, "Producto 4", new BigDecimal("40.00"));
			Producto producto5 = new Producto(null, "Producto 5", new BigDecimal("50.00"));
			Producto producto6 = new Producto(null, "Producto 6", new BigDecimal("60.00"));
			Producto producto7 = new Producto(null, "Producto 7", new BigDecimal("70.00"));
			Producto producto8 = new Producto(null, "Producto 8", new BigDecimal("80.00"));
			Producto producto9 = new Producto(null, "Producto 9", new BigDecimal("90.00"));
			// Guarda los productos en la base de datos
			productoRepository.saveAll(Arrays.asList(producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9));
		}
	}*/

	/*/Poblar la tabla transportadora con datos para pruebas
	@Component
	public class DataLoader implements CommandLineRunner {
		@Autowired
		private TransportadoraRepository transportadoraRepository;
		@Autowired
		private TransportadoraServiceImpl transportadoraService;
		@Override
		public void run(String... args) throws Exception {
			// Crea tres transportadoras de pruebas
			Transportadora transportadora1 = new Transportadora();
			transportadora1.setNombre("TransTest3");
			transportadora1.setCodigo("TransportadoraCustomer2");
			transportadora1.setStatus(true);
			transportadora1.setFechaCreacion(new Date());
			transportadora1.setIdentificador("900093");
			transportadora1.setUsuarioCreacion(1L);
			// Guarda los productos en la base de datos
			try {
				transportadoraService.createTransportadora(transportadora1);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}

			Transportadora transportadora2 = new Transportadora();
			transportadora2.setNombre("TransTest2");
			transportadora2.setCodigo("TransportadoraAdmin");
			transportadora2.setStatus(true);
			transportadora2.setFechaCreacion(new Date());
			transportadora2.setIdentificador("900092");
			transportadora2.setUsuarioCreacion(1L);
			// Guarda los productos en la base de datos
			try {
				transportadoraService.createTransportadora(transportadora2);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}*/

	//Poblar la tabla credenciales con datos para pruebas
	/*@Component
	public class DataLoader implements CommandLineRunner {
		@Autowired
		private TransportadoraService transportadoraService;
		@Autowired
		private TransportadoraCredencialService credencialService;
		@Autowired
		private PasswordEncoder passwordEncoder;
		@Override
		public void run(String... args) throws Exception {
			try {
				Transportadora t_Customer1 = transportadoraService.getTransportadoraById(5);
				Transportadora t_Customer2 = transportadoraService.getTransportadoraById(7);
				Transportadora t_Admin = transportadoraService.getTransportadoraById(6);

				TransportadoraCredencial credencial1 = new TransportadoraCredencial();
				credencial1.setUsuario("TransCus1");
				credencial1.setPassword(passwordEncoder.encode("customer1"));
				credencial1.setTransportadora(t_Customer1);
				credencial1.setStatus(true);
				credencial1.setRole(Role.CUSTOMER);
				credencial1.setFechaCreacion(new Date());

				TransportadoraCredencial credencial2 = new TransportadoraCredencial();
				credencial2.setUsuario("TransCus2");
				credencial2.setPassword(passwordEncoder.encode("customer2"));
				credencial2.setTransportadora(t_Customer2);
				credencial2.setStatus(true);
				credencial2.setRole(Role.CUSTOMER);
				credencial2.setFechaCreacion(new Date());

				TransportadoraCredencial credencial3= new TransportadoraCredencial();
				credencial3.setUsuario("TransAdmin");
				credencial3.setPassword(passwordEncoder.encode("admin"));
				credencial3.setTransportadora(t_Admin);
				credencial3.setStatus(true);
				credencial3.setRole(Role.ADMINISTRATOR);
				credencial3.setFechaCreacion(new Date());

				// Guarda los datos en la base de datos
				credencialService.createCredenciales(Arrays.asList(credencial1, credencial2, credencial3));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}*/


}
