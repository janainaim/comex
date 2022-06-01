package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoBuilder;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioDeProdutosMaisCarosDeCadaCategoriaTest {

    private RelatorioDeProdutosMaisCarosDeCadaCategoria service;

    public List<VendasProdutoMaisCaro> iniciaRelatorioDeVendasPorCategoria(List<Pedido> pedidos){
        this.service = new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);
        service.gerarRelatorio();
         return service.getVendasProdutoMaisCaro();
    }

    @Test
    void deveRetornarRelatorioComListaVaziaDePedidos(){
        //1-Arrange
        List<Pedido> pedidos = new ArrayList<>();
        RelatorioDeProdutosMaisCarosDeCadaCategoria produtosMaisCarosDeCadaCategoria =
                new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println);

        //3-Assert e 2-Act(vendasPorCategoria.gerarRelatorio()) que está como methodReference
        assertThrows(IllegalArgumentException.class,
                produtosMaisCarosDeCadaCategoria::gerarRelatorio);

    //Deu errado este teste específico com service
    }

    @Test
    void deveRetornarORelatorioComListaDeUmUnicoPedido(){
        //1-Arrange
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                .categoria("LIVROS")
                .produto("KINDLE")
                .cliente("ANA")
                .preco(new BigDecimal("200"))
                .quantidade(3)
                .data(LocalDate.now())
                .build());

        //2-Act
        List<VendasProdutoMaisCaro> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);
        VendasProdutoMaisCaro vendasProdutoMaisCaro = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        assertEquals("LIVROS", vendasProdutoMaisCaro.getCategoria());
        assertEquals("KINDLE", vendasProdutoMaisCaro.getProduto());
        Assertions.assertEquals(new BigDecimal("200"), vendasProdutoMaisCaro.getPreco());

    }

    @Test
    void deveRetornarORelatorioPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        //2-Act
        List<VendasProdutoMaisCaro> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);

        //3-Assert
        //Teste se nenhum dado retorna nulo
        assertNotNull(resultado);

    }

    @Test
    void deveRetornarOProdutoMaisCaroEntreDoisPedidos(){
        //1-Arrange
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                .categoria("CELULARES")
                .produto("MOTO G 26")
                .cliente("ANA")
                .preco(new BigDecimal("4999"))
                .quantidade(3)
                .data(LocalDate.now())
                .build(),
                new PedidoBuilder()
                        .categoria("CELULARES")
                        .produto("GALAXY S22")
                        .cliente("LUÍSA")
                        .preco(new BigDecimal("5000"))
                        .quantidade(1)
                        .data(LocalDate.now())
                        .build());

        //2-Act
        List<VendasProdutoMaisCaro> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);
        VendasProdutoMaisCaro vendasProdutoMaisCaro = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        assertEquals("CELULARES", vendasProdutoMaisCaro.getCategoria());
        assertEquals("GALAXY S22", vendasProdutoMaisCaro.getProduto());
        assertEquals(new BigDecimal("5000"), vendasProdutoMaisCaro.getPreco());

    }

}