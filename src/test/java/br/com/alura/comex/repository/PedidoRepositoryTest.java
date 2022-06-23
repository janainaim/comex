package br.com.alura.comex.repository;

import br.com.alura.comex.model.*;
import br.com.alura.comex.projecao.RelatorioPedidoPorCategoriaProjecao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class PedidoRepositoryTest {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private ItemDePedidoRepository itemDePedidoRepository;

  @Test
  void deveGerarRelatorioDePedidosPorCategoria() {

    //gerarDadosParaTeste();

    List<RelatorioPedidoPorCategoriaProjecao> relatorio =
            pedidoRepository.gerarRelatorioDePedidosPorCategoria();

//    assertNotNull(categoriaList);
//    assertNotNull(produtoList);
//    assertNotNull(pedidoList);
//    assertNotNull(itemDePedidoList);

    //Verificando se o relatório retorna 2 registros
    assertEquals(2, relatorio.size());
    //Verificando se o valor dos montantes dos dois registros estão corretos
    assertEquals(new BigDecimal("700.00"), relatorio.get(0).getMontanteVendido());
    assertEquals(new BigDecimal("30000.00"), relatorio.get(1).getMontanteVendido());
    //Verificando se a quantidade de itens por pedidos está correta
    assertEquals(2, relatorio.get(0).getQuantidadeProdutosVendidos());
    assertEquals(1, relatorio.get(1).getQuantidadeProdutosVendidos());
    //Verificando se o nome da categoria dos itens do pedido estão corretas
    assertEquals("LIVROS", relatorio.get(0).getNomeCategoria());
    assertEquals("CARROS", relatorio.get(1).getNomeCategoria());

  }

  private void gerarDadosParaTeste() {

    Categoria livros =  new Categoria("LIVROS");
    Categoria carros =  new Categoria("CARROS");

    List<Categoria> categorias = List.of(livros, carros);


    Produto kindle = new Produto("Kindle",
            "Dona Amazon baixe para 0 reais .-.",
            new BigDecimal("450"),
            3L,
            categorias.get(0));
    Produto hondaFit = new Produto("Honda Fit",
            "Um carro longe de ser o Sedan dos sonhos, mas um baita carrinho",
            new BigDecimal("30.000"),
            1L,
            categorias.get(1));

    List<Produto> produtos = List.of(kindle, hondaFit);


    List<Perfil> perfil = new ArrayList<>();

    Pedido kindle1 = new Pedido(
            LocalDate.now(),
            new Cliente("Ana",
                    "11122233344",
                    "1122224444",
                    new Endereco("Rua Um",
                            "22",
                            "Centro",
                            "Catotas",
                            "São Paulo",
                            "São Paulo"),
                    new Usuario("joaninha@email.com",
                            "123456", perfil )),
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);
    Pedido kindle2 = new Pedido(
            LocalDate.now(),
            new Cliente("Ana",
                    "11122233344",
                    "1122224444",
                    new Endereco("Rua Um",
                            "22",
                            "Centro",
                            "Catotas",
                            "São Paulo",
                            "São Paulo"),
                    new Usuario("joaninha@email.com",
                            "123456", perfil )),
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);
    Pedido honda1 = new Pedido(
            LocalDate.now(),
            new Cliente("Ana",
                    "11122233344",
                    "1122224444",
                    new Endereco("Rua Um",
                            "22",
                            "Centro",
                            "Catotas",
                            "São Paulo",
                            "São Paulo"),
                    new Usuario("joaninha@email.com",
                            "123456", perfil )),
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);

    List<Pedido> pedidos = List.of(kindle1, kindle2, honda1);



    ItemDePedido itemKindlePedido1 = new ItemDePedido( new BigDecimal("450"),
            1L,
            kindle,
            kindle1,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);
    ItemDePedido itemKindlePedido2 = new ItemDePedido( new BigDecimal("450"),
            2L,
            kindle,
            kindle2,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);
    ItemDePedido itemHondaPedido3 = new ItemDePedido( new BigDecimal("450"),
            1L,
            hondaFit,
            honda1,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);

    List<ItemDePedido> itensDePedidos = List.of(itemKindlePedido1, itemKindlePedido2, itemHondaPedido3);

  }

}