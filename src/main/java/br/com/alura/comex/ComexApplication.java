package br.com.alura.comex;

import br.com.alura.comex.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableJpaRepositories
@SpringBootApplication
@EnableSwagger2
public class ComexApplication implements CommandLineRunner {

  private final RelatoriosService relatoriosService;

  public ComexApplication(RelatoriosService relatoriosService) {
    this.relatoriosService = relatoriosService;
  }

  public static void main(String[] args) {
    SpringApplication.run(ComexApplication.class, args);
  }

  @Override
  public void run(String... args) {
    relatoriosService.pesquisaRelatorioPorCategoria();
    //System.out.println(new BCryptPasswordEncoder().encode("123456"));
  }


}
