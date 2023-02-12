package com.springframework.boot.embeddedinfinispan.controller;

import com.springframework.boot.embeddedinfinispan.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
public class CacheController {

  @Autowired
  private CacheService service;

  @GetMapping("{key}")
  public String getName(@PathVariable String key){
    return service.getName(key);
  }

  @PostMapping("/{key}/{name}")
  public String cache(@PathVariable String key, @PathVariable String name){
    return service.addName(key, name);
  }
}
