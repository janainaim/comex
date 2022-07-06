package br.com.alura.comex.infra.usuario;

import br.com.alura.comex.aplicacao.usuario.TokenService;
import br.com.alura.comex.dominio.usuario.TokenDto;
import br.com.alura.comex.dominio.cliente.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
    UsernamePasswordAuthenticationToken dadosLogin = form.converter();

    try {
      Authentication authentication = authenticationManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    } catch (AuthenticationException authenticationException) {
        throw new BadCredentialsException("Não foi possível criar o token");
      }

  }

}
