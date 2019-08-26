Drop Table if exists venda_produto;
Drop Table if exists venda;
Drop Table if exists compra_produto;
Drop Table if exists compra;
Drop Table if exists cliente;
Drop Table if exists fornecedor;
Drop Table if exists produto;
Drop Table if exists categoria;
Drop Table if exists usuario;

CREATE SEQUENCE categoria_id_seq;
CREATE TABLE categoria(
    id INTEGER DEFAULT nextval('categoria_id_seq'),
    descricao VARCHAR(50) NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY (id)
);

ALTER SEQUENCE categoria_id_seq OWNED BY categoria.id;


CREATE SEQUENCE produto_id_seq;
CREATE TABLE produto(
    id INTEGER DEFAULT nextval('produto_id_seq'),
    nome varchar(100) not null,
    descricao varchar(1000) not null,
    valor numeric(10,2) not null,
    categoria_id INTEGER not null,
    CONSTRAINT produto_pk PRIMARY KEY (id)
);

ALTER TABLE produto ADD CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id);

ALTER SEQUENCE produto_id_seq OWNED BY produto.id;


CREATE SEQUENCE fornecedor_id_seq;
CREATE TABLE fornecedor(
    id INTEGER DEFAULT nextval('fornecedor_id_seq'),
    nome VARCHAR(50) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    telefone VARCHAR(20) NOT NULL,  
    email VARCHAR(50) NOT NULL,
    CONSTRAINT fornecedor_pk PRIMARY KEY (id)
);
ALTER SEQUENCE fornecedor_id_seq OWNED BY fornecedor.id;


CREATE SEQUENCE compra_id_seq;
CREATE TABLE compra(
    id INTEGER DEFAULT nextval('compra_id_seq'),
    data date not null,
    fornecedor_id INTEGER not null,
    CONSTRAINT compra_pk PRIMARY KEY (id)
);
ALTER SEQUENCE compra_id_seq OWNED BY compra.id;
ALTER TABLE compra ADD CONSTRAINT fk_compra_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id);

CREATE SEQUENCE compraproduto_id_seq;
CREATE TABLE compra_produto(
    id INTEGER DEFAULT nextval('compraproduto_id_seq'),
    compra_id INTEGER not null,
    produto_id INTEGER not null, 
    quantidade INTEGER not null,
    valor numeric(10,2) not null,
    CONSTRAINT compraproduto_pk PRIMARY KEY (id)
);
ALTER SEQUENCE compraproduto_id_seq OWNED BY compra_produto.id;
ALTER TABLE compra_produto ADD CONSTRAINT fk_compraproduto_compra FOREIGN KEY (compra_id) REFERENCES compra(id);
ALTER TABLE compra_produto ADD CONSTRAINT fk_compraproduto_produto FOREIGN KEY (produto_id) REFERENCES produto(id);


CREATE SEQUENCE cliente_id_seq;
CREATE TABLE cliente(
    id INTEGER DEFAULT nextval('cliente_id_seq'),
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20) NOT NULL,  
    email VARCHAR(50) NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (id)
);
ALTER SEQUENCE cliente_id_seq OWNED BY cliente.id;


CREATE SEQUENCE venda_id_seq;
CREATE TABLE venda(
    id INTEGER DEFAULT nextval('venda_id_seq'),
    data date not null,
    cliente_id INTEGER not null,
    CONSTRAINT venda_pk PRIMARY KEY (id)
);
ALTER SEQUENCE venda_id_seq OWNED BY venda.id;
ALTER TABLE venda ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id);

CREATE SEQUENCE vendaproduto_id_seq;
CREATE TABLE venda_produto(
    id INTEGER DEFAULT nextval('vendaproduto_id_seq'),
    venda_id INTEGER not null,
    produto_id INTEGER not null, 
    quantidade INTEGER not null,
    valor numeric(10,2) not null,
    CONSTRAINT vendaproduto_pk PRIMARY KEY (id)
);
ALTER SEQUENCE vendaproduto_id_seq OWNED BY venda_produto.id;
ALTER TABLE venda_produto ADD CONSTRAINT fk_vendaproduto_venda FOREIGN KEY (venda_id) REFERENCES venda(id);
ALTER TABLE venda_produto ADD CONSTRAINT fk_vendaproduto_produto FOREIGN KEY (produto_id) REFERENCES produto(id);

CREATE SEQUENCE usuario_id_seq;
CREATE TABLE usuario(
  id INTEGER DEFAULT nextval('usuario_id_seq'),
  ativo character(1) DEFAULT 'T'::bpchar,
  cpf character varying(11) NOT NULL,
  data_nascimento date,
  email character varying(100) NOT NULL,
  nome character varying(100) NOT NULL,
  senha character varying(512) NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


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