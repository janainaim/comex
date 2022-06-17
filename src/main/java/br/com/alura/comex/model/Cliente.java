package br.com.alura.comex.model;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String cpf;

  private String celular;

  @Embedded
  private Endereco endereco;

  public Cliente() {
  }

  public Cliente(String nome, String cpf,
                 String celular, Endereco endereco) {
    this.nome = nome;
    this.cpf = cpf;
    this.celular = celular;
    this.endereco = endereco;
  }

  @Override
  public String toString() {
    return "Categoria [nome=" + nome + ", cpf=" + cpf
            + ", celular=" + celular + ", endereco=" + endereco.toString() +  "]";
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getCelular() {
    return celular;
  }

  public Endereco getEndereco() {
    return endereco;
  }
}
