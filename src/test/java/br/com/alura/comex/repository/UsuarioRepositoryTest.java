package br.com.alura.comex.repository;

import br.com.alura.comex.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private MockMvc mockMvc;


//  @Test
//  void deveRetornarNomeCorretoAoPesquisarUsuarioPeloEmail() throws Exception{
//
//    Usuario cadastraUsuario = new Usuario("aluno@email.com",
//            "$2a$10$HZ7H4VOI.b12rGISjwPRT.8ORSfukcbHlUowek14WjuiI7gYxLkya",
//            new ArrayList<>());
//
//    testEntityManager.persist(cadastraUsuario);
//
//    Optional<Usuario> usuario = usuarioRepository.findByEmail("aluno@email.com");
//
//    URI uri = new URI("/usuario?=aluno@email.com");
//
//    mockMvc.perform(MockMvcRequestBuilders
//                    .get(uri)
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
////            .andExpect(jsonPath("$.[0].email", equalTo(usuario.get(0).getEmail())));
//
//  }


}