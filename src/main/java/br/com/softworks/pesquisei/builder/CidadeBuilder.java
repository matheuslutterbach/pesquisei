package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.CidadeDTO;
import br.com.softworks.pesquisei.model.Cidade;
import br.com.softworks.pesquisei.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeBuilder {

    @Autowired
    private CidadeService cidadeService;

    public Cidade build(CidadeDTO dto) {
        return Cidade.builder()
                .nome(dto.getNome())
                .estadoSigla(dto.getEstadoSigla())
                .build();
    }

    public Cidade build(Cidade cidade, CidadeDTO dto) {
        cidade.setNome(dto.getNome());
        cidade.setEstadoSigla(dto.getEstadoSigla());

        return cidade;
    }
}
