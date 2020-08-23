package br.com.softworks.pesquisei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_bairro_pesquisa")
public class BairroPequisa implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "id_pesquisa")
    private Pesquisa pesquisa;

    private BigDecimal percentual;
}
