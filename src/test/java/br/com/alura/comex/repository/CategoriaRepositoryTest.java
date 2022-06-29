package br.com.alura.comex.repository;


import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private  ProdutoRepository produtoRepository;

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

    categoriaRepository.saveAll(categorias);
    List<Categoria> categoriaList = categoriaRepository.findAll();


    produtoRepository.saveAll(produtos);
    List<Produto> produtoList = produtoRepository.findAll();

    //Testanto categorias inseridas da lista
    assertNotNull(categoriaList);
    //Testando produtos inseridos na lista
    assertNotNull(produtoList);

  }


  //Dropando o bd de testes após cada teste
//  @AfterEach
//  public void execute() {
//    jdbcTemplate.execute("DROP DATABASE comexdb_test" );
//  }


}