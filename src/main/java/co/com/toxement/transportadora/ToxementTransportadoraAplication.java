package co.com.toxement.transportadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "co.com.toxement.transportadora")
public class ToxementTransportadoraAplication {

	public static void main(String[] args) {
		SpringApplication.run(ToxementTransportadoraAplication.class, args);
	}

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
			//Transportadora 1
				Transportadora transportadora1 = new Transportadora();
				transportadora1.setNombre("TransTest1");
				transportadora1.setCodigo("TransportadoraCustomer1");
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

			//Transportadora 2
				Transportadora transportadora2 = new Transportadora();
				transportadora2.setNombre("TransTest2");
				transportadora2.setCodigo("TransportadoraCustomer2");
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

			//Transportadora 3
				Transportadora transportadora3 = new Transportadora();
				transportadora3.setNombre("TransTest3");
				transportadora3.setCodigo("TransportadoraAdmin");
				transportadora3.setStatus(true);
				transportadora3.setFechaCreacion(new Date());
				transportadora3.setIdentificador("9000999");
				transportadora3.setUsuarioCreacion(1L);
				// Guarda los productos en la base de datos
				try {
					transportadoraService.createTransportadora(transportadora3);
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
			// +++++++++++++++++++++REVISAR LOS IDS EN QUE FUERON GUARDADAS ++++++++++++++++++++ /
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
		}*/


		//GENERA LA CLAVE DE USUARIO CON EL ENCRIPTAMIENTO USADO EN ESTA API -> (no es el token)
		@Component
		public class DataLoader implements CommandLineRunner {
			@Autowired
			private PasswordEncoder passwordEncoder;
			@Override
			public void run(String... args) throws Exception {
				try {
					System.out.println(passwordEncoder.encode("Customer1"));
					System.out.println(passwordEncoder.encode("Customer2"));
					System.out.println(passwordEncoder.encode("administrator"));
				} catch (Exception e){
					System.out.println(e.getMessage());
				}

		}

	}

}
