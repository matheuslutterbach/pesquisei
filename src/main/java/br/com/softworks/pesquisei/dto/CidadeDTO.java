package br.com.softworks.pesquisei.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CidadeDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String estadoSigla;
}
