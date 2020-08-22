package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.CidadeBuilder;
import br.com.softworks.pesquisei.dto.CidadeDTO;
import br.com.softworks.pesquisei.model.Cidade;
import br.com.softworks.pesquisei.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("cidade")
@RestController
public class CidadeController {

    @Autowired
    private CidadeBuilder builder;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<?> buscar() {
        return new ResponseEntity<>(cidadeService.buscar(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CidadeDTO dto) {
        return new ResponseEntity<>(cidadeService.cadastrar(builder.build(new Cidade(), dto)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody @Valid CidadeDTO dto) {
        Cidade cidade = cidadeService.buscarPorId(id);

        return new ResponseEntity<>(cidadeService.cadastrar(builder.build(cidade, dto)),
                HttpStatus.CREATED);
    }
}
