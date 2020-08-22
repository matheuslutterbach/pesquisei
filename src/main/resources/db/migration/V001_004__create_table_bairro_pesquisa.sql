create TABLE IF NOT EXISTS tbl_bairro_pesquisa (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_bairro                   BIGINT NOT NULL,
    id_pesquisa                 BIGINT NOT NULL,
    percentual                  DECIMAL,

     FOREIGN KEY (id_bairro) REFERENCES tbl_bairro(id),
     FOREIGN KEY (id_pesquisa) REFERENCES tbl_pesquisa(id)
)  ENGINE=INNODB;