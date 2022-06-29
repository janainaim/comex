package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.UsuarioDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.controller.form.UsuarioForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<UsuarioDto> inserirUsuario(@RequestBody @Valid UsuarioForm form,
                                                     UriComponentsBuilder uriBuilder) {

    Usuario usuario = form.converter(usuarioRepository);
    usuarioRepository.save(usuario);

    URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
    return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
  }

}
