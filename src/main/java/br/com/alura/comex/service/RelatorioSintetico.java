package br.com.alura.comex.service;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class RelatorioSintetico {


    int totalDeProdutosVendidos = 0;
    int totalDePedidosRealizados = 0;
    BigDecimal montanteDeVendas = BigDecimal.ZERO;
    Pedido pedidoMaisBarato = null;
    Pedido pedidoMaisCaro = null;
    CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
    int totalDeCategorias = 0;



    public RelatorioSintetico(List<Pedido> pedidos){


            pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))).get();

            pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))).get();


            montanteDeVendas = pedidos.stream()
                    .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();

            totalDePedidosRealizados = pedidos.size();

            totalDeCategorias = (int) pedidos.stream().map(Pedido::getCategoria).distinct().count();

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

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }

    public void setTotalDeCategorias(int totalDeCategorias) {
        this.totalDeCategorias = totalDeCategorias;
    }
}
