package br.com.softworks.pesquisei.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class RetornoResultadoDTO {

    @Id
    private Long id;
    private String pergunta;
    private String resposta;
    private Long total;
    private String momento;
    private String bairro;
    private String cidade;
}
