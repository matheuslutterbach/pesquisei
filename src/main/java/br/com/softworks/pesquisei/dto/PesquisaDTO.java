package br.com.softworks.pesquisei.dto;

import br.com.softworks.pesquisei.model.BairroPequisa;
import br.com.softworks.pesquisei.model.Pergunta;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
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

    List<BairroPequisa> bairroPesquisas;
}
