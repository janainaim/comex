package br.com.alura.comex.model;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusDaCategoria status;

    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
        this.status = StatusDaCategoria.ATIVO;
    }

    public String getNome() {
        return nome;
    }

    public StatusDaCategoria getStatus() {
        return status;
    }

}
