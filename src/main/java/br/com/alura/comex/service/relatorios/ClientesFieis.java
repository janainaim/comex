package br.com.alura.comex.service.relatorios;

public class ClientesFieis {

    String nomeCliente;
    Long numeroPedidos;

    public ClientesFieis(String nomeCliente, Long numeroPedidos) {
        this.nomeCliente = nomeCliente;
        this.numeroPedidos = numeroPedidos;
    }

    public ClientesFieis(){}

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getNumeroPedidos() {
        return numeroPedidos;
    }
}
