package br.com.softworks.pesquisei.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "pesquisa",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Pergunta> perguntas;

    @OneToMany(mappedBy = "pesquisa")
    List<BairroPequisa> bairroPesquisas;
}