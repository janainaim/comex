package br.com.alura.comex.service;

import br.com.alura.comex.projecao.PedidoProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RelatoriosService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public RelatoriosService(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public void pesquisaRelatorioPorCategoria() {
    List<PedidoProjecao> list = pedidoRepository.gerarRelatorioDePedidosPorCategoria();
    list.forEach(pedido -> System.out.println("Pedido: nome_da_categoria: "
            + pedido.getNomeCategoria()
            + " | quantidade_produtos_vendidos: " + pedido.getQuantidadeProdutosVendidos()
            + " | montante_vendido: " + pedido.getMontanteVendido()));
  }
}
