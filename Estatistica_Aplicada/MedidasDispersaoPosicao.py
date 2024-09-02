import math

def obter_conjunto():
    return list(map(int, input("Digite os elementos do conjunto separados por espaço: ").split()))

def calcular_amplitude(conjunto):
    return max(conjunto) - min(conjunto)

def calcular_media(conjunto):
    return sum(conjunto) / len(conjunto)

def calcular_desvio(conjunto):
    media = calcular_media(conjunto)
    return [x - media for x in conjunto]

def calcular_desvio_absoluto(conjunto):
    media = calcular_media(conjunto)
    return sum(abs(x - media) for x in conjunto) / len(conjunto)

def calcular_variancia_populacional(conjunto):
    media = calcular_media(conjunto)
    return sum((x - media) ** 2 for x in conjunto) / len(conjunto)

def calcular_desvio_padrao(conjunto):
    return math.sqrt(calcular_variancia_populacional(conjunto))

def calcular_variancia_amostral(conjunto):
    media = calcular_media(conjunto)
    return sum((x - media) ** 2 for x in conjunto) / (len(conjunto) - 1)

def calcular_desvio_amostral(conjunto):
    return math.sqrt(calcular_variancia_amostral(conjunto))

def calcular_fractis(conjunto, fractil):
    sorted_conjunto = sorted(conjunto)
    k = (len(sorted_conjunto) - 1) * fractil
    f = math.floor(k)
    c = math.ceil(k)
    if f == c:
        return sorted_conjunto[int(k)]
    d0 = sorted_conjunto[int(f)] * (c - k)
    d1 = sorted_conjunto[int(c)] * (k - f)
    return d0 + d1

def calcular_quartis(conjunto):
    return [calcular_fractis(conjunto, 0.25), calcular_fractis(conjunto, 0.5), calcular_fractis(conjunto, 0.75)]

def identificar_outliers(conjunto):
    Q1, Q3 = calcular_quartis(conjunto)[0], calcular_quartis(conjunto)[2]
    IQR = Q3 - Q1
    lower_bound = Q1 - 1.5 * IQR
    upper_bound = Q3 + 1.5 * IQR
    outliers = [x for x in conjunto if x < lower_bound or x > upper_bound]
    return outliers


def main():
    meu_conjunto = {10, 15, 20, 25, 30, 35, 40, 45, 50}
    # meu_conjunto = obter_conjunto()
    print("Amplitude:", calcular_amplitude(meu_conjunto))
    print("Desvio:", calcular_desvio(meu_conjunto))
    print("Desvio Absoluto:", calcular_desvio_absoluto(meu_conjunto))
    print("Variância Populacional:", calcular_variancia_populacional(meu_conjunto))
    print("Desvio Padrão:", calcular_desvio_padrao(meu_conjunto))
    print("Variância Amostral:", calcular_variancia_amostral(meu_conjunto))
    print("Desvio Amostral:", calcular_desvio_amostral(meu_conjunto))
    print("Fractis (0.25):", calcular_fractis(meu_conjunto, 0.25))
    print("Quartis:", calcular_quartis(meu_conjunto))
    print("Outliers:", identificar_outliers(meu_conjunto))

if __name__ == "__main__":
    main()
