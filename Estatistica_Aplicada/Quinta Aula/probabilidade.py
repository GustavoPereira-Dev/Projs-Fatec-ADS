# -*- coding: utf-8 -*-
"""Probabilidade.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1M9mYx_P2rIgYn3aBiBQo5e1aDv0h4OI6
"""

import numpy as np
import pandas as pd

from google.colab import drive
drive.mount('/content/drive')

dados = pd.read_csv('/content/drive/MyDrive/Aulas Estatística (Python)/pesquisa_tratada_exemplo.csv',
                    sep = ",", encoding = "UTF-8")
dados.head(3)

# Gráfico de setor (pizza) de cor favorita
import matplotlib.pyplot as plt

# Contando as ocorrências de cada cor favorita
contagem_cores = dados['cor_favorita'].value_counts()

# Criando o gráfico de pizza
plt.pie(contagem_cores, labels=contagem_cores.index, autopct='%1.1f%%')
plt.title("Distribuição de cores favoritas")
# plt.axis('equal') # Para garantir que o gráfico seja um círculo (caso não dê)
plt.show()

"""# **Distribuição de Cores Favoritas**"""

# Gráfico de barras da cor favorita

plt.bar(contagem_cores.index, contagem_cores.values, color="gray")
plt.xlabel("Cores favoritas")
plt.ylabel("Contagem")
plt.title("Distribuição de cores favoritas")
plt.show()

# Histograma da Altura
plt.hist(dados['altura'], bins=7, color="green", edgecolor="yellow")
plt.xlabel("Altura")
plt.ylabel("Frequência")
plt.title("Histograma da Altura")
plt.show()

cor_preto = dados.loc[dados.cor_favorita.isin(["preta","preto"])]
cor_preto.head(6)

altura1 = dados.loc[dados.altura < 1.70]
altura1.head(12)

len(dados)

len(cor_preto)

len(altura1)

# Função Probabilidade
def probab(A,E):
  resultado = (A/E)*100
  print('{:.2f}'.format(resultado))

# Probabilidade de retirar preferência cor preto
probab(len(cor_preto),len(dados))

# Probabilidade de ser menos que 1.70
probab(len(altura1),len(dados))

"""## **Probabilidade de não ocorrer um evento**"""

# Criando função probabilidade de não ocorrer um evento
def probab_nao(A,E):
  resultado = (1-(A/E))*100
  print('{:.2f}'.format(resultado))

cor_vermelho = dados.loc[dados.cor_favorita.isin(["vermelha","vermelho"])]
cor_vermelho.head()

# Probabilidade de não retirar cor favorita vermelho
probab_nao(len(cor_vermelho),len(dados))

"""## **Probabilidade da intersecção de dois eventos independentes**"""

def probab_inter(A,B,E):
  resultado = ((A/E)*(B/E))*100
  print('{:.1f}'.format(resultado))

# Probabilidade de sortear duas pessoas, com reposição, sendo uma delas com
# a altura menor que 1,70M e (intersecção) a outra cor favorita preta
probab_inter(len(cor_preto),len(altura1),len(dados))

"""## **Probabilidade da União (AUB)**"""

def probab_uniao(A,B,AB,E):
  resultado = ((A/E)+(B/E)-(AB/E))*100
  print('{:.2f}'.format(resultado))

# prompt: Faça a chamada da probabilidade da união com cor_preto, altura1 e dados

# Probabilidade da união (cor_preto ou altura1)
probab_uniao(len(cor_preto), len(altura1), int(((len(cor_preto)/len(altura1))*(len(altura1)/len(dados))))*100, len(dados))