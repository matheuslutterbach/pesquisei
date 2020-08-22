CREATE TABLE IF NOT EXISTS tbl_cidade (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    estado_sigla    VARCHAR(255) NOT NULL
)  ENGINE=INNODB;