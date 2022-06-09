package br.com.alura.comex.test;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainProdutoDao {

//cadastre 2 produtos com quantidade em estoque e 2 produtos com estoque zerado; OK
//liste os produtos indisponíveis; OK

    public static void main(String[] args) {

        Categoria livros =  new Categoria("LIVROS");
        Categoria papelaria =   new Categoria("PAPELARIA PREMIUM");

        Produto produto1estoque = new Produto("Kindle",
                "Dona Amazon precisa fazer uma promoção dele logo .-.",
                new BigDecimal("450"),
                16L,
                livros);

        Produto produto2estoque = new Produto("Clean Code",
                "Algo que estou querendo ler no Kindle, mas a dona Amazon não colabora .-.",
                new BigDecimal("70"),
                12L,
                livros);

        Produto produto3semEstoque = new Produto("Livro da Aprendizagem perfeita",
                "Com ele todo o conhecimento do mundo é absorvido em apenas uma leitura",
                new BigDecimal("100000000000"),
                0L,
                livros);

        Produto produto4semEstoque = new Produto("Mochila Voadora",
                "Basicamente poder voar por aí xD",
                new BigDecimal("324234234"),
                0L,
                 papelaria);

        EntityManager entityManager = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrarCategoria(livros);
        categoriaDao.cadastrarCategoria(papelaria);

        //Cadastra 2 produtos com estoque
        produtoDao.cadastrarProduto(produto1estoque);
        produtoDao.cadastrarProduto(produto2estoque);

        //Cadastra 2 produtos sem estoque
        produtoDao.cadastrarProduto(produto3semEstoque);
        produtoDao.cadastrarProduto(produto4semEstoque);


        //Busca os produtos indisponíveis (sem estoque)
        produtoDao.buscarTodosOsProdutosIndisponiveis().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
