create TABLE IF NOT EXISTS tbl_resultado (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pergunta                BIGINT NOT NULL,
    id_resposta                BIGINT,
    id_bairro                  BIGINT,
    momento                    timestamp
)  ENGINE=INNODB;