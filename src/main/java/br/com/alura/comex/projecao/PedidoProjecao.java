package br.com.alura.comex.projecao;

import java.math.BigDecimal;

public interface PedidoProjecao {

    String getNomeCategoria();
    int getQuantidadeProdutosVendidos();
    BigDecimal getMontanteVendido();
}
