package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.PesquisaDTO;
import br.com.softworks.pesquisei.model.Pesquisa;
import org.springframework.stereotype.Component;

@Component
public class PesquisaBuilder {

    public Pesquisa build(PesquisaDTO dto) {
        return Pesquisa.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .numeroEntrevistados(dto.getNumeroEntrevistados())
                .build();
    }

    public Pesquisa build(Pesquisa pesquisa, PesquisaDTO dto) {

        pesquisa.setNome(dto.getNome());
        pesquisa.setDescricao(dto.getDescricao());
        pesquisa.setNumeroEntrevistados(dto.getNumeroEntrevistados());

        return pesquisa;
    }
}
