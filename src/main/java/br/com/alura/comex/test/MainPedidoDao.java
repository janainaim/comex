package br.com.alura.comex.test;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.model.*;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MainPedidoDao {

    //Criar um relatório que contenha:
    //nome do cliente;
    //a quantidade de pedidos feitos por ele;

    public static void main(String[] args) {

        Cliente plinia = new Cliente("Plinia Manai Borges", "11122233344", "11233334444",
                new Endereco("R. Alecrim Dourado", "12", "Que nasceu no campo", "Semeado", "Betim", "Minas Gerais"));
        Pedido pedido = new Pedido(LocalDate.now(), plinia, new BigDecimal("0"), TipoDeDescontoPorPedido.NENHUM);

        EntityManager entityManager = JpaUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(entityManager);
        PedidoDao pedidoDao = new PedidoDao(entityManager);

        entityManager.getTransaction().begin();

        clienteDao.cadastrarCliente(plinia);
        pedidoDao.cadastrarPedido(pedido);

        pedidoDao.relatorioDeQuantidadeDePedidosPorClientes().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
