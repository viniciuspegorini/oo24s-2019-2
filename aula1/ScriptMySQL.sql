Drop Table if exists venda_produto;
Drop Table if exists venda;
Drop Table if exists compra_produto;
Drop Table if exists compra;
Drop Table if exists cliente;
Drop Table if exists fornecedor;
Drop Table if exists produto;
Drop Table if exists categoria;
Drop Table if exists usuario;

CREATE TABLE categoria(
	id int not null auto_increment,
	descricao varchar(50) not null,
	PRIMARY KEY (id)
);

CREATE TABLE produto(
	id int not null auto_increment,
	nome varchar(100) not null,
	descricao varchar(1000) not null,
	valor double precision(10,2) not null,
	categoria_id int not null,
	PRIMARY KEY (id)
);
ALTER TABLE produto ADD CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id);

CREATE TABLE fornecedor(
	id int auto_increment,
	nome varchar(50) not null,
	cnpj varchar(14),
	telefone varchar(20),
	email varchar(50),
	PRIMARY KEY (id)
);

CREATE TABLE compra(
	id int auto_increment,
	data datetime not null,
	fornecedor_id int not null,
	PRIMARY KEY (id)
);
ALTER TABLE compra ADD CONSTRAINT fk_compra_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id);

CREATE TABLE compra_produto(
	id int not null auto_increment,
	compra_id int not null,
	produto_id int not null,	
	quantidade int not null,
	valor double precision(10,2) not null,
	PRIMARY KEY (id)
);
ALTER TABLE compra_produto ADD CONSTRAINT fk_compraproduto_compra FOREIGN KEY (compra_id) REFERENCES compra(id);
ALTER TABLE compra_produto ADD CONSTRAINT fk_compraproduto_produto FOREIGN KEY (produto_id) REFERENCES produto(id);


CREATE TABLE cliente(
	id int auto_increment,
	nome varchar(50) not null,
	cpf varchar(11) not null,
	telefone varchar(15),
	email varchar(50) not null,
	PRIMARY KEY (id)
);

CREATE TABLE venda(
	id int auto_increment,
	data datetime not null,
	cliente_id int not null,
	PRIMARY KEY (id)
);
ALTER TABLE venda ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id);

CREATE TABLE venda_produto(
	id int not null auto_increment,
	venda_id int not null,
	produto_id int not null,	
	quantidade int not null,
	valor decimal(10,2) not null,
	PRIMARY KEY (id)
);
ALTER TABLE venda_produto ADD CONSTRAINT fk_vendaproduto_venda FOREIGN KEY (venda_id) REFERENCES venda(id);
ALTER TABLE venda_produto ADD CONSTRAINT fk_vendaproduto_produto FOREIGN KEY (produto_id) REFERENCES produto(id);

CREATE TABLE usuario(
  id int not null auto_increment,
  ativo character(1) DEFAULT 'T',
  cpf varchar(11) NOT NULL,
  data_nascimento date,
  email varchar(100) NOT NULL,
  nome varchar(100) NOT NULL,
  senha varchar(512) NOT NULL,
  PRIMARY KEY (id)
);

insert into categoria (descricao) values('Informática');
insert into categoria (descricao) values('Telefonia');
insert into categoria (descricao) values('Eletrônicos');

insert into produto (nome,descricao,valor,categoria_id) values('Mouse USB', 'Mouse usb preto, 6cm,3cm,5cm',25.12,1);
insert into produto (nome,descricao,valor,categoria_id) values('Teclado ABNT2', 'Teclado ABNT2 114 teclas',52.75,1);
insert into produto (nome,descricao,valor,categoria_id) values('TV 42pol.', 'Televisor de 42 polegadas 16:9 4K',3799.49,3);

insert into cliente(nome,cpf,telefone,email) values('João das Neves', '01232165487','(46)3222-2212','joao@wtfel.com');
insert into cliente(nome,cpf,telefone,email) values('Sebastião Lanister', '35742512464','(46)2525-1112','tiao@rocks.com');

insert into venda(data,cliente_id) values('2017-05-01',1);
insert into venda_produto(venda_id,produto_id,quantidade,valor) values(1, 1,2,25.12);
insert into venda_produto(venda_id,produto_id,quantidade,valor) values(1, 2,1,52.75);

insert into venda(data,cliente_id) values('2017-05-01',2);
insert into venda_produto(venda_id,produto_id,quantidade,valor) values(2, 2,2,52.75);
insert into venda_produto(venda_id,produto_id,quantidade,valor) values(2, 3,1,52.75);

insert into usuario(ativo,cpf,data_nascimento,email,nome,senha) values('T', '00100200345','1990-05-22','teste@gmail.com','Teste','teste');
insert into usuario(ativo,cpf,data_nascimento,email,nome,senha) values('T', '12345678998','1991-05-22','admin@gmail.com','Admin','admin');