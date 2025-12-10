# MongoDB

## [Carro Cliente](./CarroCliente/)
```sql
CREATE DATABASE exsqlmongo
GO
USE exsqlmongo
GO
CREATE TABLE carro (
    placa CHAR(7) NOT NULL,
    marca VARCHAR(10) NOT NULL,
    modelo VARCHAR(15) NOT NULL,
    cor VARCHAR(20) NOT NULL,
    ano INT NOT NULL
    PRIMARY KEY (placa)
)
GO
CREATE TABLE cliente (
    carro CHAR(7) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero INT NOT NULL,
    bairro VARCHAR(60) NOT NULL,
    telefone VARCHAR(8) NOT NULL
    PRIMARY KEY (carro)
    FOREIGN KEY (carro) REFERENCES carro(placa)
)
GO
INSERT INTO carro VALUES
('AFT9087', 'VW', 'Gol', 'Preto', 2007),
('DXO9876', 'Ford','Ka', 'Azul',2000),
('EGT4631', 'Renault', 'Clio','Verde', 2004),
('LKM7380', 'Fiat','Palio', 'Prata', 1997),
('BCD7521', 'Ford','Fiesta', 'Preto', 1999)
GO
INSERT INTO cliente (nome, logradouro, numero, bairro, telefone, carro) VALUES
('João Alves', 'R. Pereira Barreto', 1258, 'Jd. Oliveiras', '21549658', 'DXO9876'),
('Ana Maria', 'R. 7 de Setembro', 259, 'Centro', '96588541', 'LKM7380'),
('Clara Oliveira', 'Av. Nações Unidas', 10254, 'Pinheiros', '24589658', 'EGT4631'),
('José Simões', 'R. XV de Novembro', 36, 'Água Branca', '78952459', 'BCD7521'),
('Paula Rocha', 'R. Anhaia', 548, 'Barra Funda', '69582548', 'AFT9087')
GO
SELECT * FROM carro FOR JSON PATH
SELECT * FROM cliente FOR JSON PATH

```

- Verificar a existência da pasta C:\data\db (Se não existir, criar);
- Iniciar o MongoDB;
- Usar o Mongo Shell ou o VSCode Playground;

  - Criar, em SQL SERVER as tabelas acima, populando com dados.
  - Criar, em MongoDB, uma database exerciciomongosql
  - Criar, em MongoDB, uma collection chamada cliente
  - Montar um Array de BSON a partir dos 2 JSON para inserção na collection cliente (Carro é um atributo de cliente, portanto, deve-se substituir o carro do cliente que está apenas a placa, pelo JSON carro)
  - Inserir os dados em cliente
  - Mudar o telefone da cliente cujo nome inicie com Ana para 54329087
  - Fazer uma consulta que retorne quantos clientes tem carros de ano inferior a 2000.
  - Fazer uma consulta que retorne quais clientes tem carros de ano inferior a 2000 e o carro é da Fiat.
  - Fazer uma consulta que retorne quantos cliente moram no centro e a numeração de casa é inferior a 100
  - Fazer uma consulta que retorne o cliente do bairro Água Branca
  - Fazer uma consulta que retorne os clientes que tem carros de marca Ford ou Renault

## [Projetos](./Projetos/)

- O dataset abaixo foi formado a partir de uma base de dados e os documentos JSON não estão
bem formados. Os dados dos atributos cloud e vintage não estão disponíveis em todos os
registros, sendo usado NA para cloud não disponíveis e 0 para vintage cujo ano não é
conhecido.

```json
{"datasetName":"Microbiome Project","about":"American Gut (Microbiome Project)","link":"https://github.com/biocore/American-Gut","categoryName":"Biology","cloud":"GitHub","vintage":0},
{"datasetName":"GloBI","about":"Global Biotic Interactions (GloBI)","link":"https://github.com/jhpoelen/eol-globi-data/wiki#accessing-species-interaction-data","categoryName":"Biology","cloud":"GitHub","vintage":0},
{"datasetName":"Global Climate","about":"Global Climate Data Since 1929","link":"http://en.tutiempo.net/climate","categoryName":"Climate/Weather","cloud":"NA","vintage":1929},
{"datasetName":"CommonCraw 2012","about":"3.5B Web Pages from CommonCraw 2012","link":"http://www.bigdatanews.com/profiles/blogs/big-data-set-3-5-billion-web-pages-made-available-for-all-of-us","categoryName":"Computer Networks","cloud":"NA","vintage":2012},
{"datasetName":"Indiana Webclicks","about":"53.5B Web clicks of 100K users in Indiana Univ.","link":"http://cnets.indiana.edu/groups/nan/webtraffic/click-dataset/","categoryName":"Computer Networks","cloud":"NA","vintage":0},
{"datasetName":"Criteo click-through","about":"Criteo click-through data","link":"http://labs.criteo.com/2015/03/criteo-releases-its-new-dataset/","categoryName":"Computer Networks","cloud":"NA","vintage":0},
{"datasetName":"ICWSM 2009","about":"ICWSM Data Challenge (since 2009)","link":"http://icwsm.cs.umbc.edu/","categoryName":"Data Challenges","cloud":"NA","vintage":2009},
{"datasetName":"KDD Cup","about":"KDD Cup by Tencent 2012","link":"http://www.kddcup2012.org/","categoryName":"Data Challenges","cloud":"NA","vintage":2012},
{"datasetName":"Localytics Data","about":"Localytics Data Visualization Challenge","link":"https://github.com/localytics/data-viz-challenge","categoryName":"Data Challenges","cloud":"GitHub","vintage":0},
{"datasetName":"Yelp Dataset","about":"Yelp Dataset Challenge","link":"http://www.yelp.com/dataset_challenge","categoryName":"Data Challenges","cloud":"NA","vintage":0},
{"datasetName":"Bruteforce Database","about":"Bruteforce Database","link":"https://github.com/duyetdev/bruteforce-database","categoryName":"Data Challenges","cloud":"GitHub","vintage":0},
{"datasetName":"Countries","about":"List of all countries in all languages","link":"https://github.com/umpirsky/country-list","categoryName":"GIS","cloud":"GitHub","vintage":0},
{"datasetName":"TwoFishes","about":"TwoFishes - Foursquare's coarse geocoder","link":"https://github.com/foursquare/twofishes","categoryName":"GIS","cloud":"GitHub","vintage":0},
{"datasetName":"World countries","about":"World countries in multiple formats","link":"https://github.com/mledoze/countries","categoryName":"GIS","cloud":"GitHub","vintage":0},
{"datasetName":"Cities and countries","about":"A list of cities and countries contributed by community","link":"https://github.com/caesar0301/awesome-public-datasets/blob/master/Government.rst","categoryName":"Government","cloud":"GitHub","vintage":0},
{"datasetName":"Ebola cases","about":"Number of Ebola Cases and Deaths in Affected Countries (2014)","link":"https://data.hdx.rwlabs.org/dataset/ebola-cases-2014","categoryName":"Healthcare","cloud":"NA","vintage":2014},
{"datasetName":"eBay Online","about":"eBay Online Auctions (2012)","link":"http://www.modelingonlineauctions.com/datasets","categoryName":"Machine Learning","cloud":"NA","vintage":2012},
{"datasetName":"New Yorker Captions","about":"New Yorker caption contest ratings","link":"https://github.com/nextml/caption-contest-data","categoryName":"Machine Learning","cloud":"GitHub","vintage":0},
{"datasetName":"Cooper-Hewitt's Collection","about":"Cooper-Hewitt's Collection Database","link":"https://github.com/cooperhewitt/collection","categoryName":"Museums","cloud":"GitHub","vintage":0},
{"datasetName":"Minneapolis Institute","about":"Minneapolis Institute of Arts metadata","link":"https://github.com/artsmia/collection","categoryName":"Museums","cloud":"GitHub","vintage":0},
{"datasetName":"Tate Collection","about":"Tate Collection metadata","link":"https://github.com/tategallery/collection","categoryName":"Museums","cloud":"GitHub","vintage":0},
{"datasetName":"Google 5gram","about":"Google Web 5gram (1TB; 2006)","link":"https://catalog.ldc.upenn.edu/LDC2006T13","categoryName":"Natural Language","cloud":"NA","vintage":2006},
{"datasetName":"Arabic; 30K articles","about":"SaudiNewsNet Collection of Saudi Newspaper Articles (Arabic; 30K articles)","link":"https://github.com/ParallelMazen/SaudiNewsNet","categoryName":"Natural Language","cloud":"GitHub","vintage":0},
{"datasetName":"USENET postings","about":"USENET postings corpus of 2005~2011","link":"http://www.psych.ualberta.ca/~westburylab/downloads/usenetcorpus.download.html","categoryName":"Natural Language","cloud":"NA","vintage":2005},
{"datasetName":"Datahub.io","about":"Datahub.io","link":"https://datahub.io/dataset","categoryName":"Search Engines","cloud":"NA","vintage":0},
{"datasetName":"Twitter Scrape CIKM","about":"Cheng-Caverlee-Lee September 2009 - January 2010 Twitter Scrape","link":"https://archive.org/details/twitter_cikm_2010","categoryName":"Social Networks","cloud":"NA","vintage":2009},
{"datasetName":"Facebook Data","about":"Facebook Data Scrape (2005)","link":"https://archive.org/details/oxford-2005-facebook-matrix","categoryName":"Social Networks","cloud":"NA","vintage":2005},
{"datasetName":"LAW graphs","about":"Facebook Social Networks from LAW (since 2007)","link":"http://law.di.unimi.it/datasets.php","categoryName":"Social Networks","cloud":"NA","vintage":2007},
{"datasetName":"Foursquare from","about":"Foursquare from UMN/Sarwat (2013)","link":"https://archive.org/details/201309_foursquare_dataset_umn","categoryName":"Social Networks","cloud":"NA","vintage":2013},
{"datasetName":"Skytrax' Air","about":"Skytrax' Air Travel Reviews Dataset","link":"https://github.com/quankiquanki/skytrax-reviews-dataset","categoryName":"Social Networks","cloud":"GitHub","vintage":0},
{"datasetName":"Twitter Scrape","about":"Twitter Scrape Calufa May 2011","link":"http://archive.org/details/2011-05-calufa-twitter-sql","categoryName":"Social Networks","cloud":"NA","vintage":2011},
{"datasetName":"Youtube Video","about":"Youtube Video Social Graph in 2007;2008","link":"http://netsg.cs.sfu.ca/youtubedata/","categoryName":"Social Networks","cloud":"NA","vintage":2007},
{"datasetName":"FBI Hate Crime 2013","about":"FBI Hate Crime 2013 - aggregated data","link":"https://github.com/emorisse/FBI-Hate-Crime-Statistics/tree/master/2013","categoryName":"Social Sciences","cloud":"GitHub","vintage":2013},
{"datasetName":"GSS","about":"General Social Survey (GSS) since 1972","link":"http://gss.norc.org","categoryName":"Social Sciences","cloud":"NA","vintage":1972},
{"datasetName":"Texas Inmates","about":"Texas Inmates Executed Since 1984","link":"http://www.tdcj.state.tx.us/death_row/dr_executed_offenders.html","categoryName":"Social Sciences","cloud":"NA","vintage":1984},
{"datasetName":"Formula 1","about":"Ergast Formula 1; from 1950 up to date (API)","link":"http://ergast.com/mrd/db","categoryName":"Sports","cloud":"NA","vintage":1950},
{"datasetName":"Pinhooker: Thoroughbred","about":"Pinhooker: Thoroughbred Bloodstock Sale Data","link":"https://github.com/phillc73/pinhooker","categoryName":"Sports","cloud":"GitHub","vintage":0},
{"datasetName":"Airlines OD","about":"Airlines OD Data 1987-2008","link":"http://stat-computing.org/dataexpo/2009/the-data.html","categoryName":"Transportation","cloud":"NA","vintage":2008},
{"datasetName":"BSS","about":"Bike Share Systems (BSS) collection","link":"https://github.com/BetaNYC/Bike-Share-Data-Best-Practices/wiki/Bike-Share-Data-Systems","categoryName":"Transportation","cloud":"GitHub","vintage":0},
{"datasetName":"NYC Taxi","about":"NYC Taxi Trip Data 2009-","link":"http://www.nyc.gov/html/tlc/html/about/trip_record_data.shtml","categoryName":"Transportation","cloud":"NA","vintage":2009},
{"datasetName":"FOIA/FOILed","about":"NYC Taxi Trip Data 2013 (FOIA/FOILed)","link":"https://archive.org/details/nycTaxiTripData2013","categoryName":"Transportation","cloud":"NA","vintage":2013},
{"datasetName":"NYC Uber","about":"NYC Uber trip data April 2014 to September 2014","link":"https://github.com/fivethirtyeight/uber-tlc-foil-response","categoryName":"Transportation","cloud":"GitHub","vintage":2014},
{"datasetName":"Open Traffic","about":"Open Traffic collection","link":"https://github.com/graphhopper/open-traffic-collection","categoryName":"Transportation","cloud":"GitHub","vintage":0},
{"datasetName":"Plane Crash","about":"Plane Crash Database; since 1920","link":"http://www.planecrashinfo.com/database.htm","categoryName":"Transportation","cloud":"NA","vintage":1920},
{"datasetName":"U.S. Domestic","about":"U.S. Domestic Flights 1990 to 2009","link":"http://academictorrents.com/details/a2ccf94bbb4af222bf8e69dad60a68a29f310d9a","categoryName":"Transportation","cloud":"NA","vintage":2009},
{"datasetName":"U.S. Freight","about":"U.S. Freight Analysis Framework since 2007","link":"http://ops.fhwa.dot.gov/freight/freight_analysis/faf/index.htm","categoryName":"Transportation","cloud":"NA","vintage":2007},
{"datasetName":"Data Packaged","about":"Data Packaged Core Datasets","link":"https://github.com/datasets/","categoryName":"Complementary Collections","cloud":"GitHub","vintage":0},
{"datasetName":"USDA PLANTS","about":"U.S. Department of Agriculture's PLANTS Database","link":"http://www.plants.usda.gov/dl_all.html","categoryName":"Agriculture","cloud":"NA","vintage":0},
{"datasetName":"ClueWeb09","about":"ClueWeb09 - 1B web pages","link":"http://lemurproject.org/clueweb09/","categoryName":"Computer Networks","cloud":"NA","vintage":2009},
{"datasetName":"ClueWeb12","about":"ClueWeb12 - 733M web pages","link":"http://lemurproject.org/clueweb12/","categoryName":"Computer Networks","cloud":"NA","vintage":2012},
{"datasetName":"DEFRA Projects","about":"DEFRA Science and Research Projects data","link":"http://randd.defra.gov.uk/","categoryName":"Energy","cloud":"NA","vintage":0},
{"datasetName":"UK-DALE","about":"UK Domestic Appliance-Level Electricity (UK-DALE) dataset","link":"http://www.doc.ic.ac.uk/~dk3810/data/","categoryName":"Energy","cloud":"NA","vintage":2016},
{"datasetName":"Landsat 8","about":"Landsat 8 on AWS","link":"https://aws.amazon.com/public-data-sets/landsat/","categoryName":"GIS","cloud":"Amazon","vintage":0},
{"datasetName":"Reverse Geocode","about":"Simple but fast reverse geocoding up to city granularitiy level","link":"https://github.com/kno10/reversegeocode","categoryName":"GIS","cloud":"GitHub","vintage":0},
{"datasetName":"Faces Database","about":"10k US Adult Faces Database","link":"http://wilmabainbridge.com/facememorability2.html","categoryName":"Image Processing","cloud":"NA","vintage":0},
{"datasetName":"ClueWeb09 FACC","about":"ClueWeb09 FACC","link":"http://lemurproject.org/clueweb09/FACC1/","categoryName":"Natural Language","cloud":"NA","vintage":2009},
{"datasetName":"ClueWeb12 FACC","about":"ClueWeb12 FACC","link":"http://lemurproject.org/clueweb12/FACC1/","categoryName":"Natural Language","cloud":"NA","vintage":2012},
{"datasetName":"Google Ngrams","about":"Google Books Ngrams (2.2TB)","link":"https://aws.amazon.com/datasets/google-books-ngrams/","categoryName":"Natural Language","cloud":"Amazon","vintage":0},
{"datasetName":"EDRM Enron","about":"EDRM Enron EMail of 151 users; hosted on S3","link":"https://aws.amazon.com/datasets/enron-email-data/","categoryName":"Social Networks","cloud":"Amazon","vintage":0},
{"datasetName":"GetGlue","about":"GetGlue - users rating TV shows","link":"http://getglue-data.s3.amazonaws.com/getglue_sample.tar.gz","categoryName":"Social Networks","cloud":"NA","vintage":0},
{"datasetName":"Twitter RepLab","about":"Twitter Data for Online Reputation Management","link":"http://nlp.uned.es/replab2013/","categoryName":"Social Networks","cloud":"NA","vintage":2013}
```


- Fazer os inserts em um banco de dados MongoDB, na collection Project, com os dados como
disponibilizados
- Fazer updates para que clouds: NA e vintage:0 sejam removidos da base.
- Consultar (Sempre com saída pretty):
  - Quais projects tem datasetName que tem Clue no valor
  - Quais projects tem vintage inferior a 2000, ordenados crescentemente por
about
  - Quais projects tem vintage igual ou superior a 2000 e cloud, ordenados de maneira
decrescente por vintage
  - Quais projects tem link que tenha os termos wiki ou plane no link
  - Quais projects que o categoryName seja Transportation e tenha alguma cloud.
  - Quantos projects que o categoryName seja Sports e tenha algum vintage
  - Quantos projetos o link começa por https e tenham algum cloud
  - Quais projetos o about contém Youtube e tenham algum vintage
  - Apenas o primeiro projeto que retorne de uma busca de projeto que tenha uma cloud
GitHub e o vintage seja maior que 2005, com ordenação decrescente.


## [Escola](./Escola/)

- Criar, em MongoDB, uma database chamada db_escola e uma collection chamada aluno
com os dados abaixo. Usar Object Id para _id em todos os alunos:

```json
[
{
    "nome": "Ana Silva",
    "idade": 20,
    "curso": "Engenharia de Computação",
    "notas": [8.5, 7.0, 9.0],
    "cidade": "São Paulo"
},
{
    "nome": "Bruno Souza",
    "idade": 22,
    "curso": "Ciência da Computação",
    "notas": [6.0, 8.0, 7.5],
    "cidade": "Rio de Janeiro"
},
{
    "nome": "Carlos Pereira",
    "idade": 21,
    "curso": "Sistemas de Informação",
    "notas": [9.0, 9.5, 10.0],
    "cidade": "Belo Horizonte"
},
{
    "nome": "Daniela Costa",
    "idade": 23,
    "curso": "Engenharia de Software",
    "notas": [7.5, 6.0, 8.0],
    "cidade": "Curitiba"
},
{
    "nome": "Eduardo Lima",
    "idade": 20,
    "curso": "Análise e Desenvolvimento de Sistemas",
    "notas": [8.0, 8.5, 9.0],
    "cidade": "Porto Alegre"
}
```

Fazer:
1) Inserir um novo documento na coleção alunos com nome Fernanda Souza, de 19 anos, do curso
de Engenharia da Computação, com notas 7.0, 7.5 e 8.0, da cidade de Salvador.
2) Fazer a inserção múltipla dos alunos Gabriel Santana de 24 anos do curso de Engenharia da
Computação, com notas 7.5, 8.0 e 8.5 da cidade de Fortaleza e Helena Marques de 21 anos, do
curso de Ciência da Computação com notas 8.0, 8.5 e 9.0, da cidade de Manaus.
3) Consultar todos os alunos cujo curso seja de algum tipo de Engenharia.
4) Consultar os alunos que tem 21 anos ou mais, com saída ordenada pelo nome de forma
crescente.
5) Consultar alunos que tem nota 8.0 nas suas notas.
6) Consultar alunos da cidade de São Paulo.
7) Consultar alunos que tem pelo menos uma nota acima de 9.0, com saída ordenada pelo nome
de forma decrescente.
8) Consultar alunos que tem idade entre 20 e 22 anos, inclusive, ordenado por idade, em ordem
crescente.
9) Consultar os alunos do curso de Ciência da Computação, com saída ordenada pelo nome de
forma crescente.