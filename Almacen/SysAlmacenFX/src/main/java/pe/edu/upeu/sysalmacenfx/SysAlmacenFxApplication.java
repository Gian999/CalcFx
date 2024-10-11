package pe.edu.upeu.sysalmacenfx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pe.edu.upeu.sysalmacenfx.pruebas.CategoriaCrud;

@SpringBootApplication
public class SysAlmacenFxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysAlmacenFxApplication.class, args);

	}
	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			// Obtén el bean de MainX del contexto de Spring
//			MainX mx = context.getBean(MainX.class);
//			mx.menu();

			CategoriaCrud CCrud = context.getBean(CategoriaCrud.class);
			CCrud.menu();
		};
	}

}
