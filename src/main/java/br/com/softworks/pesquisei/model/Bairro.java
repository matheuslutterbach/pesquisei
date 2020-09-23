package br.com.softworks.pesquisei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_bairro")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    private String nome;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao")
    private Date alteracao;
}
