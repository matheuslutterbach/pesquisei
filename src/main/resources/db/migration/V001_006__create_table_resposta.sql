create TABLE IF NOT EXISTS tbl_pergunta (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pergunta                BIGINT NOT NULL,
    descricao                  VARCHAR(255),
    ordem                      INT,

   FOREIGN KEY (id_pergunta) REFERENCES tbl_pergunta(id)

)  ENGINE=INNODB;