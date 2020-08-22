package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.BairroDTO;
import br.com.softworks.pesquisei.model.Bairro;
import br.com.softworks.pesquisei.model.Cidade;
import br.com.softworks.pesquisei.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BairroBuilder {

    @Autowired
    private CidadeService cidadeService;

    public Bairro build(BairroDTO dto) {
        Cidade cidade = cidadeService.buscarPorId(dto.getIdCidade());

        return Bairro.builder()
                .nome(dto.getNome())
                .cidade(cidade)
                .build();
    }

    public Bairro build(Bairro bairro, BairroDTO dto) {
        Cidade cidade = cidadeService.buscarPorId(dto.getIdCidade());

        bairro.setCidade(cidade);
        bairro.setNome(dto.getNome());

        return bairro;
    }
}
