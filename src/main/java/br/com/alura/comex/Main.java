package br.com.alura.comex;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.ProcessadorDeCsv;
import br.com.alura.comex.service.RelatorioDeClientesFieis;
import br.com.alura.comex.service.RelatorioSintetico;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.*;

public class Main extends ProcessadorDeCsv {

    public static void main(String[] args) {


        List<Pedido> pedidos = listarPedidos("pedidos.csv");

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

        RelatorioDeClientesFieis relatorioDeClientesFieis = new RelatorioDeClientesFieis(pedidos);


        System.out.println("#### RELATÓRIO DE CLIENTES FIÉIS");
        relatorioDeClientesFieis.getQuantidadeDePedidosPorCliente().forEach((q1, q2) -> System.out.println("\n NOME: " +q1+ "\n Nº DE PEDIDOS: " +q2));

        System.out.println("\n\n");

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisBarato().getPreco().multiply(new BigDecimal(relatorioSintetico.getPedidoMaisBarato().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisCaro().getPreco().multiply(new BigDecimal(relatorioSintetico.getPedidoMaisCaro().getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.getPedidoMaisCaro().getProduto());
    }
}
