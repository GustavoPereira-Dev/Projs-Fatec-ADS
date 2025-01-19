import math

def intervalo_confianca(c, n, o, x):
    if c == 90:
        Zc = 1.645
    elif c == 95:
        Zc = 1.96
    elif c == 99:
        Zc = 2.575
    else:
        raise ValueError("Nível de confiança não suportado.")

    E = Zc * (o / math.sqrt(n))
    print(f"{x - E} < u < {x + E}")

def amostragem_minima(c, o, E):
    if c == 90:
        Zc = 1.645
    elif c == 95:
        Zc = 1.96
    elif c == 99:
        Zc = 2.575
    else:
        raise ValueError("Nível de confiança não suportado.")

    N = (Zc * o / E) ** 2
    print(f"Tamanho mínimo da amostra: {N}")