package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.BairroPesquisaDTO;
import br.com.softworks.pesquisei.dto.PesquisaDTO;
import br.com.softworks.pesquisei.model.Bairro;
import br.com.softworks.pesquisei.model.BairroPequisa;
import br.com.softworks.pesquisei.model.Pesquisa;
import br.com.softworks.pesquisei.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PesquisaBuilder {

    @Autowired
    private BairroService bairroService;

    public Pesquisa build(Pesquisa pesquisa, PesquisaDTO dto) {

        pesquisa.setNome(dto.getNome());
        pesquisa.setDescricao(dto.getDescricao());
        pesquisa.setNumeroEntrevistados(dto.getNumeroEntrevistados());

        List<BairroPequisa> bairroPesquisas = new LinkedList<>();
        for (BairroPesquisaDTO bp : dto.getBairrosPesquisa()) {
            Bairro bairro = bairroService.buscarPorId(bp.getBairroId());

            BairroPequisa bairroPesquisa = new BairroPequisa();
            bairroPesquisa.setBairro(bairro);
            bairroPesquisa.setPercentual(bp.getPercentual());
            bairroPesquisa.setPesquisa(pesquisa);

            bairroPesquisas.add(bairroPesquisa);
        }

        pesquisa.setBairroPesquisas(bairroPesquisas);

        return pesquisa;
    }
}
