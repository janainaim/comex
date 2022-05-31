package br.com.alura.comex.service.relatorios;

import java.math.BigDecimal;

public class VendasProdutoMaisCaro {

    String categoria;
    String produto;
    BigDecimal preco;

    public VendasProdutoMaisCaro(String categoria, String produto, BigDecimal preco) {
        this.categoria = categoria;
        this.produto = produto;
        this.preco = preco;
    }

    public VendasProdutoMaisCaro(){}

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
