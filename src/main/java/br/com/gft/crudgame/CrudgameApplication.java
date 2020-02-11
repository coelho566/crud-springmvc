package br.com.gft.crudgame;

import java.io.File;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.gft.crudgame.service.GameService;

@SpringBootApplication
public class CrudgameApplication {

	public static void main(String[] args) {
		new File(GameService.uploadDirectory).mkdir();
		SpringApplication.run(CrudgameApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR")); 
	}
	
	
}
