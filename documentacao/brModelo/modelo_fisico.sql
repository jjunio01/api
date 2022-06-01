drop table if exists pedido;
drop table if exists endereco;
drop table if exists item_produto;
drop table if exists estoque;
drop table if exists produto;
drop table if exists fornecedor;
drop table if exists cliente;
drop table if exists carrinho;

create table carrinho (
       id_carrinho integer not null auto_increment,
        valor_total double precision,
        primary key (id_carrinho)
) engine=InnoDB;
 
    
create table cliente (
   id_cliente integer not null auto_increment,
	codigo varchar(19) not null,
	email varchar(200) not null,
	nome varchar(255) not null,
	senha varchar(255) not null,
	telefone varchar(11),
	primary key (id_cliente)
) engine=InnoDB;

    
create table endereco (
   id_endereco integer not null auto_increment,
	bairro varchar(40) not null,
	cidade varchar(40) not null,
	numero varchar(12) not null,
	referencia varchar(200),
	rua varchar(100) not null,
	cliente_id_cliente integer,
	fornecedor_id_fornecedor integer,
	primary key (id_endereco)
) engine=InnoDB;

create table estoque (
   id_estoque integer not null auto_increment,
	quantidade integer not null,
	fornecedor_id_fornecedor integer,
	id_produto integer,
	primary key (id_estoque)
) engine=InnoDB; 
    
create table fornecedor (
   id_fornecedor integer not null auto_increment,
	cnpj varchar(19) not null,
	nome_fantasia varchar(255) not null,
	razao_social varchar(255),
	telefone varchar(11),
    senha varchar(255) not null,
	primary key (id_fornecedor)
) engine=InnoDB;
    
create table item_produto (
   quantidade integer not null,
	valor double precision not null,
	carrinho_id_carrinho integer not null,
	produto_id_produto integer not null,
	primary key (produto_id_produto, carrinho_id_carrinho)
) engine=InnoDB;
    
create table pedido (
   id_pedido integer not null auto_increment,
	data date,
	status varchar(15) not null,
	valor_total double precision,
	id_carrinho integer,
	id_cliente integer,
	id_endereco integer,
	primary key (id_pedido)
) engine=InnoDB;

    
create table produto (
   id_produto integer not null auto_increment,
	descricao varchar(40),
	marca varchar(20),
	nome varchar(40),
	valor double precision not null,
	primary key (id_produto)
) engine=InnoDB;

    
alter table endereco add constraint FKClienteEndereco foreign key (cliente_id_cliente) references cliente (id_cliente);
alter table endereco add constraint FKFornecedorEndereco foreign key (fornecedor_id_fornecedor) references fornecedor (id_fornecedor);
alter table estoque add constraint FKFornecedorEstoque foreign key (fornecedor_id_fornecedor) references fornecedor (id_fornecedor);
alter table estoque add constraint FKProdutoEstoque foreign key (id_produto) references produto (id_produto);
alter table item_produto add constraint FKCarrinhoItemProduto foreign key (carrinho_id_carrinho) references carrinho (id_carrinho);
alter table item_produto add constraint FKProdutoItemProduto foreign key (produto_id_produto) references produto (id_produto);
alter table pedido add constraint FKCarrinhoPedido foreign key (id_carrinho) references carrinho (id_carrinho);
alter table pedido add constraint FKClientePedido foreign key (id_cliente) references cliente (id_cliente);
alter table pedido add constraint FKEnderecoPedido foreign key (id_endereco) references endereco (id_endereco);