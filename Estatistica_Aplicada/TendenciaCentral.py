def obter_conjunto():
    return list(map(int, input("Digite os elementos do conjunto separados por espaço: ").split()))

def calcular_media(conjunto):
    return sum(conjunto) / len(conjunto)

def calcular_media_ponderada(conjunto, pesos):
    soma = 0
    total_pesos = sum(pesos)
    for valor, peso in zip(conjunto, pesos):
        soma += valor * peso
    return soma / total_pesos

def calcular_media_com_frequencia(conjunto, frequencias):
    soma = 0
    total_frequencias = sum(frequencias)
    for valor, frequencia in zip(conjunto, frequencias):
        soma += valor * frequencia
    return soma / total_frequencias

def calcular_mediana(conjunto):
    sorted_conjunto = sorted(conjunto)
    n = len(sorted_conjunto)
    if n % 2 == 1:
        return sorted_conjunto[n // 2]
    else:
        middle1 = sorted_conjunto[n // 2 - 1]
        middle2 = sorted_conjunto[n // 2]
        return (middle1 + middle2) / 2

def calcular_moda(conjunto):
    frequencias = {}
    for elemento in conjunto:
        frequencias[elemento] = frequencias.get(elemento, 0) + 1
    max_frequencia = max(frequencias.values())
    moda = [elemento for elemento, freq in frequencias.items() if freq == max_frequencia]
    return moda


