package br.com.alura.comex.vo;

import br.com.alura.comex.model.Cliente;

public class RelatorioDeQuantidadeDePedidosPorClienteVo {

    private String cliente;

    private Long quantidadeDePedidos;

    public RelatorioDeQuantidadeDePedidosPorClienteVo(String cliente, Long quantidadeDePedidos) {
        this.cliente = cliente;
        this.quantidadeDePedidos = quantidadeDePedidos;
    }

    @Override
    public String toString() {
        return "RelatorioDeQuantidadeDePedidosPorClienteVo [nomeCliente=" + cliente
                + ", quantidadeDePedidos=" + quantidadeDePedidos + "]";
    }

    public String getCliente() {
        return cliente;
    }

    public Long getQuantidadeDePedidos() {
        return quantidadeDePedidos;
    }
}
