package br.com.alura.comex.repository;

import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

  List<Categoria> findByNome(String nome);

}
