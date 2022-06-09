package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {

    private EntityManager entityManager;

    public ClienteDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Cliente buscarClientePorId(Long id){
        return entityManager.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodosOsClientes(){
        String jpql = "SELECT p FROM Cliente p";
        return entityManager.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> buscarTodosOsClientesPorNome(String nome){
        String jpql = "SELECT p FROM Cliente p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public void cadastrarCliente(Cliente cliente){
        this.entityManager.persist(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    public void removerCliente(Categoria cliente) {
        cliente = entityManager.merge(cliente);
        this.entityManager.remove(cliente);
    }

}
