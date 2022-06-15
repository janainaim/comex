package br.com.alura.comex.controller;

import br.com.alura.comex.projecao.PedidoProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatoriosController {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void pesquisafuncionarioSalario() {
        List<PedidoProjecao> list = pedidoRepository.gerarRelatorioDePedidosPorCategoria();
        list.forEach(pedido -> System.out.println("Pedido: nome_da_categoria: " + pedido.getNomeCategoria()
                + " | quantidade_produtos_vendidos: " + pedido.getQuantidadeProdutosVendidos()
                + " | montante_vendido: " + pedido.getMontanteVendido()));
    }
}
