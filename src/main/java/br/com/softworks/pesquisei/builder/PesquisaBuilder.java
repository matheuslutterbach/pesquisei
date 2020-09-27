package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.PesquisaDTO;
import br.com.softworks.pesquisei.model.*;
import br.com.softworks.pesquisei.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PesquisaBuilder {

    @Autowired
    private BairroService bairroService;

    public Pesquisa build(Pesquisa pesquisa, PesquisaDTO dto) {

        pesquisa.setNome(dto.getNome());
        pesquisa.setDescricao(dto.getDescricao());
        pesquisa.setNumeroEntrevistados(dto.getNumeroEntrevistados());

        Set<BairroPequisa> bairroPesquisas = new HashSet<>();
        dto.getBairrosPesquisa().forEach(bp -> {
            Bairro bairro = bairroService.buscarPorId(bp.getBairroId());
            BairroPequisa bairroPesquisa = new BairroPequisa();
            bairroPesquisa.setBairro(bairro);
            bairroPesquisa.setQuantidade(bp.getQuantidade());
            bairroPesquisa.setPesquisa(pesquisa);
            bairroPesquisas.add(bairroPesquisa);
        });

        List<Pergunta> perguntas = new LinkedList<>();

        dto.getPerguntas().forEach(perguntaDTO -> {
            Pergunta pergunta = new Pergunta();
            pergunta.setDescricao(perguntaDTO.getDescricao());
            pergunta.setOrdem(perguntaDTO.getOrdem());
            pergunta.setPesquisa(pesquisa);
            pergunta.setAlteracao(new Date());

            List<Resposta> respostas = new LinkedList<>();
            perguntaDTO.getRespostas().forEach(respostaDTO -> {
                Resposta resposta = new Resposta();
                resposta.setDescricao(respostaDTO.getDescricao());
                resposta.setOrdem(respostaDTO.getOrdem());
                resposta.setPergunta(pergunta);
                resposta.setAlteracao(new Date());
                respostas.add(resposta);
            });

            pergunta.setRespostas(respostas);
            perguntas.add(pergunta);
        });

        pesquisa.setPerguntas(perguntas);
        pesquisa.setBairroPesquisas(bairroPesquisas);

        return pesquisa;
    }
}
