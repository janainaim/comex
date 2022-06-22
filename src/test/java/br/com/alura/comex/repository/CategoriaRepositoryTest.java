package br.com.alura.comex.repository;

import br.com.alura.comex.model.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
class CategoriaRepositoryTest {

  @Autowired
  private CategoriaRepository categoriaRepository;

//  @Autowired
//  private TestEntityManager testEntityManager;

  @Test
  public void deveriaRetornarUmaCategoriaAoBuscarTodas(){

    List<Categoria> categorias = categoriaRepository.findAll();
    assertNotNull(categorias);

  }


}