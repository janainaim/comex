package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;


import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioDeProdutosMaisVendidos extends Relatorio{

    private Map<Integer, List<Pedido>> filtrarRelatorio;

    public List<VendasPorQuantidade> getVendasPorQuantidade() {
        return vendasPorQuantidade;
    }

    private List<VendasPorQuantidade> vendasPorQuantidade;

    private Consumer<String> imprimirRelatorio;
    public RelatorioDeProdutosMaisVendidos(List<Pedido> pedidos, Consumer<String> imprimirRelatorio){
        super(pedidos);
        this.imprimirRelatorio = imprimirRelatorio;
    }



    @Override
    public void filtrarRelatorio() {
        filtrarRelatorio = pedidos.stream().collect(Collectors.groupingBy(Pedido::getQuantidade));
        vendasPorQuantidade = filtrarRelatorio.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .limit(3)
                .map(quantidade -> {
                    int quantidadeVendida = quantidade.getKey();
                    String produtoPorQuantidade = quantidade.getValue().get(0).getProduto();
                    return new VendasPorQuantidade(produtoPorQuantidade, quantidadeVendida);
                }).toList();
    }

    @Override
    public void imprimirRelatorio() {
        //mudar para consumer posteriormente
        System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS");
        vendasPorQuantidade.stream().forEach(vendasPorQuantidade -> {
            System.out.println("\nPRODUTO: " +vendasPorQuantidade.getProduto()+
                    "\nQUANTIDADE: " +vendasPorQuantidade.getQuantidadeVendida());
        });

    }
}
