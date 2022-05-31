package br.com.alura.comex.service;

import java.math.BigDecimal;

public class VendasPorCategoria {

    private final String categoria;
    private final Integer quantidadeVendida;
    private final BigDecimal valorMontante;
    public VendasPorCategoria(String categoria, Integer quantidadeVendida, BigDecimal valorMontante) {
        this.categoria = categoria;
        this.quantidadeVendida = quantidadeVendida;
        this.valorMontante = valorMontante;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public BigDecimal getValorMontante() {
        return valorMontante;
    }
}
