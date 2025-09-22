
## ProcedureSQL2
- Um sistema necessita que o usuário cadastre, obrigatoriamente,
um telefone celular e um telefone fixo, ou seja, não podem ser, os dois, nulos.
Um erro deve ser disparado caso haja violação.
- O sistema funciona em São Paulo e não cadastrará pessoas de outros
estados da federação.
- A pessoa pode não ter um dos dois telefones, mas não é permitido
omitir os 2 telefones, bem como cadastrar 2 fixos ou 2 celulares.
Um erro deve ser disparado caso haja violação.
- Telefones celular têm 9 dígitos e telefones fixo têm 8 dígitos, que
devem ser validados. Um erro deve ser disparado caso haja violação.
- Ao inserir uma pessoa, deve-se criar um novo ID incremental com
relação ao último ID inserido. A tabela não terá identity.
- Criar uma procedure que cadastre trate o CRUD da pessoa e as regras de 
negócio acima.