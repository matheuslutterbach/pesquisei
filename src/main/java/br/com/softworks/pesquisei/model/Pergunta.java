package br.com.softworks.pesquisei.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

}
