CREATE TABLE task (
	id bigint primary key auto_increment,
	title varchar(200),
	description varchar(200),
	points int,
	status int DEFAULT 0
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200),
	githubuser varchar(200)
	
);

INSERT INTO user (name, email, password, githubuser) VALUES
('Joao Carlos', 'joao@gmail.com', '$2a$12$g9tJetbQ7QljdEcAKQIPxeIVae8Ofh.b2afO4coTshDUHV6NiL2oO', 'joaocarloslima'),
('Carla Lopes', 'carla@gmail.com', '123', 'carla'),
('Fabio Cabrini', 'fabio@fiap.com.br', '123', 'marcis');

INSERT INTO task (title, description, points, status) VALUES(
	'Criar banco de dados',
	'Criação do banco de dados oracle',
	150,
	10
);

INSERT INTO task (title, description, points, status) VALUES(
	'Análise de dados',
	'Modelagem do banco de dados',
	50,
	60
);

INSERT INTO task (title, description, points, status) VALUES(
	'Prototipação',
	'Desenvolver protótipo de alta fidelidade',
	80,
	95
);