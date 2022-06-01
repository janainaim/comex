package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


class RelatorioDeClientesMaisLucrativosTest {


    @Test
    void deveRetornarORelatorioPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        RelatorioDeClientesMaisLucrativos clientesMaisLucrativos =
                new RelatorioDeClientesMaisLucrativos(pedidos, System.out::println);

        //2-Act
        clientesMaisLucrativos.gerarRelatorio();
        List<ClientesMaisLucrativos> resultado = clientesMaisLucrativos.getClientesMaisLucrativos();

        //3-Assert
        //Teste se nenhum dado retorna nulo
        Assertions.assertNotNull(resultado);

    }

    @Test
    void deveGerarORelatorioDeUmaListaComTresPedidosDaMesmaPessoa(){
        //1-Arrange
        Pedido pedido1 = new Pedido("CELULARES",
                "GALAXY S22",
                "LUÍSA",
                new BigDecimal("5000"),
                1,
                LocalDate.now());
        Pedido pedido2 = new Pedido("CELULARES",
                "MOTO G 26",
                "LUÍSA",
                new BigDecimal("4999"),
                3,
                LocalDate.now());

        Pedido pedido3 = new Pedido("CELULARES",
                "GALAXY S22",
                "LUÍSA",
                new BigDecimal("5000"),
                1,
                LocalDate.now());


        List<Pedido> pedidos = List.of(pedido1, pedido2, pedido3);
        RelatorioDeClientesMaisLucrativos clientesMaisLucrativos = new RelatorioDeClientesMaisLucrativos(pedidos, System.out::println);

        //2-Act
        clientesMaisLucrativos.gerarRelatorio();
        List<ClientesMaisLucrativos> resultado = clientesMaisLucrativos.getClientesMaisLucrativos();
        ClientesMaisLucrativos clientesMaisLucrativos1 = resultado.get(0);

        //3-Assert
        //Retorno esperado de apenas um item na lista
        Assertions.assertEquals(1, resultado.size());


    }


}