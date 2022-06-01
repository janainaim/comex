package br.com.alura.comex;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.processador.ProcessadorDeJson;
import br.com.alura.comex.service.relatorios.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {


        //Seleciona classes CSV, JSON e XML no new
        ProcessadorAdapter processadorAdapter = new ProcessadorDeJson();

        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());

        //Extrações para variáveis
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        Pedido pedidoMaisBarato = relatorioSintetico.getPedidoMaisBarato();
        Pedido pedidoMaisCaro = relatorioSintetico.getPedidoMaisCaro();

        System.out.printf("- MONTANTE DE VENDAS: %s\n", currencyInstance.format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));

        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", currencyInstance.format(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisBarato.getProduto());

        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", currencyInstance.format(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisCaro.getProduto());


        //Relatórios para teste

        Consumer<String> teste = null;

        //#### RELATÓRIO DE CLIENTES FIÉIS
        new RelatorioDeClientesFieis(pedidos, System.out::println).gerarRelatorio();

        //#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS
        new RelatorioDeProdutosMaisVendidos(pedidos, System.out::println).gerarRelatorio();

        //#### RELATÓRIO DE VENDAS POR CATEGORIA
        new RelatorioDeVendasPorCategoria(pedidos, System.out::println).gerarRelatorio();

        //#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA
        new RelatorioDeProdutosMaisCarosDeCadaCategoria(pedidos, System.out::println).gerarRelatorio();

        //#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS
        new RelatorioDeClientesMaisLucrativos(pedidos, System.out::println).gerarRelatorio();


     }

}
