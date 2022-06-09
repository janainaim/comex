package br.com.alura.comex.test;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.model.*;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MainPedidoDao {

    //Criar um relatório que contenha:
    //nome do cliente;
    //a quantidade de pedidos feitos por ele;

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(entityManager);
        PedidoDao pedidoDao = new PedidoDao(entityManager);

        entityManager.getTransaction().begin();

        Cliente plinia = clienteDao.buscarClientePorId(4L);

        Pedido pedido = new Pedido(LocalDate.now(), plinia, new BigDecimal("0"), TipoDeDescontoPorPedido.NENHUM);

        pedidoDao.cadastrarPedido(pedido);

        pedidoDao.relatorioDeQuantidadeDePedidosPorClientes().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
