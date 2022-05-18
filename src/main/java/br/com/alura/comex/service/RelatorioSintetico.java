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

    public void setTotalDeProdutosVendidos(int totalDeProdutosVendidos) {
        this.totalDeProdutosVendidos = totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public void setTotalDePedidosRealizados(int totalDePedidosRealizados) {
        this.totalDePedidosRealizados = totalDePedidosRealizados;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public void setMontanteDeVendas(BigDecimal montanteDeVendas) {
        this.montanteDeVendas = montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }

    public void setPedidoMaisBarato(Pedido pedidoMaisBarato) {
        this.pedidoMaisBarato = pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }

    public void setPedidoMaisCaro(Pedido pedidoMaisCaro) {
        this.pedidoMaisCaro = pedidoMaisCaro;
    }

    public CategoriasProcessadas getCategoriasProcessadas() {
        return categoriasProcessadas;
    }

    public void setCategoriasProcessadas(CategoriasProcessadas categoriasProcessadas) {
        this.categoriasProcessadas = categoriasProcessadas;
    }

    public long getTotalDeCategorias() {
        return totalDeCategorias;
    }

    public void setTotalDeCategorias(int totalDeCategorias) {
        this.totalDeCategorias = totalDeCategorias;
    }
}
