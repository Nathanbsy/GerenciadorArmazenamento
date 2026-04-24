package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    //@GetMapping("/get")
    @GetMapping("hello-world")
    public String helloWorld() {
        return entradaService.helloWorld("Nathan");
    }

    @PostMapping("/{id}")
    public String helloWorldPost(@PathVariable("id") String id, @RequestParam(value = "filter", defaultValue = "nenhum") String filtro, @RequestBody Produto body) {
        return "Hello World " + body.getNomeProduto() + id;
    }
}
