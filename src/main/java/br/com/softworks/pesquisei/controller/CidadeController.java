package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.CidadeBuilder;
import br.com.softworks.pesquisei.dto.CidadeDTO;
import br.com.softworks.pesquisei.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("cidade")
@RestController
public class CidadeController {

    @Autowired private CidadeBuilder builder;
    @Autowired private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<?> buscarCidades() {
        return new ResponseEntity<>(cidadeService.buscarCidades(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CidadeDTO dto) {
        return new ResponseEntity<>(cidadeService.cadastrar(builder.build(dto)),
                HttpStatus.CREATED);
    }
}
