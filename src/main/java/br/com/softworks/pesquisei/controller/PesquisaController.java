package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.PesquisaBuilder;
import br.com.softworks.pesquisei.dto.PesquisaDTO;
import br.com.softworks.pesquisei.model.Pesquisa;
import br.com.softworks.pesquisei.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("pesquisa")
@RestController
public class PesquisaController {

    @Autowired private PesquisaBuilder builder;
    @Autowired private PesquisaService pesquisaService;

    @GetMapping
    public ResponseEntity<?> buscar() {
        return new ResponseEntity<>(pesquisaService.buscar(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PesquisaDTO dto) {
        return new ResponseEntity<>(pesquisaService.cadastrar(builder.build(dto)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody @Valid PesquisaDTO dto) {
        Pesquisa pesquisa = pesquisaService.buscarPorId(id);

        return new ResponseEntity<>(pesquisaService.cadastrar(builder.build(pesquisa, dto)),
                HttpStatus.CREATED);
    }
}
