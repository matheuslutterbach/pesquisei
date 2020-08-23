package br.com.softworks.pesquisei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "bairroPesquisas")
@Table(name = "tbl_pesquisa")
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCricao;

    @Column(name = "numero_entrevistados")
    private Integer numeroEntrevistados;

    @OneToMany(mappedBy = "pesquisa",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pergunta> perguntas;

    @OneToMany(mappedBy = "pesquisa",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BairroPequisa> bairroPesquisas;
}
