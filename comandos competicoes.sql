

ng g m app-routing --routing --flat true

ng g c dashboard --skip-tests true
ng g c dashboard --skip-tests true



ng g m sidenav --routing --flat true

ng g m home
ng g c home
ng g m home --routing --flat true

ng g c campeonatos-cadastro --skip-tests true
ng g c campeonatos-pesquisa --skip-tests true
ng g m campeonatos --routing --flat true
ng g s campeonatos --skip-tests true
**

ng g c sidenav --skip-tests true 
ng g m coupens --routing --flat true
ng g m products --routing --flat true
ng g c coupens-list --module coupens

ng g m app --flat true 
ng g m login --flat true

**


CRIANDO DBLINK DE KRIPTON PARA COMPETICOES
==========================================

no data base competicoes
------------------------
CREATE EXTENSION dblink;
SELECT * FROM pg_extension WHERE extname = 'dblink';

CREATE OR REPLACE FUNCTION obter_tabela_user_kripton()
RETURNS TABLE (
    id INT,
	empresa_id INT,
    name TEXT,
	email TEXT,
	password TEXT 
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT id, empresa_id, name, email, password
    FROM dblink('dbname=kripton', 'SELECT id, empresa_id, name, email, password FROM public.kripton') 
    AS USERS(id INT, empresa_id INT, name TEXT, email TEXT, password TEXT);
END;
$$;

SELECT pessoas.id,
    pessoas.name,
    pessoas.email,
    pessoas.password
   FROM dbo_kripton_link.users;
   
SELECT dblink_connect('dbo_kriton_link', 'host=localhost dbname=kripton user=postgres password=admin');
   

CREATE VIEW vw_users_kripton AS
SELECT * FROM dblink('dblink_connect', 'SELECT id, nome, email FROM users') AS users_kriton (id int, nome varchar(255), email varchar(255));


AQUI DEU CERTO DE CRIAR A VIEW
==============================
CREATE EXTENSION dblink;

SELECT dblink_connect('dbo_kriton_link', 'host=localhost dbname=kripton user=postgres password=admin');

DROP VIEW vw_users_kripton;
CREATE VIEW vw_users_kripton AS
SELECT * FROM dblink('dbo_kriton_link', 'SELECT id, name, email, password, empresa_id FROM users') 
AS users_kriton (id int, name varchar(255), email varchar(255), password varchar(255), empresa_id int);

select * from vw_users_kripton;


-----------------------------------------------------------------------------------------------------

CREATE EXTENSION postgres_fdw;

CREATE SERVER  kripton 
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (host 'localhost', 
             port '5432', 
             dbname 'kripton');			 

DROP USER MAPPING FOR postgres SERVER kripton;

CREATE USER MAPPING FOR postgres
    SERVER kripton
    OPTIONS (user 'postgres', 
             password 'admin');		 
			 
CREATE FOREIGN TABLE users (
    id INT,
	empresa_id INT,
    name TEXT,
	email TEXT,
	password TEXT 
)
SERVER kripton
OPTIONS (schema_name 'public', table_name 'users');			

select * from users;

INSERT INTO USERS (ID,EMPRESA_ID,NAME,EMAIL,PASSWORD)
VALUES(2,1,'Carlos Augusto','teste@gmail.com','$2a$10$YIgTZFDi6K1i.HiYUjFnx.p0iZGqwdUlu4d2Q85vnoJ26oRS/zKXW');

-------------------------------------------------------------------------------------------------------------

Procediemento postgres_fdw
--------------------------

CREATE EXTENSION postgres_fdw;

CREATE SERVER kripton_remote_db
FOREIGN DATA WRAPPER postgres_fdw
OPTIONS (host 'localhost', dbname 'kripton', port '5432');

CREATE USER MAPPING FOR postgres
SERVER kripton_remote_db
OPTIONS (user 'postgres', password 'admin');

IMPORT FOREIGN SCHEMA public.kripton
FROM SERVER kripton_remote_db
INTO kripton;

CREATE VIEW vw_users AS SELECT * FROM kripton.users;

SELECT * FROM vw_users;

-------------------------------------------------------------------------------------------------------------

procediemento_trigger
---------------------

CREATE OR REPLACE FUNCTION public.trg_ins_users_func()
RETURNS TRIGGER AS $$
BEGIN
  NEW.id := nextval('public.users_id_seq');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_ins_users
BEFORE INSERT ON users
FOR EACH ROW
EXECUTE PROCEDURE trg_ins_users_func();

SELECT * FROM information_schema.triggers;

SELECT *
FROM information_schema.routines
WHERE routine_schema = 'public'
  AND routine_type = 'FUNCTION';
  
CREATE SEQUENCE kripton.users_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
CACHE 1;  

--------------------------------------------------------------------------------------------------------------

procediemento_criate_FUNCTION
-----------------------------

CREATE OR REPLACE FUNCTION get_next_kripton_users_id() RETURNS BIGINT AS $$
DECLARE
  next_id BIGINT;
BEGIN
  SELECT INTO next_id * FROM dblink('kripton', 'SELECT nextval(''users_id_seq'')') AS t(next_id bigint);
  RETURN next_id;
END;
$$ LANGUAGE plpgsql;

SELECT get_next_kripton_users_id();

--------------------------------------------------------------------------------------------------------------


script para excluir arquivos com mais de 30 dias de cricao no linux
===================================================================
find /caminho/do/seu/diretorio -type f -mtime +30 -exec rm {} \;

aqui consulta os arquivos
=========================
find /home/scripts/log$ -type f -mtime +25 -print

find -type f -mtime +20 -print


modalidade - campeonato - etapa - categoria - prova 

estou escrevendo um sistema para competições de natação, gostaria que voce descreve este sistema, montando a api back end. 
gostaria de fazer algumas sugestoes de tabelas para qur voce possa usa-las. são: modalidade - campeonato - etapa - categoria - prova , 
fique a vontade para inserir mais coisas conforme a necessidade.

Modalidade: Armazena informações sobre as modalidades de natação (ex: Nado Livre, Costas, Peito, Borboleta, Medley).
Campos: id, nome, descricao
Campeonato: Representa um campeonato de natação específico.
Campos: id, nome, data_inicio, data_fim, local, modalidade_id (chave estrangeira para a tabela Modalidade)
Etapa: Divide um campeonato em diferentes etapas (ex: Eliminatórias, Semifinais, Finais).
Campos: id, nome, data, campeonato_id (chave estrangeira para a tabela Campeonato)
Categoria: Define as diferentes categorias de nadadores com base em idade, gênero, etc.
Campos: id, nome, descricao, faixa_etaria_min, faixa_etaria_max, genero
Prova: Representa uma prova específica dentro de uma etapa (ex: 50m Livre Masculino, 100m Costas Feminino).
Campos: id, nome, distancia, estilo, categoria_id (chave estrangeira para a tabela Categoria), etapa_id (chave estrangeira para a tabela Etapa)
Atleta: Armazena informações sobre os atletas participantes.
Campos: id, nome, data_nascimento, clube, pais
Resultado: Registra os resultados de cada atleta em cada prova.
Campos: id, atleta_id (chave estrangeira para a tabela Atleta), prova_id (chave estrangeira para a tabela Prova), tempo, colocacao

categoria

********

Adaptando o sistema para competições mistas
Incluir a possibilidade de competições mistas adiciona uma camada de complexidade ao sistema. Aqui estão algumas opções de como implementar isso:
Opção 1: Gênero na Equipe/Dupla
Nova Tabela: Crie uma tabela Equipe ou Dupla para representar equipes ou duplas mistas.
Campos: id, nome, campeonato_id (chave estrangeira), tipo (equipe ou dupla)
Tabela de Associação: Crie uma tabela de associação entre Atleta e Equipe/Dupla para definir os membros de cada equipe/dupla.
Campos: id, atleta_id (chave estrangeira), equipe_id/dupla_id (chave estrangeira)
Resultados: Modifique a tabela Resultado para incluir uma chave estrangeira para Equipe/Dupla, permitindo registrar resultados para equipes/duplas.
Opção 2: Flexibilidade na Categoria
Campo "Misto" na Categoria: Adicione um campo booleano misto à tabela Categoria.
Lógica de Provas: Ao criar uma prova, defina se ela é para uma categoria mista ou não.
Resultados: Mantenha a estrutura atual da tabela Resultado, mas considere o campo misto da categoria ao validar e registrar resultados.
Opção 3: Combinação de Gêneros no Campeonato
Campo "Combinação de Gêneros" no Campeonato: Adicione um campo combinacao_generos na tabela Campeonato para permitir especificar as combinações de gêneros permitidas (ex: masculino, feminino, misto).
Lógica de Provas: Ao criar provas, filtre as categorias disponíveis com base na combinação de gêneros definida no campeonato.
Recomendação:
A melhor opção dependerá da complexidade do seu sistema e da frequência de competições mistas.
Opção 1 é a mais completa e flexível, mas também a mais complexa de implementar.
Opção 2 é mais simples, mas pode ser menos flexível se você precisar de diferentes tipos de competições mistas.
Opção 3 oferece um bom equilíbrio entre flexibilidade e complexidade.
Considerações Adicionais:
Regras de Competição: Defina regras claras para competições mistas, como a composição de equipes/duplas, pontuação e elegibilidade.
Interface do Usuário: Adapte a interface do usuário para permitir a criação e gerenciamento de equipes/duplas mistas ou a seleção de categorias mistas.
Relatórios e Rankings: Considere como os resultados de competições mistas serão exibidos em relatórios e rankings.
Lembre-se de adaptar o sistema de forma que atenda às necessidades específicas das competições que você pretende gerenciar.
User
onde eu salvo esta informacoes para continuarmos e outro dia?
Model
warning
Você tem diversas opções para salvar estas informações e continuar a conversa em outro momento:
1. Simples e acessível:
Copiar e colar: Simplesmente copie todo o texto da nossa conversa e cole em um editor de texto como o Bloco de Notas, um documento do Word ou Google Docs. Salve o arquivo com um nome descritivo, como "Planejamento Sistema Natação.txt".
E-mail: Envie um e-mail para você mesmo com o conteúdo da conversa.
2. Organizado e visual:
Ferramentas de anotações: Use ferramentas como Evernote, OneNote, Google Keep ou Notion para organizar as informações de forma visual, com títulos, tópicos e formatação.
Quadro branco virtual: Crie um quadro no Miro, Mural ou Trello para visualizar as tabelas, relacionamentos e ideias de forma colaborativa.
3. Dentro do código (se aplicável):
Comentários: Se você já está trabalhando no código do sistema, adicione comentários detalhados explicando as decisões de design, tabelas e funcionalidades planejadas. Isso manterá as informações junto ao código-fonte.
Recomendação:
A melhor opção depende do seu estilo de trabalho e da complexidade do projeto. Para projetos mais simples, um editor de texto pode ser suficiente. Para projetos mais complexos, as ferramentas visuais de anotações ou quadros brancos virtuais podem ser mais eficazes.
Lembre-se de escolher um local de fácil acesso para que você possa retomar o planejamento sem dificuldades!

Refatorando para um Sistema Multi-Empresas SaaS
Para transformar o sistema de gerenciamento de competições de natação em uma solução multi-empresas SaaS, precisamos implementar algumas mudanças importantes na estrutura do banco de dados e na lógica da aplicação. O objetivo é permitir que diversas empresas gerenciem suas próprias competições de forma independente, utilizando a mesma plataforma.
Mudanças no Banco de Dados:
Tabela Empresa:
Crie uma nova tabela para armazenar as informações das empresas que utilizam o sistema.
Campos: id, nome, CNPJ, endereço, contato, etc.
Adicionar empresa_id:
Inclua um campo empresa_id (chave estrangeira para a tabela Empresa) em todas as tabelas relevantes, como Campeonato, Etapa, Categoria, Prova, Atleta e Resultado.
Isso permitirá associar os dados de cada competição, atleta e resultado à empresa correspondente.
Mudanças na Lógica da Aplicação:
Autenticação e Autorização:
Implemente um sistema de autenticação robusto para permitir que usuários de diferentes empresas acessem o sistema com suas próprias credenciais.
Crie um sistema de autorização para controlar o acesso de cada usuário às informações e funcionalidades de sua respectiva empresa.
Gerenciamento de Empresas:
Crie uma interface administrativa para gerenciar as empresas cadastradas no sistema, permitindo adicionar, editar, desativar empresas, além de configurar seus planos, limites de uso, etc.
Isolamento de Dados:
Adapte todas as queries e operações no banco de dados para filtrar as informações pela empresa_id.
Garanta que usuários de uma empresa não consigam acessar dados de outras empresas.
API Adaptada:
Inclua a empresa_id como parâmetro nas requisições da API para recuperar e manipular dados da empresa correta.
Implemente mecanismos para garantir que a API só acesse e modifique dados da empresa autenticada.

*****************************************************************************************************************************************

A tabela USERS, é uma view do banco kripton, assim será para todas as tabelas que compõem a parte seguranca do sistema.

Como foi criada esta view, que permite inserir e consultar dados na tabela users do banco kripton.
1. primeiro foi realizado o procediemento: postgres_fdw descrito acima.
2. Segundamente o procedimento: procediemento_trigger - Cria uma trigeer que busca a proximo id da tabela.
3. terceiramente o procediemnto: procediemento_criate_FUNCTION - Cria uma funcao que é executada na api.


select * from users;

drop table users;
create table users (
	id int8 generated by default as identity, 
	empresa_id int8 not null, 
	name varchar(100) not null,
	email varchar(100) not null,
	password varchar(100) not null,
primary key(id));	

CREATE OR REPLACE FUNCTION public.trg_ins_users_func()
RETURNS TRIGGER AS $$
BEGIN
  NEW.id := nextval('public.users_id_seq');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_ins_users
BEFORE INSERT ON users
FOR EACH ROW
EXECUTE PROCEDURE trg_ins_users_func();


INSERT INTO USERS (EMPRESA_ID,NAME,EMAIL,PASSWORD)
VALUES(1,'Fernando Pessoa','pessoa@gmail.com','$2a$10$YIgTZFDi6K1i.HiYUjFnx.p0iZGqwdUlu4d2Q85vnoJ26oRS/zKXW');

SELECT last_value FROM users_id_seq;

SELECT currval('users_id_seq');





modalidade - campeonato - etapa - categoria - prova

O estilo vai ser um enum

create table tipos_modalidades(
	id int8 generated by default as identity, 
	nome varchar(100) not null,
	descricao varchar(300) not null,
primary key (id));	

create table campeonatos(
	id int8 generated by default as identity, 
	empresa_id int2 not null,
	nome varchar(100) not null,
	descricao varchar(300) not null,
primary key (id));	







---------------------------------------------------------------------------------------------------------------------------------------------------------------

NOVO MENU BAIXADO DA INTERNET
=============================
Onde estão as chamadas no menu
------------------------------
pages-menu.ts

Modalidade
----------
ng g c modalidades --skip-tests true
ng g m modalidades --flat true                   /// Ele cria uma pasta modalidade e cria o arquivo la dentro.
ng g m modalidades --routing --flat true
ng g s modalidades --skip-tests true
ng g c modalidades-pesquisa --module modalidades
ng g c modalidades-cadastro --module modalidades --skip-tests true
