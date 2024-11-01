# CRIANDO BANCO DE DADOS
CREATE DATABASE agendaweb;

# APAGANDO BANCO DE DADOS
DROP DATABASE agendaweb;

# APAGANDO A TABELA DO BANCO DE DADOS
DROP TABLE logins;

# SELECIONANDO BANCO DE DADOS
USE agendaweb;

# EXCLUINDO TABELAS
DROP TABLE usuario_contato;
DROP TABLE contato;
DROP TABLE usuario;
DROP TABLE login;

# CONSULTANDO TABELA
SELECT * FROM login l
JOIN usuario u ON l.id_login = u.id_login
JOIN usuario_contato uc ON uc.usuario_id_usuario = u.id_usuario
JOIN contato c ON c.id_contato = uc.contatos_id_contato;

SELECT * FROM contato;

delete from contato where id_contato=1;

# INSERINDO REGISTROS
INSERT INTO login (username, password) VALUES ('daniel', '123');

INSERT INTO usuario (email, nome, id_login) VALUES ('daniel.coelho.santos@teste.com', 'Daniel Luiz Coelho dos Santos', 1);

INSERT INTO login (username, password) VALUES ('admin', '123');

INSERT INTO usuario (email, nome, id_login) VALUES ('admin@teste.com', 'Admin Luiz Coelho dos Santos', 2);

INSERT INTO contato (celular, email, nome, telefone, usuario_id_usuario) VALUES ('(65)99514-3125', 'fabiano.magno@teste.com', 'Fabiano Magno da Silva', '(65)3165-4520', 1);
INSERT INTO contato (celular, email, nome, telefone, usuario_id_usuario) VALUES ('(65)91234-3125', 'carlos.santos@teste.com', 'Carlos Araújo dos Santos', '(65)3165-4520', 1);
INSERT INTO contato (celular, email, nome, telefone, usuario_id_usuario) VALUES ('(65)93244-3125', 'marcia.fernada@teste.com', 'Márcia da Silva Costa Fernanda', '(65)3165-4520', 1);
INSERT INTO contato (celular, email, nome, telefone, usuario_id_usuario) VALUES ('(65)92345-3125', 'julia.magno@teste.com', 'Júlia da Conceição Magno', '(65)3165-4520', 1);

INSERT INTO usuario_contato (usuario_id_usuario, contatos_id_contato) VALUES (1, 1);
INSERT INTO usuario_contato (usuario_id_usuario, contatos_id_contato) VALUES (1, 2);
INSERT INTO usuario_contato (usuario_id_usuario, contatos_id_contato) VALUES (1, 3);
INSERT INTO usuario_contato (usuario_id_usuario, contatos_id_contato) VALUES (1, 4);
