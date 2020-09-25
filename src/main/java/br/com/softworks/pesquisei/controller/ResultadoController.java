package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.ResultadoBuilder;
import br.com.softworks.pesquisei.dto.ResultadoDTO;
import br.com.softworks.pesquisei.service.ResultadoService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping("resultado")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResultadoController {

    @Autowired
    private ResultadoBuilder builder;
    @Autowired
    private ResultadoService service;

    @GetMapping
    public ResponseEntity<?> buscar() {
        return new ResponseEntity<>(service.buscar(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid List<ResultadoDTO> dto) {
        return new ResponseEntity<>(service.cadastrar(builder.build(dto)),
                HttpStatus.CREATED);
    }


    @GetMapping("/pergunta/{idPergunta}/consolidado")
    public ResponseEntity<?> buscarConsolidado(@PathVariable("idPergunta") Long idPergunta) {
        return new ResponseEntity<>(service.buscarConsolidadoPergunta(idPergunta),
                HttpStatus.CREATED);
    }

    @GetMapping("/pergunta/{idPergunta}")
    public ResponseEntity<?> buscarResultados(@PathVariable("idPergunta") Long idPergunta) {
        return new ResponseEntity<>(service.buscarResultados(idPergunta),
                HttpStatus.CREATED);
    }

    @GetMapping("/pesquisa/{idPesquisa}/exportar")
    public void exportarXlsx(HttpServletResponse response, @PathVariable("idPesquisa") Long idPesquisa) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=resultado.xlsx; charset=utf-8");
        ByteArrayInputStream stream = service.exportarXlsx(idPesquisa);
        IOUtils.copy(stream, response.getOutputStream());
    }
}
