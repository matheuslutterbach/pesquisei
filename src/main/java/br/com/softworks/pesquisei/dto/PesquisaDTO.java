package br.com.softworks.pesquisei.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Data
public class PesquisaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String descricao;

    private Integer numeroEntrevistados;

    private List<BairroPesquisaDTO> bairrosPesquisa = new LinkedList<>();

    private List<PerguntaDTO> perguntas = new LinkedList<>();
}
