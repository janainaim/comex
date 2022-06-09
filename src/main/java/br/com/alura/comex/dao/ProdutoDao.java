package br.com.alura.comex.dao;

import br.com.alura.comex.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Produto buscarProdutoPorId(Long id){
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarTodosOsProdutos(){
        String jpql = "SELECT p FROM Produto p";
        return entityManager.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarTodosOsProdutosIndisponiveis(){
        String jpql = "SELECT p FROM Produto p WHERE p.quantidadeEmEstoque = 0";
        return entityManager.createQuery(jpql, Produto.class).getResultList();
    }

    public void cadastrarProduto(Produto produto){
        this.entityManager.persist(produto);
    }

    public void atualizarProduto(Produto produto) {
        this.entityManager.merge(produto);
    }

    public void removerProduto(Produto produto) {
        produto = entityManager.merge(produto);
        this.entityManager.remove(produto);
    }

}
