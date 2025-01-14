# -*- coding: utf-8 -*-
"""DistribuicaoProbabilidade.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1-D3CA6DVgas2hHY2FFmtpRqlDV6e8Iil
"""

import numpy as np
import pandas as pd

enem_sp = pd.read_csv("/content/drive/MyDrive/Aulas Estatística (Python)/enem_2019_tratado.csv",
                      sep=",",encoding="iso-8859-1")

enem_sp.shape

enem_sp.head(2)

"""# Distribuição Binomial"""

from scipy.stats import binom

# PROBABILIDADE DE RETIRAR UMA MULHER
mulher_enem = enem_sp.loc[enem_sp["SEXO"] == "F"]

len(mulher_enem)

p = len(mulher_enem)/len(enem_sp)
p

"""binom.pmf = valor pontual
binom.cdf = faixa de valores (acumulada)
"""

# PROBABILIDADE DE RETIRAR EXATAMENTE 4 MULHERES NUM TOTAL DE 10 AMOSTRAS
binom.pmf(4,10,p)

# Primeiro parâmetro: valor ou limite que se está pretendendo calcular
# Segundo: Número de tentativas
# Terceiro: Probabilidade de um sucesso.

# PROBABILIDADE DE RETIRAR PELO MENOS UMA MULHER NUM TOTAL DE 10 AMOSTRAS

p0 = 1 - binom.pmf(0,10,p)
p0

p0 = binom.cdf(10,10,p) - binom.cdf(0,10,p)
p0

# PROBABILIDADE DE RETIRAR MAIS DO QUE 2 MULHERES NUM TOTAL DE 10 AMOSTRAS
p1 = 1 - (binom.pmf(0,10,p)+binom.pmf(1,10,p)+binom.pmf(2,10,p))
p1

# Usando a cumulativa
p1 = 1 - binom.cdf(2,10,p)
p1

# PROBABILIDADE DE RETIRAR MAIS DO QUE 3 MULHERES NUM TOTAL DE 10 AMOSTRAS
p2 = binom.pmf(4,10,p) + binom.pmf(4,10,p) + binom.pmf(4,10,p) + binom.pmf(5,10,p) + binom.pmf(6,10,p) + binom.pmf(7,10,p) + binom.pmf(8,10,p) + binom.pmf(9,10,p) + binom.pmf(10,10,p)

p2 = 1 - (binom.pmf(0,10,p) + binom.pmf(1,10,p) + binom.pmf(2,10,p) + binom.pmf(3,10,p))
p2

# Cumulativa
p3 = 1 - binom.cdf(3,10,p)
p3

# PROBABILIDADE DE RETIRR MAIS DO QUE 8 MULHERES NUM TOTAL DE 10 AMOSTRAS
p4 = binom.pmf(9,10,p) + binom.pmf(10,10,p)
p4

# OUTRA FORMA
p5 = 1 - binom.cdf(8,10,p)
p5

"""# Distribuição Geométrica"""

from scipy.stats import geom

# PROBABILIDADE DE RETIRAR UMA MULHER NA QUARTA TENTATIVA
# geom.pmf(x,p) x representa a tentativa que se obteve sucesso e p a probabilidade
geom.pmf(4,p)

"""# Distribuição de Poisson"""

from scipy.stats import poisson

"""3) Um corpo de bombeiros de uma determinada cidade recebe em média 5 solicitações de atendimento por hora

Qual a possibilidade de receber 2 solicitações numa hora selecionada aleatoriamente?

Qual a probabilidade de receber até 2 solicitações numa hora selecionada aleatoriamente?
"""

# poisson.pmf(x,m) x é a quantidade de ocorrências e m é a taxa de ocorrências.
poisson.pmf(2,5)

poisson.cdf(2,5)

"""# Distrubuição Contínua

Exemplo: Um fabricante de automóveis garante que seus veículos podem ser utilizados por 4,8 anos, em média, sem ter que trocar nenhuma peça com desvio padrão de 0,5 anos. Um proprietário de um veículo desse fabricante é selecionado de uma forma aleatória.

Qual a probabilidade de ele ter que trocar uma peça em menos de 4,5 anos? Considere esse problema com distribuição normal.
"""

from scipy.stats import norm

norm.cdf(4.5,loc=4.8,scale=0.5)

"""## Qual a probabilidade de sortear uma pessoa com idade inferior a 17 anos no Enem 2019? Considerando distribuição normal"""

enem_sp.IDADE.mean()

enem_sp.IDADE.std()

norm.cdf(17, loc=enem_sp.IDADE.mean(), scale=enem_sp.IDADE.std())

"""## Qual a probabilidade de sortear uma pessoa com idade superior a 50 anos no Enem 2019?"""

sup = 1 - (norm.cdf(50, loc=enem_sp.IDADE.mean(), scale=enem_sp.IDADE.std()))
sup

"""## Qual a probabilidade de sortear uma pessoa com idade entre 20 e 30 anos no Enem 2019?"""

1 - ((norm.cdf(30, loc=enem_sp.IDADE.mean(), scale=enem_sp.IDADE.std())) - (norm.cdf(20, loc=enem_sp.IDADE.mean(), scale=enem_sp.IDADE.std())))