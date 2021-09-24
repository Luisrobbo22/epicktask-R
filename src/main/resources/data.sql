CREATE TABLE task (
	id bigint primary key auto_increment,
	title varchar(200),
	description varchar(200),
	points int
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200)
	
);

INSERT INTO user (name, email, password) VALUES
('Joao Carlos', 'joao@gmail.com', '123'),
('Carla Lopes', 'carla@gmail.com', '123'),
('Fabio Cabrini', 'fabio@fiap.com.br', '123');

INSERT INTO task (title, description, points) VALUES(
	'Criar banco de dados',
	'Criação do banco de dados oracle',
	150
);

INSERT INTO task (title, description, points) VALUES(
	'Análise de dados',
	'Modelagem do banco de dados',
	50
);

INSERT INTO task (title, description, points) VALUES(
	'Prototipação',
	'Desenvolver protótipo de alta fidelidade',
	80
);