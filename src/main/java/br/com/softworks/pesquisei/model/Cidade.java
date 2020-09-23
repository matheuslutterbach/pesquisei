package br.com.softworks.pesquisei.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Size(max = 2)
    private String estadoSigla;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao")
    private Date alteracao;
}
