package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.model.Resposta;
import br.com.softworks.pesquisei.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("resposta")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RespostaController {

    @Autowired
    private RespostaService service;

    @GetMapping
    public ResponseEntity<?> buscar() {
        List<Resposta> respostas = service.buscar();

        respostas.forEach(resposta -> {
            resposta.setPerguntaId(resposta.getPergunta().getId());
        });

        return new ResponseEntity<>(respostas,
                HttpStatus.OK);
    }
}
