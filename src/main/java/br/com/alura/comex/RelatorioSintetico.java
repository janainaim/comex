package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelatorioSintetico {


    int totalDeProdutosVendidos = 0;
    int totalDePedidosRealizados = 0;
    BigDecimal montanteDeVendas = BigDecimal.ZERO;
    Pedido pedidoMaisBarato = null;
    Pedido pedidoMaisCaro = null;
    CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
    int totalDeCategorias = 0;



    public RelatorioSintetico(List<Pedido> pedidos){
//        for (int i = 0; i < pedidos.size(); i++) {
//            Pedido pedidoAtual = pedidos.get(i);
//
//            if (pedidoAtual == null) {
//                break;
//            }

//            if (pedidoMaisBarato == null ||
//                    pedidoAtual.getPreco()
//                            .multiply(new BigDecimal(pedidoAtual.getQuantidade()))
//                            .compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
//                pedidoMaisBarato = pedidoAtual;
//            }


            pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))).get();

            //pedidos.stream().min(p -> p.getPreco().multiply(p.getQuantidade()).)



//            pedidoMaisBarato = pedidos.stream().min(p -> p.getPreco()
//                    .multiply(new BigDecimal(p.getQuantidade()));

//            if (pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
//                pedidoMaisCaro = pedidoAtual;
//            }
            pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))).get();

            //montanteDeVendas = montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
            montanteDeVendas = pedidos.stream()
                    .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            //totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();

            //totalDePedidosRealizados++;
            totalDePedidosRealizados = pedidos.size();


//            if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
//                totalDeCategorias++;
//                categoriasProcessadas.add(pedidoAtual.getCategoria());
//            }
            //contar todas as categorias diferentes

            totalDeCategorias = (int) pedidos.stream().map(Pedido::getCategoria).distinct().count();
        }
//    }

    public int getTotalDeProdutosVendidos() {
        return totalDeProdutosVendidos;
    }

    public void setTotalDeProdutosVendidos(int totalDeProdutosVendidos) {
        this.totalDeProdutosVendidos = totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public void setTotalDePedidosRealizados(int totalDePedidosRealizados) {
        this.totalDePedidosRealizados = totalDePedidosRealizados;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public void setMontanteDeVendas(BigDecimal montanteDeVendas) {
        this.montanteDeVendas = montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }

    public void setPedidoMaisBarato(Pedido pedidoMaisBarato) {
        this.pedidoMaisBarato = pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }

    public void setPedidoMaisCaro(Pedido pedidoMaisCaro) {
        this.pedidoMaisCaro = pedidoMaisCaro;
    }

    public CategoriasProcessadas getCategoriasProcessadas() {
        return categoriasProcessadas;
    }

    public void setCategoriasProcessadas(CategoriasProcessadas categoriasProcessadas) {
        this.categoriasProcessadas = categoriasProcessadas;
    }

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }

    public void setTotalDeCategorias(int totalDeCategorias) {
        this.totalDeCategorias = totalDeCategorias;
    }
}
