# CRIANDO BANCO DE DADOS
CREATE DATABASE agendaweb;

# APAGANDO BANCO DE DADOS
DROP DATABASE agendaweb;

# APAGANDO A TABELA DO BANCO DE DADOS
DROP TABLE login;

# SELECIONANDO BANCO DE DADOS
USE agendaweb;

# CRIANDO TABELAS
create table login (
    id int auto_increment,
    login varchar(30) not null,
    senha varchar(30) not null,

    constraint id_login primary key (id)
);


SELECT * FROM login;