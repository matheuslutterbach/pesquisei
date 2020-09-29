package br.com.softworks.pesquisei.controller;


import br.com.softworks.pesquisei.builder.ResultadoBuilder;
import br.com.softworks.pesquisei.dto.ResultadoDTO;
import br.com.softworks.pesquisei.dto.RetornoResultadoDTO;
import br.com.softworks.pesquisei.service.ResultadoService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void exportCSV(@PathVariable("idPesquisa") Long idPesquisa, HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "resultado_pesquisa.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer

        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("PERGUNTA", "pergunta");
        columnMapping.put("RESPOSTA", "resposta");
        columnMapping.put("BAIRRO", "bairro");
        columnMapping.put("CIDADE", "cidade");

        HeaderColumnNameTranslateMappingStrategy<RetornoResultadoDTO> strategy = new HeaderColumnNameTranslateMappingStrategy<RetornoResultadoDTO>();
        strategy.setType(RetornoResultadoDTO.class);
        strategy.setColumnMapping(columnMapping);


        StatefulBeanToCsv<RetornoResultadoDTO> writer = new StatefulBeanToCsvBuilder<RetornoResultadoDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withMappingStrategy(strategy)
                .withOrderedResults(false)
                .build();


        writer.write(service.exportarCsv(idPesquisa));
    }


}
