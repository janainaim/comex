package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoBuilder;
import br.com.alura.comex.service.processador.ProcessadorDeCsv;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioDeClientesFieisTest {

    private RelatorioDeClientesFieis service;

    public List<ClientesFieis>  iniciaRelatorioDeVendasPorCategoria(List<Pedido> pedidos){
        this.service = new RelatorioDeClientesFieis(pedidos, System.out::println);
        service.gerarRelatorio();
        return service.getClientesFieis();
    }


    @Test
    void deveRetornarORelatorioComListaDeTresPedidosEUmClienteFiel(){
        //1-Arrange
        List<Pedido> pedidos = List.of(new PedidoBuilder()
                        .categoria("LIVROS")
                        .produto("LIVROS DIVERSOS")
                        .cliente("ANA JÚLIA")
                        .preco(new BigDecimal("50"))
                        .quantidade(3)
                        .data(LocalDate.now())
                        .build(),
                new PedidoBuilder()
                        .categoria("INFORMÁTICA")
                        .produto("MOUSE GAMER")
                        .cliente("ANA JÚLIA")
                        .preco(new BigDecimal("300"))
                        .quantidade(5)
                        .data(LocalDate.now())
                        .build(),
                new PedidoBuilder()
                        .categoria("LIVROS")
                        .produto("KINDLE")
                        .cliente("ANA JÚLIA")
                        .preco(new BigDecimal("200"))
                        .quantidade(1)
                        .data(LocalDate.now())
                        .build());

        //2-Act
        List<ClientesFieis> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);

        //3-Assert
        //Retorno esperado de 1 item na lista pois a mesma cliente fez 3 pedidos
        assertEquals(1, resultado.size());

    }

    @Test
    void deveRetornarORelatorioPresenteNaBaseDeDados(){
        //1-Arrange
        ProcessadorAdapter processadorAdapter = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        //2-Act
        List<ClientesFieis> resultado = iniciaRelatorioDeVendasPorCategoria(pedidos);

        //3-Assert
        //Teste se nenhum dado retorna nulo
        assertNotNull(resultado);

    }

}