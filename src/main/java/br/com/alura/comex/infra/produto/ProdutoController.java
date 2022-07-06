package br.com.alura.comex.infra.produto;

import br.com.alura.comex.dominio.produto.ProdutoDto;
import br.com.alura.comex.dominio.produto.ProdutoForm;
import br.com.alura.comex.dominio.produto.Produto;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import br.com.alura.comex.dominio.produto.ProdutoRepository;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  CategoriaRepository categoriaRepository;

  @GetMapping
  public List<ProdutoDto> listaDeProdutos() {
    Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "nome"));
    Page<Produto> listaDeProdutos = produtoRepository.findAll(pageable);

    return ProdutoDto.converter(listaDeProdutos);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProdutoDto> inserirProduto(@RequestBody @Valid ProdutoForm form,
                                                   UriComponentsBuilder uriBuilder) {

    Produto produto = form.converter(categoriaRepository);
    produtoRepository.save(produto);

    URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
    return ResponseEntity.created(uri).body(new ProdutoDto(produto));
  }

}
