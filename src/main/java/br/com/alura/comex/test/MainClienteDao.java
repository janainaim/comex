package br.com.alura.comex.test;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MainClienteDao {

//cadastre 3 clientes; OK
//pesquise clientes pelo nome. OK se for literal

    public static void main(String[] args) {
        Cliente ana1 = new Cliente("Ana de Oliveira",
                "56789011121",
                "11233334444",
                new Endereco("R. José da Silva",
                        "21",
                        "Apto",
                        "Lapa",
                        "São Paulo",
                        "São Paulo"));

        Cliente jose2 = new Cliente("José da Silva",
                "33455556666",
                "123",
                new Endereco("R. Ana de Oliveira",
                        "12",
                        "Casa",
                        "Lagoa",
                        "Rio de Janeiro",
                        "Rio de Janeiro"));

        Cliente marcia3 = new Cliente("Marcia Matos Moreira",
                "12345678900",
                "00122223333",
                new Endereco("R. Lindeza de dois cantos",
                        "222",
                        "Apto",
                        "Lindeza",
                        "Recife",
                        "Pernambuco"));

        Cliente plinia4 = new Cliente("Plinia Manai Borges",
                "11122233344",
                "11233334444",
                new Endereco(
                        "R. Alecrim Dourado",
                        "12",
                        "Que nasceu no campo",
                        "Semeado",
                        "Betim",
                        "Minas Gerais"));

        EntityManager entityManager = JpaUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(entityManager);

        entityManager.getTransaction().begin();

        //Cadastra três clientes
        clienteDao.cadastrarCliente(ana1);
        clienteDao.cadastrarCliente(jose2);
        clienteDao.cadastrarCliente(marcia3);
        clienteDao.cadastrarCliente(plinia4);

        //Apresenta todos os clientes existentes
        clienteDao.buscarTodosOsClientes().forEach(System.out::println);

        //Procura um/uma cliente pelo nome
        List<Cliente> buscaPorNome = clienteDao.buscarTodosOsClientesPorNome("Marcia Matos Moreira");

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println(buscaPorNome);

    }

}
