package br.com.alura.comex.service;

import br.com.alura.comex.baseDados.ProcessadorAdapter;
import br.com.alura.comex.model.Pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessadorDeCsv extends ProcessadorAdapter {

    public List<Pedido> listarPedidos(String nomeArquivo) {
        List<Pedido> pedidos = new ArrayList<>();

        try {
            URL recursoCSV = ClassLoader.getSystemResource(nomeArquivo.concat(".csv"));

            Scanner leitorDeLinhas = new Scanner(Path.of(recursoCSV.toURI()));

            leitorDeLinhas.nextLine();

            gerarListaDePedidos(pedidos, leitorDeLinhas);

        } catch (
                URISyntaxException e) {
            throw new RuntimeException("Arquivo pedido.csv não localizado!");
        } catch (
                IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
        return pedidos;
    }

    private static void gerarListaDePedidos(List<Pedido> pedidos, Scanner leitorDeLinhas) {
        int quantidadeDeRegistros = 0;
        while (leitorDeLinhas.hasNextLine()) {
            String linha = leitorDeLinhas.nextLine();
            String[] registro = linha.split(",");

            String categoria = registro[0];
            String produto = registro[1];
            BigDecimal preco = new BigDecimal(registro[2]);
            int quantidade = Integer.parseInt(registro[3]);
            LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String cliente = registro[5];

            Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
            pedidos.add(pedido);

            quantidadeDeRegistros++;
        }
    }
}
