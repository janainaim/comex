package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioDeClientesMaisLucrativos extends Relatorio{

    private Map<String, List<Pedido>> filtrarRelatorio;

    private Consumer<String> imprimirRelatorio;

    private List<ClientesMaisLucrativos> clientesMaisLucrativos;

    public RelatorioDeClientesMaisLucrativos(List<Pedido> pedidos, Consumer<String> imprimirRelatorio){
        super(pedidos);
        this.imprimirRelatorio = imprimirRelatorio;
    }


    @Override
    public void filtrarRelatorio() {
        filtrarRelatorio = pedidos.stream().collect(Collectors.groupingBy(Pedido::getCliente));
        clientesMaisLucrativos = filtrarRelatorio.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                //.limit(2)
                .map(entry -> {
                   String cliente = entry.getKey();
                   long numeroPedidos = entry.getValue().stream().mapToLong(Pedido::getQuantidade).count();
                    BigDecimal montante = entry.getValue().stream()
                            .map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new ClientesMaisLucrativos(cliente, numeroPedidos, montante);
                }).toList();

        //Lista reordenada e limitada as duas pessoas com o montante maior
        clientesMaisLucrativos = clientesMaisLucrativos.stream().sorted(Collections.reverseOrder(Comparator.comparing(ClientesMaisLucrativos::getNumeroPedidos))).limit(2).toList();
    }

    @Override
    public void imprimirRelatorio() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        imprimirRelatorio.accept("\n\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");
        clientesMaisLucrativos.stream().forEach(clientesMaisLucrativos -> imprimirRelatorio.accept(
                "\nNOME: " +clientesMaisLucrativos.getCliente()+
                        "\nNº DE PEDIDOS: " +clientesMaisLucrativos.getNumeroPedidos()+
                        "\nMONTANTE GASTO: " +numberFormat.format(clientesMaisLucrativos.getMontante())));

    }

    public List<ClientesMaisLucrativos> getClientesMaisLucrativos() {
        return clientesMaisLucrativos;
    }
}
