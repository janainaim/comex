package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class RelatorioSintetico {


    private final int totalDeProdutosVendidos;
    private final int totalDePedidosRealizados;
    private final BigDecimal montanteDeVendas;
    private final Pedido pedidoMaisBarato;
    private final Pedido pedidoMaisCaro;
    private final long totalDeCategorias;



    public RelatorioSintetico(List<Pedido> pedidos){


        //Extração de variável
        Comparator<Pedido> comparing = Comparator.comparing(Pedido::getValorTotal);

        //Como não fiz as opcionais da semana passada ainda, acredito que se der tempo de criar os outros relatórios, outras refatorações surgirão ao longo dos processos
        pedidoMaisBarato = pedidos.stream().min(comparing).orElseThrow(() -> new IllegalStateException("Não foi possível obter o pedido mais barato!"));
        pedidoMaisCaro = pedidos.stream().max(comparing).orElseThrow(() -> new IllegalStateException("Não foi possível obter o pedido mais caro!"));

        montanteDeVendas = pedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();

        totalDePedidosRealizados = pedidos.size();

        totalDeCategorias = pedidos.stream().map(Pedido::getCategoria).distinct().count();

    }

    public int getTotalDeProdutosVendidos() {

        return totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {

        return totalDePedidosRealizados;
    }

    public BigDecimal getMontanteDeVendas() {

        return montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {

        return pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }

    public long getTotalDeCategorias() {
        return totalDeCategorias;
    }

}
