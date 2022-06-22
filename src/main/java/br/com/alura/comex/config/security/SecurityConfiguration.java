package br.com.alura.comex.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  @Autowired
  private AutenticacaoService autenticacaoService;

  //Configuracoes de autenticacao
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration,
                                                     ApplicationContext context,
                                                     ObjectPostProcessor<Object> objectObjectPostProcessor
                                                     ) throws Exception {
    authenticationConfiguration.authenticationManagerBuilder(objectObjectPostProcessor, context).userDetailsService(autenticacaoService)
            .passwordEncoder(new BCryptPasswordEncoder());
    return authenticationConfiguration.getAuthenticationManager();
  }


  //Configuracoes de autorizacao
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/categorias/**").permitAll()
            .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers(HttpMethod.GET, "/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM/**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }


  //Configuracoes de recursos estaticos(js, css, imagens, etc.)
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web ->  web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
  }

}
