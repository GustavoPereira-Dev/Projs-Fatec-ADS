# -*- coding: utf-8 -*-
"""DistribuicaoFrequencia.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1IpTOASQZP5Zp2DDF5CEgGzLgqFi9xmAW

# Distribuição de Frequências
"""

import numpy as np
import pandas as pd
from collections import Counter

import warnings
warnings.filterwarnings('ignore')

df = pd.read_csv('/content/drive/MyDrive/Aulas Estatística (Python)/dados_tratados_dolar.csv',
                 sep = ',', encoding = 'iso-8859-1')

df.head(10)

df.shape

df.dtypes

# coluna datas em string
df['datas'] = df['datas'].astype(str)

for i in range(0,7):
  valor = df.loc[i, 'datas']
  df.loc[i, 'datas'] = '0' + valor

df.head(20)

# Incluindo barras nas datas
df['datas'] = df['datas'].apply(lambda x: x[:2] + '/' + x[2:4] + '/' + x[4:])
df.head(20)

df['datas'] = pd.to_datetime(df['datas'], format='%d/%m/%Y')
df.head()

# Criar um dataframe somente com valores de compra
compra = df['compra']
compra

"""# Tabela de Frequências"""

# Frequência absoluta
freq_absoluta = Counter(compra)
freq_absoluta

# Transformando em dataframe
tabela = pd.DataFrame.from_dict(freq_absoluta,orient='index')
tabela

# Colocar em ordem crescente
tabela = tabela.sort_index(ascending=True)

# Colocar os índices reais
tabela.reset_index(inplace=True)
tabela

# Renomear colunas
tabela = tabela.rename(columns={'index':'valor'})
tabela.head()

tabela = tabela.rename(columns={0:'freq_abs'})
tabela.head()

# Frequência Relativa
tabela['freq_rel'] = tabela['freq_abs']/tabela['freq_abs'].sum()
tabela

# Frequência percentual relativa
tabela['freq_rel_perc'] = tabela['freq_rel']*100
tabela

# Frequência acumulada
tabela['freq_acum'] = tabela['freq_abs'].cumsum()
tabela

"""## Tabela de Classes de Frequência"""

tabela.valor.min()

tabela.valor.max()

dif = tabela.valor.max() - tabela.valor.min()
dif

classes = [5.3,5.4,5.5,5.6,5.7]

labels = ['5.3 - 5.4','5.4 - 5.5','5.5 - 5.6','5.6 - 5.7']

intervalos = pd.cut(x = tabela.valor, bins=classes, labels=labels,
                    include_lowest=True)
intervalos

freq_abs = pd.value_counts(intervalos)
freq_abs

freq_rel = pd.value_counts(intervalos, normalize=True)
freq_rel

dist_freq = pd.DataFrame({'freq_abs':freq_abs.values,
                         'freq_rel':freq_rel.values})
dist_freq

dist_freq.sort_index(ascending=True, inplace=True)
dist_freq

dist_freq['freq_rel_perc'] = np.round(dist_freq['freq_rel']*100,2)
dist_freq

dist_freq['freq_acum'] = dist_freq['freq_abs'].cumsum()
dist_freq

"""# Histograma

### Matplotlib
"""

import matplotlib.pyplot as plt

from matplotlib import colors

plt.hist(df.compra, bins= 5, color='red')
plt.title('Histograma')
plt.xlabel('Compra')
plt.ylabel('Frequência')
plt.show()

"""### Seaborn"""

import seaborn as sns

# kde = linha
sns.histplot(df['venda'], bins=5, color='red', kde="True");

"""### Plotly"""

import plotly.express as px
grafico = px.histogram(df, x='compra', nbins= 10)
grafico.update_layout(width=500,height=400,title_text='Histograma')
grafico.show()
