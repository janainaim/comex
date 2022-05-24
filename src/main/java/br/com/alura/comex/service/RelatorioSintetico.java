package br.com.alura.comex.service;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class RelatorioSintetico {


    private int totalDeProdutosVendidos = 0;
    private int totalDePedidosRealizados = 0;
    private BigDecimal montanteDeVendas = BigDecimal.ZERO;
    private Pedido pedidoMaisBarato = null;
    private Pedido pedidoMaisCaro = null;
    private CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
    private long totalDeCategorias = 0;



    public RelatorioSintetico(List<Pedido> pedidos){


            pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException(""));

            pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException(""));

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

    public CategoriasProcessadas getCategoriasProcessadas() {
        return categoriasProcessadas;
    }

    public long getTotalDeCategorias() {
        return totalDeCategorias;
    }

}
