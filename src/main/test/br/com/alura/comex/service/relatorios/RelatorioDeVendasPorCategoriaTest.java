package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class RelatorioDeVendasPorCategoriaTest {

    @Test
    void deveRetornarRelatorioComListaVaziaDePedidos(){
        //1-Arrange
        List<Pedido> pedidos = new ArrayList<>();
        RelatorioDeVendasPorCategoria vendasPorCategoria = new RelatorioDeVendasPorCategoria(pedidos, System.out::println);

        //3-Assert e 2-Act(vendasPorCategoria.gerarRelatorio()) que está como methodReference
        Assertions.assertThrows(IllegalArgumentException.class,
                vendasPorCategoria::gerarRelatorio);
    }

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
        RelatorioDeVendasPorCategoria vendasPorCategoria = new RelatorioDeVendasPorCategoria(pedidos, System.out::println);

        //2-Act
        vendasPorCategoria.gerarRelatorio();
        List<VendasPorCategoria> resultado = vendasPorCategoria.getVendasPorCategoria();
        VendasPorCategoria vendasPorCategoria1 = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        Assertions.assertEquals("LIVROS", vendasPorCategoria1.getCategoria());
        Assertions.assertEquals(3, vendasPorCategoria1.getQuantidadeVendida());
        //Assertions.assertEquals("600", vendasPorCategoria1.getValorMontante());

    }

    @Test
    void deveRetornarAListaDePedidosPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        RelatorioDeVendasPorCategoria vendasPorCategoria = new RelatorioDeVendasPorCategoria(pedidos, System.out::println);

        //2-Act
        vendasPorCategoria.gerarRelatorio();
        List<VendasPorCategoria> resultado = vendasPorCategoria.getVendasPorCategoria();
        VendasPorCategoria vendasPorCategoria1 = resultado.get(0);

        //3-Assert
        //Teste se nenhum dado retorna nulo
        Assertions.assertNotNull(resultado);

    }

}