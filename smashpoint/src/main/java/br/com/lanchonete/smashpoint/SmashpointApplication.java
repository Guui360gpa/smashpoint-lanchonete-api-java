package br.com.lanchonete.smashpoint;

import br.com.lanchonete.smashpoint.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmashpointApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SmashpointApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.exibirMenuMain();


	}
}
