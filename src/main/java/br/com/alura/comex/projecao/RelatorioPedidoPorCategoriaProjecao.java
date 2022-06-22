package br.com.alura.comex.projecao;

import java.math.BigDecimal;

public interface RelatorioPedidoPorCategoriaProjecao {

  String getNomeCategoria();

  int getQuantidadeProdutosVendidos();

  BigDecimal getMontanteVendido();
}
