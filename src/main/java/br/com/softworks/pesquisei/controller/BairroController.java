package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.BairroBuilder;
import br.com.softworks.pesquisei.dto.BairroDTO;
import br.com.softworks.pesquisei.model.Bairro;
import br.com.softworks.pesquisei.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("bairro")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BairroController {

    @Autowired
    private BairroBuilder builder;
    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> buscar() {
        return new ResponseEntity<>(bairroService.buscar(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid BairroDTO dto) {
        return new ResponseEntity<>(bairroService.cadastrar(builder.build(new Bairro(), dto)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody @Valid BairroDTO dto) {
        Bairro bairro = bairroService.buscarPorId(id);

        return new ResponseEntity<>(bairroService.cadastrar(builder.build(bairro, dto)),
                HttpStatus.CREATED);
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<?> buscarPorCidade(@PathVariable("id") Long idCidade) {
        return new ResponseEntity<>(bairroService.buscarPorCidade(idCidade),
                HttpStatus.OK);
    }
}
