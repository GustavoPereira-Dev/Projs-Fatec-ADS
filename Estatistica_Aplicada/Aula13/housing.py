# -*- coding: utf-8 -*-
"""housing.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1c0NILPJvx8GklGRXE3LjzwCzPJLao9ka
"""

import numpy as np
import pandas as pd

from google.colab import drive
drive.mount('/content/drive')

housing = pd.read_csv('/content/drive/MyDrive/Aulas Estatística (Python)/Lab2/housing.csv',
                    sep=",", encoding='iso-8859-1')
housing.head()

housing.shape

housing.dtypes

"""1) Apresente os dados da estatística descritiva (média, mediana, moda, quartis...) sobre as duas variáveis? Essas variáveis possuem outliers (apresentar o gráfico BoxPlot)?

Dados da estatística descritiva
"""

housing["MEDV"].describe()

housing["RM"].describe()

"""Gráficos BoxPlot"""

import plotly.express as px

grafico_medv = px.box(housing, y="MEDV")
grafico_medv.show()

grafico_rm = px.box(housing, y="RM")
grafico_rm.show()

"""R: É possíver ver que existem muitos outliers nessas variáveis (tanto em MEDV e em RM)

2) Apresente os resultados dos testes de normalidade (gráfico QQPlot e teste de hipótese). Os dados possuem distribuição normal?
"""

import scipy.stats as stats
import matplotlib.pyplot as plt

stats.probplot(housing['MEDV'], dist="norm", plot=plt)
plt.title("Normal Q-Q plot")
plt.show()

stats.probplot(housing['RM'], dist="norm", plot=plt)
plt.title("Normal Q-Q plot")
plt.show()

"""Teste de Shapiro-Wilk

CRITÉRIOS:

NÍVEL DE SIGNIFICÂNCIA DE 0,05 ou 5% (MAIS UTILIZADO)

Aceita Ho: p >= 0,05 (distribuição normal).

Rejeita Ho: p < 0,05 (distribuição não normal).
"""

stats.shapiro(housing.MEDV)

stats.shapiro(housing.RM)

"""Tanto MEDV e RM Rejeitam o Ho (p < 0,05 = distribuição não normal)

Conclusão: ambos os dados não possuem distribuição normal

3) Qual o coeficiente de correlação adequado para essa situação (Pearson, Spearman ou Kendall). Por quê?

R: Spearman

Pois como as variáveis não possuem normalidade, Pearson já não seria possível de usar, ficando a escolha entre Spearman ou Kendall

Contudo, já que existem mais de 400 consultas referente ao "MEDV" e "RM", Spearman possivelmente se encaixaria melhor do que o Kendall (que atende distribuições não normais com uma quantidade pequena de amostras)

4) Qual o valor do coeficiente de correlação entre as variáveis?
"""

import scipy.stats as stats

# Spearman
coef,p = stats.spearmanr(housing.MEDV,housing.RM)
print('Coeficiente de correlação: {}'.format(coef))

"""5) Classifique o tipo de correlação entre as variáveis (positiva, negativa, forte, fraca, muito forte, moderada...)?

R: A correlação entre as variáveis seria uma positiva possivelmente Moderada (entre 0,5 e 0,8 o coeficiente)

6) Qual o coeficiente de determinação entre as variáveis?
"""

print('Coeficiente de determinação: {}'.format(coef**2))

"""7) Apresente um gráfico com os valores observados e com a reta de regressão."""

import seaborn as sns

sns.regplot(x='RM', y='MEDV', data=housing, line_kws={'color': 'red'})
plt.title('MEDV vs. RM com Reta de Regressão')
plt.xlabel('RM')
plt.ylabel('MEDV')
plt.show()

"""8) Qual a equação do modelo de regressão?"""

import statsmodels.formula.api as smf
import statsmodels.stats.api as sms

# Criação do modelo
regressao = smf.ols('MEDV ~ RM', data = housing).fit()

residuos = regressao.resid
residuos

regressao.summary()

regressao.params

"""**Resposta:**
*MEDV = -663283.910583 + 179098.591898*

9) Determine a previsão para o valor de um imóvel dessa região com 11 cômodos.
"""

predicted_value = regressao.params[0] + regressao.params[1] * 11
print(f"A previsão para o valor de um imóvel com 11 cômodos é: {predicted_value}")

"""10) Considerando nível de significância de 0,01, há evidências para afirmar que a média dos valores médios das casas com RM (número médio de cômodos) entre 6 e 7 é igual a média dos valores médios de todas as casas? Justifique a conclusão com os resultados do teste de hipótese, destacando o teste de hipótese utilizado."""

# Filtra as casas com RM entre 6 e 7
casas_rm_6_7 = housing[(housing['RM'] >= 6) & (housing['RM'] <= 7)]

# Define as hipóteses
# H0: média dos valores médios das casas com RM entre 6 e 7 = média dos valores médios de todas as casas
# H1: média dos valores médios das casas com RM entre 6 e 7 != média dos valores médios de todas as casas

# Realiza o teste t de duas amostras independentes
t_stat, p_valor = stats.ttest_ind(casas_rm_6_7['MEDV'], housing['MEDV'])

# Imprime os resultados
print(f"Estatística t: {t_stat}")
print(f"Valor-p: {p_valor}")

# Interpreta os resultados com base no nível de significância de 0.01
alpha = 0.01
if p_valor < alpha:
  print("Rejeitamos a hipótese nula. Há evidências para afirmar que a média dos valores médios das casas com RM entre 6 e 7 é diferente da média dos valores médios de todas as casas.")
else:
  print("Não rejeitamos a hipótese nula. Não há evidências suficientes para afirmar que a média dos valores médios das casas com RM entre 6 e 7 é diferente da média dos valores médios de todas as casas.")