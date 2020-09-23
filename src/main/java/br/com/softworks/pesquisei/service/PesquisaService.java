package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.dto.RetornoResultadoDTO;
import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.model.BairroPequisa;
import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.model.Pesquisa;
import br.com.softworks.pesquisei.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository pesquisaRepository;
    @Autowired
    private ResultadoService resultadoService;

    public List<Pesquisa> buscar() {
        return pesquisaRepository.findAll();
    }

    public Pesquisa cadastrar(Pesquisa pesquisa) {
        pesquisa.setAlteracao(new Date());
        pesquisa.setDataCricao(new Date());

        BigDecimal percentualTotal = pesquisa.getBairroPesquisas()
                .stream()
                .map(BairroPequisa::getPercentual)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (percentualTotal.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new ServiceException("Percentual ultrapassou 100%");
        }

        return pesquisaRepository.save(pesquisa);
    }


    public Pesquisa buscarPorId(Long id) {
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(id);

        return pesquisa.orElseThrow(() -> new ServiceException("Pesquisa", id));
    }

    public Pesquisa buscarComResultado(Long id) {
        Pesquisa pesquisa = buscarPorId(id);

        for (Pergunta pergunta : pesquisa.getPerguntas()) {
            List<RetornoResultadoDTO> resultadoDTOS = resultadoService.buscarConsolidadoPergunta(pergunta.getId());
            pergunta.setResultados(resultadoDTOS);


            pergunta.getResultados().forEach(resultadoDTO -> {
                pergunta.getLabels().add(resultadoDTO.getResposta());
            });

            pergunta.getResultados().forEach(resultadoDTO -> {
                pergunta.getTotais().add(resultadoDTO.getTotal());
            });

        }
        return pesquisa;
    }
}
