package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class UsuarioForm {

  @NotEmpty
  @NotNull
  private String email;

  @NotEmpty @NotNull
  private String senha;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Usuario converter(UsuarioRepository usuarioRepository){
    return new Usuario(email, senha, new ArrayList<>());
  }

}
