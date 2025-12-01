// Criar, em MongoDB, uma database chamada db_escola e uma collection chamada aluno 
// com os dados abaixo. Usar Object Id para _id em todos os alunos: 

use db_escola

db.aluno.insertMany([
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
])

// 1) Inserir um novo documento na coleção alunos com nome Fernanda Souza, de 19 anos, do curso 
// de Engenharia da Computação, com notas 7.0, 7.5 e 8.0, da cidade de Salvador. 
db.aluno.insertOne({
  "nome": "Fernanda Souza",
  "idade": 19,
  "curso": "Engenharia da Computação",
  "notas": [7.0, 7.5, 8.0],
  "cidade": "Salvador"
})

// 2) Fazer a inserção múltipla dos alunos Gabriel Santana de 24 anos do curso de Engenharia da 
// Computação, com notas 7.5, 8.0 e 8.5 da cidade de Fortaleza e Helena Marques de 21 anos, do 
// curso de Ciência da Computação com notas 8.0, 8.5 e 9.0, da cidade de Manaus. 
db.aluno.insertMany([
  {
    "nome": "Gabriel Santana",
    "idade": 24,
    "curso": "Engenharia da Computação",
    "notas": [7.5, 8.0, 8.5],
    "cidade": "Fortaleza"
  },
  {
    "nome": "Helena Marques",
    "idade": 21,
    "curso": "Ciência da Computação",
    "notas": [8.0, 8.5, 9.0],
    "cidade": "Manaus"
  }
])

// 3) Consultar todos os alunos cujo curso seja de algum tipo de Engenharia.
db.aluno.find({ "curso": /Engenharia/ })

// 4) Consultar os alunos que tem 21 anos ou mais, ordenado por nome (crescente).
db.aluno.find({ "idade": { $gte: 21 } }).sort({ "nome": 1 })

// 5) Consultar alunos que tem nota 8.0 nas suas notas.
db.aluno.find({ "notas": 8.0 })

// 6) Consultar alunos da cidade de São Paulo
db.aluno.find({ "cidade": "São Paulo" })

// 7) Consultar alunos com pelo menos uma nota acima de 9.0, ordenado por nome (decrescente).
db.aluno.find({ "notas": { $gt: 9.0 } }).sort({ "nome": -1 })

// 8) Consultar alunos com idade entre 20 e 22 anos (inclusive), ordenado por idade (crescente).
db.aluno.find({ 
  "idade": { $gte: 20, $lte: 22 } 
}).sort({ "idade": 1 })

// 9) Consultar os alunos de Ciência da Computação, ordenado por nome (crescente)
db.aluno.find({ "curso": "Ciência da Computação" }).sort({ "nome": 1 })