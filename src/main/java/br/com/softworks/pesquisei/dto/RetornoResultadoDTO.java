package br.com.softworks.pesquisei.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class RetornoResultadoDTO {

    @Id
    private Long id;
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "pergunta")
    private String pergunta;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "resposta")
    private String resposta;
    private Long total;
    @CsvBindByPosition(position = 4)
    private String momento;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "bairro")
    private String bairro;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "cidade")
    private String cidade;
}
