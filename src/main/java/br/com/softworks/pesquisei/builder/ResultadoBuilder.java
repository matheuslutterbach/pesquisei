package br.com.softworks.pesquisei.builder;

import br.com.softworks.pesquisei.dto.ResultadoDTO;
import br.com.softworks.pesquisei.model.Resultado;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.*;

@Component
public class ResultadoBuilder {

    public List<Resultado> build(List<ResultadoDTO> dto) {

        List<Resultado> resultados = new LinkedList<>();

        dto.forEach(resultadoDTO -> {
            resultados.add(Resultado.builder()
                    .perguntaId(resultadoDTO.getIdPergunta())
                    .respostaId(resultadoDTO.getIdResposta())
                    .bairroId(resultadoDTO.getIdBairro())
                    .momento(Date.from(resultadoDTO.getMomento().atZone(ZoneId.of("Brazil/East")).toInstant()))
                    .build());
        });
        return resultados;
    }
}
