package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioDeProdutosMaisCarosDeCadaCategoria extends Relatorio {

    Map<String, List<Pedido>> filtrarRelatorio;
    List<VendasProdutoMaisCaro> vendasProdutoMaisCaro;
    private Consumer<String> imprimirRelatorio;

    public RelatorioDeProdutosMaisCarosDeCadaCategoria(List<Pedido> pedidos, Consumer<String> imprimirRelatorio){
        super(pedidos);
        this.imprimirRelatorio = imprimirRelatorio;
        //exemplo da locadora, teste data builder

    }

    @Override
    public void filtrarRelatorio() {
        filtrarRelatorio = pedidos
                .stream().collect(Collectors.groupingBy(Pedido::getCategoria));

//        filtrarRelatorio.entrySet().stream()
//                .forEach(entry -> entry.getValue().get(0).getPreco().max(Comparator.comparing(entry.getValue().get(0).getPreco())));
//        System.out.println(filtrarRelatorio);

        vendasProdutoMaisCaro = filtrarRelatorio.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                //.max(Comparator.comparing(entry -> entry.getValue().get(0).getPreco())).stream().findFirst()
                .map(entry -> {
                    String categoria = entry.getKey();
                    String produto = entry.getValue().get(0).getProduto();
                    BigDecimal preco = entry.getValue().get(0).getPreco();
                    return new VendasProdutoMaisCaro(categoria, produto, preco);
                }).toList();


    }

    @Override
    public void imprimirRelatorio() {
        imprimirRelatorio.accept("\n\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA - falta retornar produto mais caro");
        vendasProdutoMaisCaro.stream().forEach(vendasProdutoMaisCaro -> imprimirRelatorio.accept(
                "\nCATEGORIA: " +vendasProdutoMaisCaro.getCategoria()+
                        "\nPRODUTO: " +vendasProdutoMaisCaro.getProduto()+
                        "\nPREÇO: " +vendasProdutoMaisCaro.getPreco()));
    }

}
