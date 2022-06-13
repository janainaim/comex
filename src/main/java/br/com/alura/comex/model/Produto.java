package br.com.alura.comex.model;

import javax.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    //@Column(name = "quantidade_em_estoque")
    private Long quantidadeEmEstoque;

    @ManyToOne
    private Categoria categoria;

    public Produto() {}

    public Produto(String nome, String descricao,
                   BigDecimal precoUnitario, Long quantidadeEmEstoque, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", descricao=" + descricao +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                ", categoria=" + categoria.toString() +"]";
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Long getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
