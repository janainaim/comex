package br.com.alura.comex.dominio.itemDePedido;

import br.com.alura.comex.dominio.itemDePedido.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {



}
