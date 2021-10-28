drop database if  exists hotel;
create database hotel;

#TABELA FUNCIONARIOS
use hotel;
create table funcionarios (
	idFuncionario int primary key auto_increment not null,
	nome varchar (100) not null,
	sexo enum ('M', 'F','Outros') not null,
	nascimento date not null,
	endereco varchar (200) not null,
	dataContratacao date not null,
	cargo varchar (50) not null,
	cargaHoraria double not null,
	cpf varchar (14) unique not null,
	rg varchar (12) not null,
	telefone varchar(16) not null,
	email varchar(50) not null,
	dataCadastro datetime not null,
	usuario varchar(100),
	senha varchar(100)
);

#TABELA HOSPEDES
use hotel;
create table hospedes (
	idHospede int primary key auto_increment not null,
	nome varchar (100) not null,
	sexo enum ('M', 'F','Outros') not null,
	nascimento date not null,
	cidade varchar(50) not null,
	cpf varchar(14) unique not null,
	rg varchar (16) not null,
	telefone varchar(16) not null
);

#TABELA QUARTOS
use hotel;
create table quartos (
	numero int primary key not null,
	andar int not null,
	tipo varchar (25) not null,
	descricao varchar (500) not null,
	preco double not null,
	disponivel enum ('S', 'N') not null
);

#TABELA DE CONTROLE DE DINHEIRO
/*use hotel;
create table fluxodecaixa (
	idFluxo int primary key auto_increment,
	nome varchar(100),
	valor double,
	data date,
	ESD enum ('Pagar', 'Receber')
);*/

#TABELA DE ESTOQUE
/*use hotel;
create table estoque (
	idEstoque double primary key auto_increment,
	nome varchar(100),
	descricao varchar(200),
	quantidade int,
	valor double,
	validade date
);*/

#TABELA RESERVAS
use hotel;
create table reservas(
	idReserva int primary key auto_increment not null,
	id_hospede int not null,
	num_quarto int not null,
	dataEntrada datetime not null,
	dataSaida datetime not null,
	estado varchar(10) not null,
	consumo double not null,

	foreign key (id_hospede) references hospedes (idHospede),
	foreign key (num_quarto) references quartos (numero)/*,
	foreign key (consumo) references estoque (idEstoque)*/
);

#TABELA PAGAMENTOS - GERAR RELATÓRIO
use hotel;
create table pagamento(
	idPagamento int primary key auto_increment not null,
	valor double not null,
	Fpagamento varchar(50) not null,
	data datetime not null,
    descricao varchar(200) not null/*,
	id_Fluxo int not null,
	pFuncionario double not null,

	foreign key (id_Fluxo) references fluxodecaixa (idFluxo),
	foreign key (pFuncionario) references funcionarios (salario)*/
);

#LEMBRETES
use hotel;
create table lembrete (
	idLembrete int primary key auto_increment not null,
	nomeUsuario varchar(100) not null,
	assunto varchar(100) not null,
	data datetime not null,
	descricao varchar(250) not null
);

#INSERTS
use hotel;
insert into funcionarios(usuario,senha,nome,sexo,nascimento,endereco,dataContratacao,cargo,cargaHoraria,cpf,rg,telefone,email,dataCadastro) 
values("admin","admin",'Administrador','Outros','2100-12-31','Vale do Silício','2019-08-08','Gerente de TI','12','111.111.111-1','11.111.111-1','1999504-9808','Administrador@gmail.com','2019-08-27 23:57:11');

use hotel;
insert into quartos(numero,andar,tipo,descricao,preco,disponivel) values
(1,1,'Tipo 11','Descrição do quarto 1 do andar 1!',5001.0,'N'),
(2,1,'Tipo 21','Descrição do quarto 2 do andar 1!',5002.0,'S'),
(3,1,'Tipo 31','Descrição do quarto 3 do andar 1!',5003.0,'S'),
(4,1,'Tipo 41','Descrição do quarto 4 do andar 1!',5004.0,'S'),
(5,1,'Tipo 51','Descrição do quarto 5 do andar 1!',5005.0,'S'),
(6,1,'Tipo 61','Descrição do quarto 6 do andar 1!',5006.0,'S'),
(7,1,'Tipo 71','Descrição do quarto 7 do andar 1!',5007.0,'S'),
(8,1,'Tipo 81','Descrição do quarto 8 do andar 1!',5008.0,'S'),
(9,1,'Tipo 91','Descrição do quarto 9 do andar 1!',5009.0,'S'),
(10,1,'Tipo 101','Descrição do quarto 10 do andar 1!',5010.0,'S'),
(11,2,'Tipo 112','Descrição do quarto 11 do andar 2!',5011.0,'S'),
(12,2,'Tipo 122','Descrição do quarto 12 do andar 2!',5012.0,'S'),
(13,2,'Tipo 132','Descrição do quarto 13 do andar 2!',5013.0,'S'),
(14,2,'Tipo 142','Descrição do quarto 14 do andar 2!',5014.0,'S'),
(15,2,'Tipo 152','Descrição do quarto 15 do andar 2!',5015.0,'S'),
(16,2,'Tipo 162','Descrição do quarto 16 do andar 2!',5016.0,'S'),
(17,2,'Tipo 172','Descrição do quarto 17 do andar 2!',5017.0,'S'),
(18,2,'Tipo 182','Descrição do quarto 18 do andar 2!',5018.0,'S'),
(19,2,'Tipo 192','Descrição do quarto 19 do andar 2!',5019.0,'S'),
(20,2,'Tipo 202','Descrição do quarto 20 do andar 2!',5020.0,'S'),
(21,3,'Tipo 213','Descrição do quarto 21 do andar 3!',5021.0,'S'),
(22,3,'Tipo 223','Descrição do quarto 22 do andar 3!',5022.0,'S'),
(23,3,'Tipo 233','Descrição do quarto 23 do andar 3!',5023.0,'S'),
(24,3,'Tipo 243','Descrição do quarto 24 do andar 3!',5024.0,'S'),
(25,3,'Tipo 253','Descrição do quarto 25 do andar 3!',5025.0,'S'),
(26,3,'Tipo 263','Descrição do quarto 26 do andar 3!',5026.0,'S'),
(27,3,'Tipo 273','Descrição do quarto 27 do andar 3!',5027.0,'S'),
(28,3,'Tipo 283','Descrição do quarto 28 do andar 3!',5028.0,'S'),
(29,3,'Tipo 293','Descrição do quarto 29 do andar 3!',5029.0,'S'),
(30,3,'Tipo 303','Descrição do quarto 30 do andar 3!',5030.0,'S'),
(31,4,'Tipo 314','Descrição do quarto 31 do andar 4!',5031.0,'S'),
(32,4,'Tipo 324','Descrição do quarto 32 do andar 4!',5032.0,'S'),
(33,4,'Tipo 334','Descrição do quarto 33 do andar 4!',5033.0,'S'),
(34,4,'Tipo 344','Descrição do quarto 34 do andar 4!',5034.0,'S'),
(35,4,'Tipo 354','Descrição do quarto 35 do andar 4!',5035.0,'S'),
(36,4,'Tipo 364','Descrição do quarto 36 do andar 4!',5036.0,'S'),
(37,4,'Tipo 374','Descrição do quarto 37 do andar 4!',5037.0,'S'),
(38,4,'Tipo 384','Descrição do quarto 38 do andar 4!',5038.0,'S'),
(39,4,'Tipo 394','Descrição do quarto 39 do andar 4!',5039.0,'S'),
(40,4,'Tipo 404','Descrição do quarto 40 do andar 4!',5040.0,'S'),
(41,5,'Tipo 415','Descrição do quarto 41 do andar 5!',5041.0,'S'),
(42,5,'Tipo 425','Descrição do quarto 42 do andar 5!',5042.0,'S'),
(43,5,'Tipo 435','Descrição do quarto 43 do andar 5!',5043.0,'S'),
(44,5,'Tipo 445','Descrição do quarto 44 do andar 5!',5044.0,'S'),
(45,5,'Tipo 455','Descrição do quarto 45 do andar 5!',5045.0,'S'),
(46,5,'Tipo 465','Descrição do quarto 46 do andar 5!',5046.0,'S'),
(47,5,'Tipo 475','Descrição do quarto 47 do andar 5!',5047.0,'S'),
(48,5,'Tipo 485','Descrição do quarto 48 do andar 5!',5048.0,'S'),
(49,5,'Tipo 495','Descrição do quarto 49 do andar 5!',5049.0,'S'),
(50,5,'Tipo 505','Descrição do quarto 50 do andar 5!',5050.0,'S'),
(51,6,'Tipo 516','Descrição do quarto 51 do andar 6!',5051.0,'S'),
(52,6,'Tipo 526','Descrição do quarto 52 do andar 6!',5052.0,'S'),
(53,6,'Tipo 536','Descrição do quarto 53 do andar 6!',5053.0,'S'),
(54,6,'Tipo 546','Descrição do quarto 54 do andar 6!',5054.0,'S'),
(55,6,'Tipo 556','Descrição do quarto 55 do andar 6!',5055.0,'S'),
(56,6,'Tipo 566','Descrição do quarto 56 do andar 6!',5056.0,'S'),
(57,6,'Tipo 576','Descrição do quarto 57 do andar 6!',5057.0,'S'),
(58,6,'Tipo 586','Descrição do quarto 58 do andar 6!',5058.0,'S'),
(59,6,'Tipo 596','Descrição do quarto 59 do andar 6!',5059.0,'S'),
(60,6,'Tipo 606','Descrição do quarto 60 do andar 6!',5060.0,'S'),
(61,7,'Tipo 617','Descrição do quarto 61 do andar 7!',5061.0,'S'),
(62,7,'Tipo 627','Descrição do quarto 62 do andar 7!',5062.0,'S'),
(63,7,'Tipo 637','Descrição do quarto 63 do andar 7!',5063.0,'S'),
(64,7,'Tipo 647','Descrição do quarto 64 do andar 7!',5064.0,'S'),
(65,7,'Tipo 657','Descrição do quarto 65 do andar 7!',5065.0,'S'),
(66,7,'Tipo 667','Descrição do quarto 66 do andar 7!',5066.0,'S'),
(67,7,'Tipo 677','Descrição do quarto 67 do andar 7!',5067.0,'S'),
(68,7,'Tipo 687','Descrição do quarto 68 do andar 7!',5068.0,'S'),
(69,7,'Tipo 697','Descrição do quarto 69 do andar 7!',5069.0,'S'),
(70,7,'Tipo 707','Descrição do quarto 70 do andar 7!',5070.0,'S'),
(71,8,'Tipo 718','Descrição do quarto 71 do andar 8!',5071.0,'S'),
(72,8,'Tipo 728','Descrição do quarto 72 do andar 8!',5072.0,'S'),
(73,8,'Tipo 738','Descrição do quarto 73 do andar 8!',5073.0,'S'),
(74,8,'Tipo 748','Descrição do quarto 74 do andar 8!',5074.0,'S'),
(75,8,'Tipo 758','Descrição do quarto 75 do andar 8!',5075.0,'S'),
(76,8,'Tipo 768','Descrição do quarto 76 do andar 8!',5076.0,'S'),
(77,8,'Tipo 778','Descrição do quarto 77 do andar 8!',5077.0,'S'),
(78,8,'Tipo 788','Descrição do quarto 78 do andar 8!',5078.0,'S'),
(79,8,'Tipo 798','Descrição do quarto 79 do andar 8!',5079.0,'S'),
(80,8,'Tipo 808','Descrição do quarto 80 do andar 8!',5080.0,'S'),
(81,9,'Tipo 819','Descrição do quarto 81 do andar 9!',5081.0,'S'),
(82,9,'Tipo 829','Descrição do quarto 82 do andar 9!',5082.0,'S'),
(83,9,'Tipo 839','Descrição do quarto 83 do andar 9!',5083.0,'S'),
(84,9,'Tipo 849','Descrição do quarto 84 do andar 9!',5084.0,'S'),
(85,9,'Tipo 859','Descrição do quarto 85 do andar 9!',5085.0,'S'),
(86,9,'Tipo 869','Descrição do quarto 86 do andar 9!',5086.0,'S'),
(87,9,'Tipo 879','Descrição do quarto 87 do andar 9!',5087.0,'S'),
(88,9,'Tipo 889','Descrição do quarto 88 do andar 9!',5088.0,'S'),
(89,9,'Tipo 899','Descrição do quarto 89 do andar 9!',5089.0,'S'),
(90,9,'Tipo 909','Descrição do quarto 90 do andar 9!',5090.0,'S'),
(91,10,'Tipo 9110','Descrição do quarto 91 do andar 10!',5091.0,'S'),
(92,10,'Tipo 9210','Descrição do quarto 92 do andar 10!',5092.0,'S'),
(93,10,'Tipo 9310','Descrição do quarto 93 do andar 10!',5093.0,'S'),
(94,10,'Tipo 9410','Descrição do quarto 94 do andar 10!',5094.0,'S'),
(95,10,'Tipo 9510','Descrição do quarto 95 do andar 10!',5095.0,'S'),
(96,10,'Tipo 9610','Descrição do quarto 96 do andar 10!',5096.0,'S'),
(97,10,'Tipo 9710','Descrição do quarto 97 do andar 10!',5097.0,'S'),
(98,10,'Tipo 9810','Descrição do quarto 98 do andar 10!',5098.0,'S'),
(99,10,'Tipo 9910','Descrição do quarto 99 do andar 10!',5099.0,'S'),
(100,10,'Tipo 10010','Descrição do quarto 100 do andar 10!',5100.0,'S');

use hotel;
insert into hospedes(nome,sexo,nascimento,cidade,cpf,rg,telefone)
values('Kássio','M','2002-08-01','São João da Boa Vista','111.111.111-1','11.111.111-1','199123-4567'),
('Mariana','F','1998-05-17','São Paulo','222.222.222-2','22.222.222-2','199891-0111');

use hotel;
insert into reservas(id_hospede,num_quarto,dataEntrada,dataSaida,estado,consumo)
values(1,88,'2019-08-29 13:06:22','2019-09-14 16:58:31','Concluído',749.83),
(2,1,'2019-08-27 15:44:59','2019-09-10 12:04:33','Em estadia',395.79);

use hotel;
insert into pagamento(valor,Fpagamento,data,descricao)
values(534.31,'Crédito','2019-08-27 17:32:14','descricao do pagamento da garagem'),
(349.55,'Boleto','2019-08-27 17:44:23','descricao do pagamento do 2º andar');

insert into lembrete(nomeUsuario,assunto,data,descricao)
values('Pedro','Pagar conta de eletricidade 2ªfeira','2019-08-27 14:00:00','Conta de eletricidade boleto R$600.00 na lotérica na avenida principal'),
('Luis','Pagar encanador','2019-08-27 16:27:15','Encanador consertou vazamento no quarto 1 térreo essa semana R$250.00');

#SELECTS
use hotel;
#seleciona os quarto disponivel com preço igual ou menor que 5000
select * from quartos where preco <=5000 and disponivel = 'S';

use hotel;
#retorna nome do usuario, o numero do quarto que ele ficou, data de entrada e data de saída da reserva, o estado do quarto e o estado da reserva listando os nomes em ordem crescente(alfabética)
select hospedes.nome,numero,andar,disponivel,consumo,dataEntrada,dataSaida,estado from reservas,hospedes,quartos where idHospede = id_hospede and numero = num_quarto order by nome asc;

use hotel;
#verifica se o usuário é igual 'admin' e senha igual a 'admin'
select usuario,senha from funcionarios where usuario='admin' and senha='admin';

use hotel;
#seleciona todos os campos da tabela de lembretes que tem a data depois de 2019-08-27 e da hora 15:00:00
select * from lembrete where data >= '2019-08-27 15:00:00';
