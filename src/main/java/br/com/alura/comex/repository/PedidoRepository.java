package br.com.alura.comex.repository;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.projecao.PedidoProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT c.nome, COUNT(i.id) AS quantidadeProdutosVendidos, SUM((i.preco_unitario * i.quantidade)) AS montanteVendido " +
            "FROM pedidos " +
            "JOIN itens_pedido i " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE pedidos.id = i.pedido_id AND i.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY c.id, pedidos.id, i.id", nativeQuery = true)
    List<PedidoProjecao> gerarRelatorioDePedidosPorCategoria();
}
