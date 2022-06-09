package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusDaCategoria;

import javax.persistence.EntityManager;
import java.util.List;


public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Categoria buscarCategoriaPorId(Long id){
        return entityManager.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodasAsCategorias(){
        String jpql = "SELECT p FROM Categoria p";
        return entityManager.createQuery(jpql, Categoria.class).getResultList();
    }

    public List<Categoria> buscarTodasAsCategoriasInativas(){
        String jpql = "SELECT p FROM Categoria p WHERE p.status == INATIVO";
        return entityManager.createQuery(jpql, Categoria.class).getResultList();
    }

    public void cadastrarCategoria(Categoria categoria){
        this.entityManager.persist(categoria);
    }

    public void atualizarCategoria(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void inativarCategoria(Categoria categoria){
        this.entityManager.merge(categoria);
        categoria.setStatus(StatusDaCategoria.INATIVO);
        this.entityManager.merge(categoria);
    }

    public void removerCategoria(Categoria categoria) {
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }



}
