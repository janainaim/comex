package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioDeClientesFieis extends Relatorio {

    private Map<String, Long> filtrarRelatorio;
    private Consumer<String> imprimirRelatorio;

    private List<ClientesFieis> clientesFieis;


    public RelatorioDeClientesFieis(List<Pedido> pedidos, Consumer<String> imprimirRelatorio){
        super(pedidos);
        this.imprimirRelatorio = imprimirRelatorio;

    }

    @Override
    public void filtrarRelatorio() {
        filtrarRelatorio = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));
        clientesFieis = filtrarRelatorio.entrySet().stream().map(entry -> {
            String nomeCliente = entry.getKey();
            Long numeroPedidos = entry.getValue();
            return new ClientesFieis(nomeCliente, numeroPedidos);
        }).toList();

    }

    @Override
    public void imprimirRelatorio() {
        imprimirRelatorio.accept("\n\n#### RELATÓRIO DE CLIENTES FIÉIS");
        clientesFieis.stream().forEach(clientesFieis -> imprimirRelatorio.accept("\nNOME: " +clientesFieis.getNomeCliente()+ "\nNº DE PEDIDOS: " +clientesFieis.getNumeroPedidos()));
    }

    public List<ClientesFieis> getClientesFieis() {
        return clientesFieis;
    }
}
