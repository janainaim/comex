package br.com.alura.comex.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "itens_de_pedido")
public class ItemDePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "preco_unitario")
  private BigDecimal precoUnitario;

  private Long quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  private Produto produto;

  @ManyToOne(fetch = FetchType.LAZY)
  private Pedido pedido;

  private BigDecimal desconto;

  private TipoDeDescontoPorItemDePedido tipoDeDescontoPorItemDePedido;

  public ItemDePedido() {
  }

  public ItemDePedido(BigDecimal precoUnitario, Long quantidade,
                      Produto produto, Pedido pedido,
                      BigDecimal desconto,
                      TipoDeDescontoPorItemDePedido tipoDeDescontoPorItemDePedido) {
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
    this.produto = produto;
    this.pedido = pedido;
    this.desconto = desconto;
    this.tipoDeDescontoPorItemDePedido = tipoDeDescontoPorItemDePedido;
  }

  @Override
  public String toString() {
    return "Produto [precoUnitario=" + precoUnitario + ", quantidade=" + quantidade
            + ", produto=" + produto.toString() + ", pedido=" + pedido.toString()
            + ", desconto=" + desconto
            + ", tipoDeDescontoPorItemDePedido=" + tipoDeDescontoPorItemDePedido + "]";
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Long getQuantidade() {
    return quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public TipoDeDescontoPorItemDePedido getTipoDeDescontoPorItemDePedido() {
    return tipoDeDescontoPorItemDePedido;
  }
}
