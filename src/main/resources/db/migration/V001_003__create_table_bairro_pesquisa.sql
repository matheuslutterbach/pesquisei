create TABLE IF NOT EXISTS tbl_bairro_pesquisa (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                       VARCHAR(255) NOT NULL,
    descricao                  VARCHAR(255),
    percentual                 DECIMAL
)  ENGINE=INNODB;