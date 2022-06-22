package br.com.alura.comex.controller;

import br.com.alura.comex.projecao.PedidoProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias/pedidos")
public class RelatoriosController {

  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping
  @Cacheable({"relatorioPorCategoria"})
  public List<PedidoProjecao> pesquisaRelatorioPorCategoria() {
    List<PedidoProjecao> list = pedidoRepository.gerarRelatorioDePedidosPorCategoria();
    list.forEach(pedido -> System.out.println("Pedido: nome_da_categoria: "
            + pedido.getNomeCategoria()
            + " | quantidade_produtos_vendidos: " + pedido.getQuantidadeProdutosVendidos()
            + " | montante_vendido: " + pedido.getMontanteVendido()));
    return list;
  }
}
