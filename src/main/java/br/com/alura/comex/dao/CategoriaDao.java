package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;


public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrarCategoria(Categoria categoria){
        this.entityManager.persist(categoria);
    }

    public void atualizarCategoria(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void removerCategoria(Categoria categoria) {
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }



}
