package br.com.alura.comex.repository;

import br.com.alura.comex.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>,
        JpaSpecificationExecutor<Produto>, JpaRepository<Produto, Long> {
}
