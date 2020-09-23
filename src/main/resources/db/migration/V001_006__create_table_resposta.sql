create TABLE IF NOT EXISTS tbl_resposta (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pergunta                BIGINT NOT NULL,
    descricao                  VARCHAR(255),
    ordem                      INT,
    data_alteracao             DATETIME  NOT NULL,

   FOREIGN KEY (id_pergunta) REFERENCES tbl_pergunta(id)

)  ENGINE=INNODB;