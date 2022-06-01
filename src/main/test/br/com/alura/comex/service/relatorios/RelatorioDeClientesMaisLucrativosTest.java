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


class RelatorioDeClientesMaisLucrativosTest {

    private RelatorioDeClientesMaisLucrativos service;

    public List<ClientesMaisLucrativos> iniciaRelatorioDeVendasPorCategoria(List<Pedido> pedidos){
        this.service = new RelatorioDeClientesMaisLucrativos(pedidos, System.out::println);
        service.gerarRelatorio();
         return service.getClientesMaisLucrativos();
    }

    @Test
    void deveRetornarORelatorioPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        //2-Act
        List<ClientesMaisLucrativos> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);

        //3-Assert
        //Teste se nenhum dado retorna nulo
        Assertions.assertNotNull(resultado);

    }

    @Test
    void deveGerarORelatorioDeUmaListaComTresPedidosDaMesmaPessoa(){
        //1-Arrange
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                        .categoria("CELULARES")
                        .produto("GALAXY S22")
                        .cliente("LUÍSA")
                        .preco(new BigDecimal("5000"))
                        .quantidade(1)
                        .data(LocalDate.now())
                        .build(),
                new PedidoBuilder()
                        .categoria("CELULARES")
                        .produto("MOTO G 26")
                        .cliente("LUÍSA")
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
        List<ClientesMaisLucrativos> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());

    }


}