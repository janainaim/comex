package br.com.alura.comex;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        //Troca o dataSet pelo new
        ProcessadorAdapter processadorAdapter = new ProcessadorDeJson();

        List<Pedido> pedidos = processadorAdapter.listarPedidos("pedidos");

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

        RelatorioDeClientesFieis relatorioDeClientesFieis = new RelatorioDeClientesFieis(pedidos);


        System.out.println("#### RELATÓRIO DE CLIENTES FIÉIS");
        relatorioDeClientesFieis.getQuantidadeDePedidosPorCliente().forEach((q1, q2) -> System.out.println("\n NOME: " +q1+ "\n Nº DE PEDIDOS: " +q2));

        System.out.println("\n");

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.printf("- MONTANTE DE VENDAS: %s\n", currencyInstance.format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));

        Pedido pedidoMaisBarato = relatorioSintetico.getPedidoMaisBarato();
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", currencyInstance.format(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisBarato.getProduto());

        Pedido pedidoMaisCaro = relatorioSintetico.getPedidoMaisCaro();
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", currencyInstance.format(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisCaro.getProduto());
    }
}
