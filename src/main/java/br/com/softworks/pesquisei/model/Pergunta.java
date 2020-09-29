package br.com.softworks.pesquisei.model;


import br.com.softworks.pesquisei.dto.ResultadoDTO;
import br.com.softworks.pesquisei.dto.RetornoResultadoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"pesquisa", "resposta"})
@Table(name = "tbl_pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pesquisa", nullable = false)
    private Pesquisa pesquisa;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    private Integer ordem;

    @OneToMany(mappedBy = "pergunta",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao")
    private Date alteracao;

    @Transient
    private Long pesquisaId;

    @Transient
    private List<RetornoResultadoDTO> resultados;

    @Transient
    private List<String> labels = new LinkedList<>();

    @Transient
    private List<Long> totais = new LinkedList<>();

    @Transient
    private List<BigDecimal> totaisPorcentagem = new LinkedList<>();

    public void adicionaResultados(List<RetornoResultadoDTO> resultados) {
        if (resultados == null) {
            resultados = new LinkedList<>();
        }

        resultados.addAll(resultados);
    }
}
