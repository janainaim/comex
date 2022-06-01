package br.com.alura.comex.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoBuilder {

    private String categoria;

    private String produto;

    private String cliente;

    private BigDecimal preco;

    private int quantidade;

    private LocalDate data;

    public PedidoBuilder categoria(String categoria){
        this.categoria = categoria;
        return this;
    }

    public PedidoBuilder produto(String produto){
        this.produto = produto;
        return this;
    }

    public PedidoBuilder cliente(String cliente){
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder preco(BigDecimal preco){
        this.preco = preco;
        return this;
    }

    public PedidoBuilder quantidade(int quantidade){
        this.quantidade = quantidade;
        return this;
    }


    public PedidoBuilder data(LocalDate data){
        this.data = data;
        return this;
    }

    public Pedido build(){
        return new Pedido(categoria, produto, cliente, preco, quantidade, data);
    }

}
