package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
public class RelatorioDeVendasPorCategoria extends Relatorio {

    Map<String, List<Pedido>> filtrarRelatorio;
    List<VendasPorCategoria> vendasPorCategoria;
    private Consumer<String> imprimirRelatorio;

    public RelatorioDeVendasPorCategoria(List<Pedido> pedidos, Consumer<String> imprimirRelatorio){
        super(pedidos);
        this.imprimirRelatorio = imprimirRelatorio;
    }

    @Override
    public void filtrarRelatorio() {
        //Extraír para método filtrarRelatorio
        filtrarRelatorio = pedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria));

        vendasPorCategoria = filtrarRelatorio.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String categoria = entry.getKey();
                    int quantidadeVendida = entry.getValue().stream().mapToInt(Pedido::getQuantidade).sum();
                    BigDecimal valorMontante = entry.getValue().stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new VendasPorCategoria(categoria, quantidadeVendida, valorMontante);
                })
                .toList();
    }

    public void imprimirRelatorio() {

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("\n\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
        vendasPorCategoria.stream().forEach(vendasPorCategoria-> System.out.println("\nCATEGORIA: " +vendasPorCategoria.getCategoria()+
                "\nQUANTIDADE VENDIDA: " +vendasPorCategoria.getQuantidadeVendida()+
                "\nMONTANTE: " +numberFormat.format(vendasPorCategoria.getValorMontante())));


    }
}
