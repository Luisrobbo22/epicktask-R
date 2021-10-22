CREATE TABLE task (
	id bigint primary key auto_increment,
	title varchar(200),
	description varchar(200),
	points int,
	status int DEFAULT 0,
	user_id int
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200),
	githubuser varchar(200)
	
);

CREATE TABLE ROLE (id int primary key auto_increment, name varchar(200));

CREATE TABLE USER_ROLES (user_id int, roles_id int);

INSERT INTO ROLE (name) VALUES('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO  USER_ROLES VALUES (1,1);

INSERT INTO user (name, email, password, githubuser) VALUES
('Luis Robbo', 'luis@gmail.com', '$2a$12$g9tJetbQ7QljdEcAKQIPxeIVae8Ofh.b2afO4coTshDUHV6NiL2oO', 'Luisrobbo22'),
('Carla Lopes', 'carla@gmail.com', '$2a$12$g9tJetbQ7QljdEcAKQIPxeIVae8Ofh.b2afO4coTshDUHV6NiL2oO', 'carla'),
('Fabio Cabrini', 'fabio@fiap.com.br', '$2a$12$g9tJetbQ7QljdEcAKQIPxeIVae8Ofh.b2afO4coTshDUHV6NiL2oO', 'marcis'),
('Luis Robbo', 'luis@gmail.com', '$2a$12$g9tJetbQ7QljdEcAKQIPxeIVae8Ofh.b2afO4coTshDUHV6NiL2oO', 'Luisrobbo22'),;

INSERT INTO task (title, description, points, status, user_id) VALUES(
	'Criar banco de dados',
	'Criação do banco de dados oracle',
	150,
	10,
	1
);

INSERT INTO task (title, description, points, status) VALUES(
	'Análise de dados',
	'Modelagem do banco de dados',
	50,
	60
);

INSERT INTO task (title, description, points, status, user_id) VALUES(
	'Prototipação',
	'Desenvolver protótipo de alta fidelidade',
	80,
	95,
	2
);