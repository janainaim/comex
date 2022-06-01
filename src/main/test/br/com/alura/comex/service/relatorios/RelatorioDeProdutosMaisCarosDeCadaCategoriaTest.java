package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioDeProdutosMaisCarosDeCadaCategoriaTest {

    @Test
    void deveRetornarRelatorioComListaVaziaDePedidos(){
        //1-Arrange
        List<Pedido> pedidos = new ArrayList<>();
        RelatorioDeProdutosMaisCarosDeCadaCategoria produtosMaisCarosDeCadaCategoria = new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);

        //3-Assert e 2-Act(vendasPorCategoria.gerarRelatorio()) que está como methodReference
        Assertions.assertThrows(IllegalArgumentException.class,
                produtosMaisCarosDeCadaCategoria::gerarRelatorio);
    }

    @Test
    void deveRetornarORelatorioComListaDeUmUnicoPedido(){
        //1-Arrange
        Pedido pedido = new Pedido("LIVROS",
                "KINDLE",
                "ANA",
                new BigDecimal("200"),
                3,
                LocalDate.now());

        List<Pedido> pedidos = List.of(pedido);
        RelatorioDeProdutosMaisCarosDeCadaCategoria produtosMaisCarosDeCadaCategoria = new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);

        //2-Act
        produtosMaisCarosDeCadaCategoria.gerarRelatorio();
        List<VendasProdutoMaisCaro> resultado = produtosMaisCarosDeCadaCategoria.getVendasProdutoMaisCaro();
        VendasProdutoMaisCaro vendasProdutoMaisCaro = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        Assertions.assertEquals("LIVROS", vendasProdutoMaisCaro.getCategoria());
        Assertions.assertEquals("KINDLE", vendasProdutoMaisCaro.getProduto());
        //Assertions.assertEquals("200", vendasProdutoMaisCaro.getPreco());

    }

    @Test
    void deveRetornarORelatorioPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        RelatorioDeProdutosMaisCarosDeCadaCategoria produtosMaisCarosDeCadaCategoria =
                new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);

        //2-Act
        produtosMaisCarosDeCadaCategoria.gerarRelatorio();
        List<VendasProdutoMaisCaro> resultado = produtosMaisCarosDeCadaCategoria.getVendasProdutoMaisCaro();
        VendasProdutoMaisCaro vendasProdutoMaisCaro = resultado.get(0);

        //3-Assert
        //Teste se nenhum dado retorna nulo
        Assertions.assertNotNull(resultado);

    }

    @Test
    void deveRetornarOProdutoMaisCaroEntreDoisPedidos(){
        //1-Arrange
        Pedido pedido = new Pedido("CELULARES",
                "MOTO G 26",
                "ANA",
                new BigDecimal("4999"),
                3,
                LocalDate.now());

        Pedido pedido1 = new Pedido("CELULARES",
                "GALAXY S22",
                "LUÍSA",
                new BigDecimal("5000"),
                1,
                LocalDate.now());

        List<Pedido> pedidos = List.of(pedido, pedido1);
        RelatorioDeProdutosMaisCarosDeCadaCategoria produtosMaisCarosDeCadaCategoria = new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);

        //2-Act
        produtosMaisCarosDeCadaCategoria.gerarRelatorio();
        List<VendasProdutoMaisCaro> resultado = produtosMaisCarosDeCadaCategoria.getVendasProdutoMaisCaro();
        VendasProdutoMaisCaro vendasProdutoMaisCaro = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        Assertions.assertEquals("CELULARES", vendasProdutoMaisCaro.getCategoria());
        Assertions.assertEquals("GALAXY S22", vendasProdutoMaisCaro.getProduto());
        //Assertions.assertEquals("200", vendasProdutoMaisCaro.getPreco());

    }

}