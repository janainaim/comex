package br.com.alura.comex.service.relatorios;

public class VendasPorQuantidade {

    private String produto;
    private Integer quantidadeVendida;

    public VendasPorQuantidade(String produto, Integer quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
