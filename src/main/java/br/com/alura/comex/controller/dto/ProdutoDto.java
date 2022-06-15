package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private Long quantidadeEmEstoque;

    private Long idCategoria;

    private String nomeCategoria;


    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
        this.idCategoria = produto.getCategoria().getId();
        this.nomeCategoria = produto.getCategoria().getNome();
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public static List<ProdutoDto> converter(Page<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }
}
