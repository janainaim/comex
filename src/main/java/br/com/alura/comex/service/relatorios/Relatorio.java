package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public abstract class Relatorio {

    List<Pedido> pedidos = new ArrayList<>();

    public Relatorio(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Relatorio(){}

    public abstract void filtrarRelatorio();

    public abstract void imprimirRelatorio();

    public void gerarRelatorio(){
        if(pedidos == null || pedidos.isEmpty()){
            throw new IllegalArgumentException("A lista de pedidos não deve ser vazia!");
        }
        filtrarRelatorio();
        imprimirRelatorio();
    }

}
