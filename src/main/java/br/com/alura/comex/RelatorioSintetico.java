package br.com.alura.comex;

import java.math.BigDecimal;
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
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoAtual = pedidos.get(i);

            if (pedidoAtual == null) {
                break;
            }

            if (pedidoMaisBarato == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
                pedidoMaisBarato = pedidoAtual;
            }

            if (pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
                pedidoMaisCaro = pedidoAtual;
            }

            montanteDeVendas = montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
                totalDeCategorias++;
                categoriasProcessadas.add(pedidoAtual.getCategoria());
            }
        }
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
