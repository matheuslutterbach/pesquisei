package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.model.Resposta;
import br.com.softworks.pesquisei.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("pergunta")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PerguntaController {

    @Autowired
    private PerguntaService service;

    @GetMapping
    public ResponseEntity<?> buscar() {
        List<Pergunta> perguntas = service.buscar();

        perguntas.forEach(pergunta -> {
            pergunta.setPesquisaId(pergunta.getPesquisa().getId());
        });

        return new ResponseEntity<>(perguntas,
                HttpStatus.OK);
    }
}
