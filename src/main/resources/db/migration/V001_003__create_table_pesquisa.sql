create TABLE IF NOT EXISTS tbl_pesquisa (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                       VARCHAR(255) NOT NULL,
    descricao                  VARCHAR(255),
    data_cricao                DATETIME,
    numero_entrevistados       INT,
    data_alteracao             DATETIME  NOT NULL

)  ENGINE=INNODB;