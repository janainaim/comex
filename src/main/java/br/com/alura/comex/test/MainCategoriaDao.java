package br.com.alura.comex.test;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;

public class MainCategoriaDao {

//cadastre 3 categorias;
//altere 1 categoria;
//inative 1 categoria.

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");

        EntityManager entityManager = JpaUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrarCategoria(celulares);

        categoriaDao.buscarTodasAsCategorias().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
