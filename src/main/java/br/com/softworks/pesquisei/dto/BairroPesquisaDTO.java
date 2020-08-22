package br.com.softworks.pesquisei.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BairroPesquisaDTO {

    private Long bairroId;
    private BigDecimal percentual;
}
