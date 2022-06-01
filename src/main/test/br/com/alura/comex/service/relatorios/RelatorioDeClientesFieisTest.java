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

class RelatorioDeClientesFieisTest {

    @Test
    void deveRetornarORelatorioComListaDeTresClientesFieis(){
        //1-Arrange
        Pedido pedido = new Pedido("LIVROS",
                "LIVROS DIVERSOS",
                "ANA JÚLIA",
                new BigDecimal("50"),
                3,
                LocalDate.now());

        Pedido pedido2 = new Pedido("INFORMÁTICA",
                "MOUSE GAMER",
                "ANA JÚLIA",
                new BigDecimal("300"),
                5,
                LocalDate.now());

        Pedido pedido3 = new Pedido("LIVROS",
                "KINDLE",
                "ANA JÚLIA",
                new BigDecimal("200"),
                1,
                LocalDate.now());


        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        pedidos.add(pedido2);
        pedidos.add(pedido3);


        RelatorioDeClientesFieis clientesFieis = new RelatorioDeClientesFieis(pedidos, System.out::println);

        //2-Act
        clientesFieis.gerarRelatorio();
        List<ClientesFieis> resultado = clientesFieis.getClientesFieis();

        //3-Assert
        //Retorno esperado de 1 item na lista pois a mesma cliente fez 3 pedidos
        Assertions.assertEquals(1, resultado.size());

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

}