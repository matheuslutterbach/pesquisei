package br.com.softworks.pesquisei.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Data
public class PerguntaDTO {

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    private Integer ordem;

    private List<RespostaDTO> respostas = new LinkedList<>();
}
