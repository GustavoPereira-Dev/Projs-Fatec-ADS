# -*- coding: utf-8 -*-

# Automatically generated by Colab.

# importação das bibliotecas
import numpy as np
import pandas as pd

dados = pd.read_csv('endereco/CotacoesMoedasPeriodo.csv',
                    sep=';', encoding='UTF-8', header = None) # https://www.bcb.gov.br/estabilidadefinanceira/historicocotacoes)

dados.head(20) # primeiras linhas (por padrão 5)

dados.tail() # últimas linhas (por padrão 5)

dados.shape # números de linhas e colunas

dados.columns = ["datas","codigo","tipo","simbolo","compra","venda","ns1","ns2"] # adicionar nomes as colunas

dados.head()

dados = dados.rename(columns={"ns2": "ns10"}) # alterar nome de coluna já definido
dados.head()

dados1 = dados.drop(columns=["ns1","ns10"])
dados1.head(2)

# prompt: excluir linha no dados1 e passar ao mesmo

dados1 = dados1.drop(1)
dados1.head(2)

"""Análise dos tipos de atributos:

object(nominal): string

int64(discreta): inteiro

float(contínua): reais

complex: complexos
"""

dados1.dtypes

dados["compra"] = dados1["compra"].apply(lambda x: x.replace(",","."))
dados.head()

dados["venda"] = dados1["venda"].apply(lambda x: x.replace(",","."))
dados.head()

dados['compra'] = dados['compra'].astype(float)
dados['venda'] = dados['venda'].astype(float)

dados.dtypes

# Alterar todos de uma vez
dados[['compra','venda']] = dados[['compra','venda']].apply(pd.to_numeric)

# Salvando arquivo
dados.to_csv("dados_tratado_dolar", encoding = "iso-8859-1", index = False)