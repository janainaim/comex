package br.com.alura.comex.dao;

import br.com.alura.comex.vo.RelatorioDeQuantidadeDePedidosPorClienteVo;
import br.com.alura.comex.model.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager entityManager;

    public PedidoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrarPedido(Pedido pedido) {
        this.entityManager.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeQuantidadeDePedidosPorClienteVo> relatorioDeQuantidadeDePedidosPorClientes() {
        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioDeQuantidadeDePedidosPorClienteVo(" +
                "cliente.nome, " +
                "SUM(pedido)) " +
                "FROM Pedido pedido, " +
                "Cliente cliente " +
                "WHERE pedido.id = cliente.id " +
                "GROUP BY cliente.nome";
        return entityManager.createQuery(jpql, RelatorioDeQuantidadeDePedidosPorClienteVo.class)
                .getResultList();
    }
}
