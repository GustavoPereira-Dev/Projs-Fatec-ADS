def obter_conjunto(nome):
    return list(map(int, input("Digite os elementos do " + nome + " separados por espaço: ").split()))

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

    
def calcular_mediana_intervalo_classes(intervalos, frequencias):
    # Calcular a frequência acumulada
    frequencia_acumulada = [sum(frequencias[:i+1]) for i in range(len(frequencias))]
    
    # Número total de dados
    N = sum(frequencias)
    
    # Encontrar a classe mediana
    for i, fa in enumerate(frequencia_acumulada):
        if fa >= N / 2:
            classe_mediana = i
            break
    
    # Limite inferior da classe mediana
    L = intervalos[classe_mediana][0]
    
    # Frequência acumulada da classe anterior à classe mediana
    F = frequencia_acumulada[classe_mediana - 1] if classe_mediana > 0 else 0
    
    # Frequência da classe mediana
    f = frequencias[classe_mediana]
    
    # Amplitude do intervalo de classe
    h = intervalos[classe_mediana][1] - intervalos[classe_mediana][0]
    
    # Calcular a mediana
    mediana = L + ((N / 2 - F) / f) * h
    return mediana


def calcular_moda(conjunto):
    frequencias = {}
    for elemento in conjunto:
        frequencias[elemento] = frequencias.get(elemento, 0) + 1
    max_frequencia = max(frequencias.values())
    moda = [elemento for elemento, freq in frequencias.items() if freq == max_frequencia]
    return moda

def main():
    texto = """ Digite a opção
        1 - Calcular Média
        2 - Calcular Média Ponderada
        3 - Calcular Média com Frequência
        4 - Calcular Mediana
        5 - Calcular Mediana com Intervalo de Classes
        6 - Calcular Moda
        7 - Forma de distribuição possível para o conjunto (média, mediana e moda)
    """
    opc = int(input(texto))
    
    conjunto = obter_conjunto("conjunto")
    if opc == 2 or opc == 3 or opc == 5:
        conjunto2 = obter_conjunto("peso/frequencia \n")
    match opc:
        case 1:
            res = calcular_media(conjunto)
        case 2:
            res = calcular_media_ponderada(conjunto,conjunto2)
        case 3:
            res = calcular_media_com_frequencia(conjunto,conjunto2)
        case 4:
            res = calcular_mediana(conjunto)
        case 5:
            res = calcular_mediana_intervalo_classes(conjunto,conjunto2)
        case 6:
            res = calcular_moda(conjunto)
        case 7:
            media = calcular_media(conjunto)
            mediana = calcular_mediana(conjunto)
            moda = calcular_moda(conjunto)
            print("Média: " + media + "; Mediana: " + mediana + "; Moda: " + moda)
            if media == mediana and mediana == moda:
                print("Distribuição Simétrica")
            elif media == mediana and moda <= 1:
                print("Distribuição uniforme")
            elif media < mediana or media < moda:
                print("Distribuição assimétrica à esquerda (negativamente assimétrica)")
            elif media > mediana or media > moda:
                print("Distribuição assimétrica à direito (positivamente assimétrica)")
        case _:
            res = calcular_media(conjunto)

    if opc > 0 and opc < 7:
        print(res)

if __name__ == "__main__":
    main()


