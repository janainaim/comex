package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoBuilder;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class RelatorioDeProdutosMaisVendidosTest {

    private RelatorioDeProdutosMaisVendidos service;

    public List<VendasPorQuantidade> iniciaRelatorioDeVendasPorCategoria(List<Pedido> pedidos){
        this.service = new RelatorioDeProdutosMaisVendidos(pedidos, System.out::println);
        service.gerarRelatorio();
         return service.getVendasPorQuantidade();
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
//                new PedidoBuilder()
//                        .categoria("LIVROS")
//                        .produto("KINDLE")
//                        .cliente("JOANA")
//                        .preco(new BigDecimal("200"))
//                        .quantidade(1)
//                        .data(LocalDate.now())
//                        .build());

        //2-Act
        List<VendasPorQuantidade> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);
        VendasPorQuantidade vendasPorQuantidade = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        assertEquals(1, resultado.size());
        //Comparação dos itens do pedido
        assertEquals("KINDLE", vendasPorQuantidade.getProduto());
        assertEquals(3, vendasPorQuantidade.getQuantidadeVendida());

        //Se coloco dois produtos iguais, ele retorna os dois produtos, no caso, terei de consertar o RelatorioDeProdutosMaisVendidos
        //Acredito que seja porque foi ordenado por quantidade e não por produto (verei isso em breve)
    }

    @Test
    void deveRetornarORelatorioComOsDadosPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        //2-Act
        List<VendasPorQuantidade> resultado =  iniciaRelatorioDeVendasPorCategoria(pedidos);
        //VendasPorQuantidade vendasPorQuantidade = resultado.get(0);

        //3-Assert
        //Teste se o relatório retorna os dados da base de dados
        assertNotNull(resultado);
        //Teste dos dados presentes esperado pelo banco de dados
        assertEquals("iPhone 13 Pro", resultado.get(0).getProduto());
        assertEquals(6, resultado.get(0).getQuantidadeVendida());
        assertEquals("Galaxy S22 Ultra", resultado.get(1).getProduto());
        assertEquals(5, resultado.get(1).getQuantidadeVendida());
        assertEquals("Galaxy Tab S8", resultado.get(2).getProduto());
        assertEquals(4, resultado.get(2).getQuantidadeVendida());

    }

}