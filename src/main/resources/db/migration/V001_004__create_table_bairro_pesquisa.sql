create TABLE IF NOT EXISTS tbl_bairro_pesquisa (
    id_bairro                   BIGINT NOT NULL,
    id_pesquisa                 BIGINT NOT NULL,
    quantidade                  INT,
     PRIMARY KEY (id_bairro, id_pesquisa),
     FOREIGN KEY (id_bairro) REFERENCES tbl_bairro(id),
     FOREIGN KEY (id_pesquisa) REFERENCES tbl_pesquisa(id)
)  ENGINE=INNODB;