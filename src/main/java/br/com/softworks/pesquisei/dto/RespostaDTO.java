package br.com.softworks.pesquisei.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RespostaDTO {

    @NotNull
    private Long idPergunta;

    @NotBlank
    @Size(max = 256)
    private String descricao;

    @NotNull
    private Integer ordem;

}
