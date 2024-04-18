create table medicos(

  id bigint not null auto_increment,
  nome varchar(100) not null,
  email varchar(100) not null,
  crm varchar(6) not null unique,
  especialidade varchar(100) not null,
  logradouro varchar (100) not null,
  bairro varchar(50) not null,
  cep varchar(9) not null,
  complemento varchar(50),
  numero varchar(20),
  uf char(2) not null,
  cidade varchar(100) not null,

  primary key(id)

);