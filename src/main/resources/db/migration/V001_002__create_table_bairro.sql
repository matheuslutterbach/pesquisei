create TABLE IF NOT EXISTS tbl_bairro (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    id_cidade       BIGINT       NOT NULL,
    data_alteracao  DATETIME     NOT NULL,

    FOREIGN KEY (id_cidade) REFERENCES tbl_cidade(id)
)  ENGINE=INNODB;