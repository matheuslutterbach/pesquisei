package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.CidadeDTO;
import br.com.softworks.pesquisei.model.Cidade;
import org.springframework.stereotype.Component;

@Component
public class CidadeBuilder {

    public Cidade build(CidadeDTO dto) {
        return Cidade.builder()
                .nome(dto.getNome())
                .estadoSigla(dto.getEstadoSigla())
                .build();
    }
}
