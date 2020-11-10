package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.dto.RetornoResultadoDTO;
import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.model.BairroPequisa;
import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.model.Pesquisa;
import br.com.softworks.pesquisei.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        return pesquisaRepository.save(pesquisa);
    }


    public Pesquisa buscarPorId(Long id) {
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(id);

        return pesquisa.orElseThrow(() -> new ServiceException("Pesquisa", id));
    }

    public Pesquisa buscarComResultado(Long id, Long idBairro) {
        Pesquisa pesquisa = buscarPorId(id);

        List<RetornoResultadoDTO> resultadoDTOS;
        for (Pergunta pergunta : pesquisa.getPerguntas()) {
            if (Objects.isNull(idBairro)) {
                resultadoDTOS =
                        resultadoService.buscarConsolidadoPergunta(pergunta.getId());
            } else {
                resultadoDTOS =
                        resultadoService.buscarConsolidadoPerguntaBairro(pergunta.getId(), idBairro);
            }

            pergunta.setResultados(resultadoDTOS);


            pergunta.getResultados().forEach(resultadoDTO -> {
                pergunta.getLabels().add(resultadoDTO.getResposta());
            });

            pergunta.getResultados().forEach(resultadoDTO -> {
                pergunta.getTotais().add(resultadoDTO.getTotal());
            });


            Long totalGeral = pergunta.getTotais().stream().reduce(0L, Long::sum);

            pergunta.getTotais().forEach(total -> pergunta.getTotaisPorcentagem().add(BigDecimal.valueOf(total)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalGeral), 2, RoundingMode.HALF_EVEN)));
        }
        return pesquisa;
    }


    public Integer buscarNumeroPerguntas(Long idPesquisa) {
        Pesquisa pesquisa = buscarPorId(idPesquisa);

        return pesquisa.getPerguntas().size();
    }


    @Transactional
    public void remover(Long idPesquisa) {
        Pesquisa pesquisa = buscarPorId(idPesquisa);

        for (Pergunta pergunta : pesquisa.getPerguntas()) {
            resultadoService.deletarPorPergunta(pergunta);
        }

        pesquisaRepository.delete(pesquisa);
    }
}
