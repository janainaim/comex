package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class RelatorioDeProdutosMaisVendidosTest {

    @Test
    void deveRetornarRelatorioComListaDeUmUnicoPedido(){
        //1-Arrange
        Pedido pedido = new Pedido("LIVROS",
                "KINDLE",
                "ANA",
                new BigDecimal("200"),
                3,
                LocalDate.now());

        List<Pedido> pedidos = List.of(pedido);
        RelatorioDeProdutosMaisVendidos produtosMaisVendidos =
                new RelatorioDeProdutosMaisVendidos(pedidos, System.out::println);

        //2-Act
        produtosMaisVendidos.gerarRelatorio();
        List<VendasPorQuantidade> resultado = produtosMaisVendidos.getVendasPorQuantidade();
        VendasPorQuantidade vendasPorQuantidade = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        Assertions.assertEquals("KINDLE", vendasPorQuantidade.getProduto());
        Assertions.assertEquals(3, vendasPorQuantidade.getQuantidadeVendida());

    }

    @Test
    void deveRetornarORelatorioComOsDadosPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        RelatorioDeProdutosMaisVendidos produtosMaisVendidos =
                new RelatorioDeProdutosMaisVendidos(pedidos, System.out::println);

        //2-Act
        produtosMaisVendidos.gerarRelatorio();
        List<VendasPorQuantidade> resultado = produtosMaisVendidos.getVendasPorQuantidade();
        //VendasPorQuantidade vendasPorQuantidade = resultado.get(0);

        //3-Assert
        //Teste se o relatório retorna os dados da base de dados
        Assertions.assertNotNull(resultado);

    }

}