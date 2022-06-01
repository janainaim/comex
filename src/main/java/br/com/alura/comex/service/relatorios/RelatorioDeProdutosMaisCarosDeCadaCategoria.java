package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
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

        vendasProdutoMaisCaro = filtrarRelatorio.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                //.max(Comparator.comparing(entry -> entry.getValue().get(0).getPreco())).stream().findFirst()
                .map(entry -> {
                    String categoria = entry.getKey();
                    //Cria um optional de pedido com o pedido mais caro de cada categoria
                    Optional<Pedido> maximoPorCategoria = entry.getValue().stream().max(Comparator.comparing(Pedido::getPreco));
                    String produto = maximoPorCategoria.get().getProduto();
                    BigDecimal preco = maximoPorCategoria.get().getPreco();
                    return new VendasProdutoMaisCaro(categoria, produto, preco);
                }).toList();

    }

    @Override
    public void imprimirRelatorio() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        imprimirRelatorio.accept("\n\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA");
        vendasProdutoMaisCaro.stream().forEach(vendasProdutoMaisCaro -> imprimirRelatorio.accept(
                "\nCATEGORIA: " +vendasProdutoMaisCaro.getCategoria()+
                        "\nPRODUTO: " +vendasProdutoMaisCaro.getProduto()+
                        "\nPREÇO: " +numberFormat.format(vendasProdutoMaisCaro.getPreco())));
    }

    public List<VendasProdutoMaisCaro> getVendasProdutoMaisCaro() {
        return vendasProdutoMaisCaro;
    }
}
