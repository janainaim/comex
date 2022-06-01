package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoBuilder;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class RelatorioDeVendasPorCategoriaTest {

    private RelatorioDeVendasPorCategoria service;

    public void iniciaRelatorioDeVendasPorCategoria(List<Pedido> pedidos){
        this.service = new RelatorioDeVendasPorCategoria(pedidos, System.out::println);
        service.gerarRelatorio();
    }


    @Test
    void deveRetornarRelatorioComListaVaziaDePedidos(){
        //1-Arrange em comum nos testes
        List<Pedido> pedidos = new ArrayList<>();
        RelatorioDeVendasPorCategoria relatorioDeVendasPorCategoria =
                new RelatorioDeVendasPorCategoria(pedidos, System.out::println);

        //3-Assert e 2-Act(vendasPorCategoria.gerarRelatorio()) que está como methodReference
        assertThrows(IllegalArgumentException.class,
                relatorioDeVendasPorCategoria::gerarRelatorio);
    }

    @Test
    void deveRetornarRelatorioComListaDeUmUnicoPedido(){
        //1-Arrange
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                .categoria("LIVROS")
                .produto("KINDLE")
                .cliente("ANA")
                .preco(new BigDecimal("200"))
                .quantidade(3)
                .data(LocalDate.now())
                .build());

        iniciaRelatorioDeVendasPorCategoria(pedidos);

        //2-Act
        List<VendasPorCategoria> resultado = service.getVendasPorCategoria();
        VendasPorCategoria vendasPorCategoriaRetorno = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        assertEquals(1, resultado.size());

        //Comparação dos itens do pedido
        assertEquals("LIVROS", vendasPorCategoriaRetorno.getCategoria());
        assertEquals(3, vendasPorCategoriaRetorno.getQuantidadeVendida());
        assertEquals(new BigDecimal("600"), vendasPorCategoriaRetorno.getValorMontante());

    }

    @Test
    void deveRetornarAListaDePedidosPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        iniciaRelatorioDeVendasPorCategoria(pedidos);

        //2-Act
        List<VendasPorCategoria> resultado = service.getVendasPorCategoria();

        //3-Assert
        //Teste se nenhum dado retorna nulo
        assertNotNull(resultado);

    }

    @Test
    void deveRetornarDuasCategoriasComTresPedidosAoTodo(){
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                .categoria("LIVROS")
                .produto("KINDLE")
                .cliente("ANA")
                .preco(new BigDecimal("200"))
                .quantidade(3)
                .data(LocalDate.now())
                .build(),
                new PedidoBuilder()
                        .categoria("INFORMÁTICA")
                        .produto("MOUSE GAMER LOGTECH")
                        .cliente("JAMILA")
                        .preco(new BigDecimal("300"))
                        .quantidade(1)
                        .data(LocalDate.now())
                        .build(),
                new PedidoBuilder()
                        .categoria("LIVROS")
                        .produto("LIVROS DIVERSOS")
                        .cliente("TAMILA")
                        .preco(new BigDecimal("122.80"))
                        .quantidade(5)
                        .data(LocalDate.now())
                        .build());

        iniciaRelatorioDeVendasPorCategoria(pedidos);

        //2-Act
        List<VendasPorCategoria> resultado = service.getVendasPorCategoria();

        //Verifica se os três pedidos retornaram um relatório com duas categorias
        assertEquals(2, resultado.size());

        //Verifica se o montante da categoria LIVROS está correta conforme a soma dos pedidos e os seus respetivos preços
        assertEquals(new BigDecimal("1214.00"), resultado.get(1).getValorMontante());

    }

}