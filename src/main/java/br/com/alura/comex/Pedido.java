package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;

    private BigDecimal valorTotal;
    private boolean pedidoMaisBaratoQue;
    private boolean pedidoMaisCaroQue;

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public boolean isPedidoMaisBaratoQue() {
        return pedidoMaisBaratoQue;
    }

    public void setPedidoMaisBaratoQue(boolean pedidoMaisBaratoQue) {
        this.pedidoMaisBaratoQue = pedidoMaisBaratoQue;
    }

    public boolean isPedidoMaisCaroQue() {
        return pedidoMaisCaroQue;
    }

    public void setPedidoMaisCaroQue(boolean pedidoMaisCaroQue) {
        this.pedidoMaisCaroQue = pedidoMaisCaroQue;
    }

    public BigDecimal getValorTotal(){

        return valorTotal;
    }

    public boolean isMaisBaratoQue(Pedido outroPedido) {

        return pedidoMaisBaratoQue;
    }

    public boolean isMaisCaroQue(Pedido outroPedido){

        return pedidoMaisCaroQue;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
