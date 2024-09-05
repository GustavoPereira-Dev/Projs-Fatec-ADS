import math

def binomial(n, k, p):
    comb = math.comb(n, k)
    return comb * (p ** k) * ((1 - p) ** (n - k))

def poisson(lambd, k):
    return (lambd ** k * math.exp(-lambd)) / math.factorial(k)

def geometrica(p, k):
    return (1 - p) ** (k - 1) * p

def continua(x, mu, sigma):
    return (1 / (sigma * math.sqrt(2 * math.pi))) * math.exp(-0.5 * ((x - mu) / sigma) ** 2)


# Distribuição Binomial
n = 10  # número de experimentos
k = 5   # número de sucessos
p = 0.5 # probabilidade de sucesso
print("Binomial:", binomial(n, k, p))

# Distribuição de Poisson
lambd = 3  # média de ocorrências por intervalo
k = 2      # número de ocorrências
print("Poisson:", poisson(lambd, k))

# Distribuição Geométrica
p = 0.5  # probabilidade de sucesso
k = 3    # número de tentativas até o primeiro sucesso
print("Geométrica:", geometrica(p, k))

# Distribuição Normal
x = 1.0   # valor da variável aleatória
mu = 0.0  # média
sigma = 1.0 # desvio padrão
print("Normal:", continua(x, mu, sigma))
