
// O dataset abaixo foi formado a partir de uma base de dados e os documentos JSON não estão
// bem formados. Os dados dos atributos cloud e vintage não estão disponíveis em todos os
// registros, sendo usado NA para cloud não disponíveis e 0 para vintage cujo ano não é
// conhecido.

// Fazer os inserts em um banco de dados MongoDB, na collection Project, com os dados como disponibilizados
use ('db_datasets')

db.projetos.insertMany([
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
]);

db.projetos.find({});

// Fazer updates para que clouds: NA e vintage:0 sejam removidos da base.
// Remover cloud: "NA"
db.projetos.updateMany(
    { cloud: "NA" },
    { $unset: { cloud: "" } }
);

// Remover vintage: 0
db.projetos.updateMany(
    { vintage: 0 },
    { $unset: { vintage: "" } }
);

db.projetos.find({});

// Consultar (Sempre com saída pretty):
// Quais projects tem datasetName que tem Clue no valor
db.projetos.find(
    { datasetName: { $regex: /Clue/ } }
).pretty();

// Quais projects tem vintage inferior a 2000, ordenados crescentemente por about
db.projetos.find(
    { vintage: { $lt: 2000 } }
).sort({ about: 1 }).pretty();

// Quais projects tem vintage igual ou superior a 2000 e cloud, ordenados de maneira decrescente por vintage
db.projetos.find(
    { 
        vintage: { $gte: 2000 }, 
        cloud: { $exists: true } 
    }
).sort({ vintage: -1 }).pretty();

// Quais projects tem link que tenha os termos wiki ou plane no link
db.projetos.find(
    { link: { $regex: /wiki|plane/ } }
).pretty();

// Quais projects que o categoryName seja Transportation e tenha alguma cloud.
db.projetos.find(
    { 
        categoryName: "Transportation", 
        cloud: { $exists: true } 
    }
).pretty();

// Quantos projects que o categoryName seja Sports e tenha algum vintage
db.projetos.find(
    { 
        categoryName: "Sports", 
        vintage: { $exists: true } 
    }
).count();

// Quantos projetos o link começa por https e tenham algum cloud
db.projetos.find(
    { 
        link: { $regex: /^https/ }, 
        cloud: { $exists: true } 
    }
).count();

// Quais projetos o about contém Youtube e tenham algum vintage
db.projetos.find(
    { 
        about: { $regex: /Youtube/ }, 
        vintage: { $exists: true } 
    }
).pretty();

// Apenas o primeiro projeto que retorne de uma busca de projeto que tenha uma cloud GitHub e o vintage seja maior que 2005, com ordenação decrescente.
db.projetos.find(
    { 
        cloud: "GitHub", 
        vintage: { $gt: 2005 } 
    }
).sort({ vintage: -1 }).limit(1).pretty();