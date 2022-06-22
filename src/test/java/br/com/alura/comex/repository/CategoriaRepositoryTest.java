package br.com.alura.comex.repository;



import br.com.alura.comex.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private  ProdutoRepository produtoRepository;

//  @Autowired
//  private TestEntityManager testEntityManager;

  @Test
  public void deveRetornarUmaCategoriaAoBuscarTodas(){

    List<Categoria> categorias = categoriaRepository.findAll();
    assertNotNull(categorias);

  }

  @Test
  public void deveCadastrarPreviamenteDuasCategorias(){

    List<Categoria> categorias = List.of(
            new Categoria("LIVROS"),
            new Categoria("CARROS")
    );

    List<Produto> produtos = List.of(
            new Produto("Kindle",
                    "Dona Amazon baixe para 0 reais .-.",
                    new BigDecimal("450"),
                    3L,
                    categorias.get(0)),
            new Produto("Honda Fit",
                    "Um carro longe de ser o Sedan dos sonhos, mas um baita carrinho",
                    new BigDecimal("30.000"),
                    1L,
                    categorias.get(1))
    );

//    List<Pedido> pedidos = List.of(
//            new Pedido(
//                    LocalDate.now(),
//                    new Cliente("Ana",
//                            "11122233344",
//                            "1122224444",
//                            new Endereco("Rua Um",
//                                    "22",
//                                    "Centro",
//                                    "Catotas",
//                                    "São Paulo",
//                                    "São Paulo"),
//                            new Usuario("joaninha@email.com",
//                                    "123456", new Perfil())),
//                    new BigDecimal("0"),
//                    "NENHUM")
//    );

    categoriaRepository.saveAll(categorias);
    List<Categoria> categoriaList = categoriaRepository.findAll();


    produtoRepository.saveAll(produtos);
    List<Produto> produtoList = produtoRepository.findAll();

    //Testanto categorias inseridas da lista
    assertNotNull(categoriaList);
    //Testando produtos inseridos na lista
    assertNotNull(produtoList);

  }


}