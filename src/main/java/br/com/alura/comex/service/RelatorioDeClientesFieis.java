package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioDeClientesFieis {

    Map<String, Long> quantidadeDePedidosPorCliente;
    public RelatorioDeClientesFieis(List<Pedido> pedidos){

        quantidadeDePedidosPorCliente = pedidos.stream().collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new,
                Collectors.counting()));

    }

    public Map<String, Long> getQuantidadeDePedidosPorCliente() {
        return quantidadeDePedidosPorCliente;
    }
}
