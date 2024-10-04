DROP DATABASE IF EXISTS furini;

CREATE DATABASE IF NOT EXISTS furini;
use furini;

CREATE TABLE IF NOT EXISTS produto (
    id INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(45) NOT NULL,
    valor DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(120),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS notafiscal (
    id INT NOT NULL AUTO_INCREMENT,
    dataEmissao DATE NOT NULL,
    cliente_id INT NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE TABLE IF NOT EXISTS itemnota (
    id INT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    valorItem DOUBLE,
    produto_id INT NOT NULL,
    notaFiscal_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (produto_id) REFERENCES produto (id),
    FOREIGN KEY (notaFiscal_id) REFERENCES notafiscal (id)    
);



