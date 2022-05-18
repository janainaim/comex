package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioDeClientesFieis {

    Map<String, Long> quantidadeDePedidosPorCliente;
    public RelatorioDeClientesFieis(List<Pedido> pedidos){

        //Não consegui o sort
        quantidadeDePedidosPorCliente = pedidos.stream().collect(Collectors.groupingBy(Pedido::getCliente,
                Collectors.counting()));

    }

    public Map<String, Long> getQuantidadeDePedidosPorCliente() {


        return quantidadeDePedidosPorCliente;
    }

    public void setQuantidadeDePedidosPorCliente(Map<String, Long> quantidadeDePedidosPorCliente) {
        this.quantidadeDePedidosPorCliente = quantidadeDePedidosPorCliente;
    }
}