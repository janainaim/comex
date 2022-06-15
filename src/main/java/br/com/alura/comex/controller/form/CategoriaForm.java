package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusDaCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {

    @NotNull @NotEmpty @Length(min=2)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        return new Categoria(nome);
    }
}
