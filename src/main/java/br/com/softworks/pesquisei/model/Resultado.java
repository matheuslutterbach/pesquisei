package br.com.softworks.pesquisei.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pergunta")
    private Long perguntaId;

    @Column(name = "id_resposta")
    private Long respostaId;

    @Column(name = "id_bairro")
    private Long bairroId;

    private Date momento;
}
