package br.com.alura.comex.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

  @GetMapping("/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
  @CacheEvict(value = {"relatorioPorCategoria"}, allEntries = true)
  public ResponseEntity<?> cacheEvict(){
    return ResponseEntity.accepted().build();
  }


}
