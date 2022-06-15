package br.com.alura.comex;

import br.com.alura.comex.controller.RelatoriosController;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ComexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);

	}


}
