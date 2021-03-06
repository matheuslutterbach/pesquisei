create TABLE IF NOT EXISTS tbl_pergunta (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pesquisa                BIGINT NOT NULL,
    descricao                  VARCHAR(255),
    ordem                      INT,
    data_alteracao             DATETIME  NOT NULL,

   FOREIGN KEY (id_pesquisa) REFERENCES tbl_pesquisa(id)

)  ENGINE=INNODB;