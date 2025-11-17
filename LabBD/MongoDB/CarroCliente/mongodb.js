// Montar um Array de BSON a partir dos 2 JSON para inserção na collection cliente (Carro é um atributo de cliente, portanto,
// deve-se substituir o carro do cliente que está apenas a placa, pelo JSON carro)
// Arquivo no cliente.bson

// Inserir os dados em cliente
db.cliente.insertMany([
  { nome:"João Alves", logradouro:"R. Pereira Barreto", numero:1258, bairro:"Jd. Oliveiras", telefone:"21549658",
    carro:{ placa:"DXO9876", marca:"Ford", modelo:"Ka", cor:"Azul", ano:2000 } },

  { nome:"Ana Maria", logradouro:"R. 7 de Setembro", numero:259, bairro:"Centro", telefone:"96588541",
    carro:{ placa:"LKM7380", marca:"Fiat", modelo:"Palio", cor:"Prata", ano:1997 } },

  { nome:"Clara Oliveira", logradouro:"Av. Nações Unidas", numero:10254, bairro:"Pinheiros", telefone:"24589658",
    carro:{ placa:"EGT4631", marca:"Renault", modelo:"Clio", cor:"Verde", ano:2004 } },

  { nome:"José Simões", logradouro:"R. XV de Novembro", numero:36, bairro:"Água Branca", telefone:"78952459",
    carro:{ placa:"BCD7521", marca:"Ford", modelo:"Fiesta", cor:"Preto", ano:1999 } },

  { nome:"Paula Rocha", logradouro:"R. Anhaia", numero:548, bairro:"Barra Funda", telefone:"69582548",
    carro:{ placa:"AFT9087", marca:"VW", modelo:"Gol", cor:"Preto", ano:2007 } }
])

db.cliente.find({});

// Mudar o telefone da cliente cujo nome inicie com Ana para 54329087
db.cliente.updateOne(
  { nome: /^Ana/ },
  { $set: { telefone: "54329087" } }
)

// Fazer uma consulta que retorne quantos clientes tem carros de ano inferior a 2000.
db.cliente.countDocuments({ "carro.ano": { $lt: 2000 } })


// Fazer uma consulta que retorne quais clientes tem carros de ano inferior a 2000 e o carro é da Fiat.
db.cliente.find(
  { 
    "carro.ano": { $lt: 2000 },
    "carro.marca": "Fiat"
  }
)


// Fazer uma consulta que retorne quantos cliente moram no centro e a numeração de casa é inferior a 100
db.cliente.countDocuments({
  bairro: "Centro",
  numero: { $lt: 100 }
})


// Fazer uma consulta que retorne o cliente do bairro Água Branca
db.cliente.find({ bairro: "Água Branca" })

// Fazer uma consulta que retorne os clientes que tem carros de marca Ford ou Renault
db.cliente.find({
  "carro.marca": { $in: ["Ford", "Renault"] }
})
