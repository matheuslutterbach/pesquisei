package br.com.softworks.pesquisei.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_resposta")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pergunta", nullable = false)
    private Pergunta pergunta;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    private Integer ordem;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao")
    private Date alteracao;

    @Transient
    private Long perguntaId;

}
