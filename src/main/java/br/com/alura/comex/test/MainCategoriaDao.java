package br.com.alura.comex.test;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;

public class MainCategoriaDao {

//cadastre 3 categorias; OK
//altere 1 categoria; OK
//inative 1 categoria. OK

    public static void main(String[] args) {
        Categoria celulares1 = new Categoria("CELULARES");
        Categoria livros2 = new Categoria("LIVROS");
        Categoria carros3 = new Categoria("CARROS");


        EntityManager entityManager = JpaUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        //Cadastra 3 categorias
        categoriaDao.cadastrarCategoria(celulares1);
        categoriaDao.cadastrarCategoria(livros2);
        categoriaDao.cadastrarCategoria(carros3);

        //Apresenta as categorias cadastradas
        categoriaDao.buscarTodasAsCategorias().forEach(System.out::println);

        //Altera uma categoria
        livros2.setNome("LIVROS_ALTERADO");
        categoriaDao.atualizarCategoria(livros2);

        //Inativa uma categoria
        categoriaDao.inativarCategoria(celulares1);

        System.out.println("Resultado final:");
        categoriaDao.buscarTodasAsCategorias().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
