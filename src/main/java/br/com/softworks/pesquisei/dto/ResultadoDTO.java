package br.com.softworks.pesquisei.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ResultadoDTO {

    private Long idPergunta;
    private Long idResposta;
    private Long idBairro;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime momento;
}
