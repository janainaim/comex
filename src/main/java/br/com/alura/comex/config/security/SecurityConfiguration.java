package br.com.alura.comex.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  //Configuracoes de autenticacao
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
//  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

//  @Bean
//  AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//    return builder.userDetailsService(userDetailsService).passwordEncoder(encoder()).and().build();
//  }
//
//  @Bean
//  public PasswordEncoder encoder() {
//    return new BCryptPasswordEncoder();
//  }


//  //@Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/api/categorias?=/").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/produtos").permitAll();
//            /*.antMatchers(HttpMethod.POST, "/auth").permitAll()
//            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
//            .anyRequest().authenticated()
//            .and().csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);*/
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/categorias/**").permitAll()
            .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }


//  //Configuracoes de recursos estaticos(js, css, imagens, etc.)
//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//  }

}
