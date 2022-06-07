package br.com.alura.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @ManyToOne
    private Cliente cliente;

    private BigDecimal desconto;

    private TipoDeDescontoPorPedido tipoDeDescontoPorPedido;

    public Pedido() {
    }

    public Pedido(LocalDate data, Cliente cliente,
                  BigDecimal desconto, TipoDeDescontoPorPedido tipoDeDescontoPorPedido) {
        this.data = data;
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDeDescontoPorPedido = tipoDeDescontoPorPedido;
    }

    public LocalDate getData() {
        return data;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDeDescontoPorPedido getTipoDeDescontoPorPedido() {
        return tipoDeDescontoPorPedido;
    }
}
