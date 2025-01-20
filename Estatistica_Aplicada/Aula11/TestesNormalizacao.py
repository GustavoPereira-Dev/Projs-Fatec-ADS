import numpy as np

def mann_whitney_u_test(amostra1, amostra2):
    n1 = len(amostra1)
    n2 = len(amostra2)
    todas_amostras = amostra1 + amostra2
    ranks = {v: i + 1 for i, v in enumerate(sorted(todas_amostras))}
    soma_ranks1 = sum(ranks[v] for v in amostra1)
    soma_ranks2 = sum(ranks[v] for v in amostra2)
    
    U1 = soma_ranks1 - n1 * (n1 + 1) / 2
    U2 = soma_ranks2 - n2 * (n2 + 1) / 2
    
    U = min(U1, U2)
    mu_U = n1 * n2 / 2
    sigma_U = np.sqrt(n1 * n2 * (n1 + n2 + 1) / 12)
    Z = (U - mu_U) / sigma_U
    
    return U, Z

def normalizacao_zscore(data):
    media = np.mean(data)
    desvio_padrao = np.std(data)
    return [(x - media) / desvio_padrao for x in data]

def normalizacao_minmax(data):
    minimo = np.min(data)
    maximo = np.max(data)
    return [(x - minimo) / (maximo - minimo) for x in data]

if __name__ == '__main__':
    amostra1 = [20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
    amostra2 = [30, 31, 32, 33, 34, 35, 36, 37, 38, 39]

    print("Teste de Mann-Whitney U:")
    U, Z = mann_whitney_u_test(amostra1, amostra2)
    print(f'Estatística U: {U}, Valor Z: {Z}')

    dados = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    print("\nNormalização Z-score:")
    print(normalizacao_zscore(dados))

    print("\nNormalização Min-Max:")
    print(normalizacao_minmax(dados))
