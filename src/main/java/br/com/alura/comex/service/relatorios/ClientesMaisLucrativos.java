package br.com.alura.comex.service.relatorios;

import java.math.BigDecimal;
public class ClientesMaisLucrativos {

    private final String cliente;
    private final long numeroPedidos;
    private final BigDecimal montante;

    public ClientesMaisLucrativos(String cliente, long numeroPedidos, BigDecimal montante) {
        this.cliente = cliente;
        this.numeroPedidos = numeroPedidos;
        this.montante = montante;
    }

    public String getCliente() {
        return cliente;
    }

    public long getNumeroPedidos() {
        return numeroPedidos;
    }

    public BigDecimal getMontante() {
        return montante;
    }
}
