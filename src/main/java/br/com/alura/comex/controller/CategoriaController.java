package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<CategoriaDto> listaDeCategorias() {
    List<Categoria> listaDeCategorias = categoriaRepository.findAll();
    return CategoriaDto.converter(listaDeCategorias);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<CategoriaDto> inserirCategoria(@RequestBody @Valid CategoriaForm form,
                                                       UriComponentsBuilder uriBuilder) {

    Categoria categoria = form.converter(categoriaRepository);
    categoriaRepository.save(categoria);

    URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
    return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
  }

}