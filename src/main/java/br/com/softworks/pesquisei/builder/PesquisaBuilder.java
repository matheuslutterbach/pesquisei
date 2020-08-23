package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.BairroPesquisaDTO;
import br.com.softworks.pesquisei.dto.PerguntaDTO;
import br.com.softworks.pesquisei.dto.PesquisaDTO;
import br.com.softworks.pesquisei.dto.RespostaDTO;
import br.com.softworks.pesquisei.model.*;
import br.com.softworks.pesquisei.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class PesquisaBuilder {

    @Autowired
    private BairroService bairroService;

    public Pesquisa build(Pesquisa pesquisa, PesquisaDTO dto) {

        pesquisa.setNome(dto.getNome());
        pesquisa.setDescricao(dto.getDescricao());
        pesquisa.setNumeroEntrevistados(dto.getNumeroEntrevistados());

        Set<BairroPequisa> bairroPesquisas = new HashSet<>();
        for (BairroPesquisaDTO bp : dto.getBairrosPesquisa()) {
            Bairro bairro = bairroService.buscarPorId(bp.getBairroId());

            BairroPequisa bairroPesquisa = new BairroPequisa();
            bairroPesquisa.setBairro(bairro);
            bairroPesquisa.setPercentual(bp.getPercentual());
            bairroPesquisa.setPesquisa(pesquisa);

            bairroPesquisas.add(bairroPesquisa);
        }

        List<Pergunta> perguntas = new LinkedList<>();
        for (PerguntaDTO perguntaDTO : dto.getPerguntas()) {
            Pergunta pergunta = new Pergunta();
            pergunta.setDescricao(perguntaDTO.getDescricao());
            pergunta.setOrdem(perguntaDTO.getOrdem());
            pergunta.setPesquisa(pesquisa);

            List<Resposta> respostas = new LinkedList<>();
            for (RespostaDTO respostaDTO : perguntaDTO.getRespostas()) {
                Resposta resposta = new Resposta();
                resposta.setDescricao(respostaDTO.getDescricao());
                resposta.setOrdem(respostaDTO.getOrdem());
                resposta.setPergunta(pergunta);
                respostas.add(resposta);
            }
            pergunta.setRespostas(respostas);
            perguntas.add(pergunta);
        }

        pesquisa.setPerguntas(perguntas);
        pesquisa.setBairroPesquisas(bairroPesquisas);

        return pesquisa;
    }
}
