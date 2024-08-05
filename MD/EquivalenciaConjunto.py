def obter_conjunto(nome):
    return set(input(f"Digite os elementos do conjunto {nome} separados por espaço: ").split())

def obter_relacao(conjunto):
    relacao = set()
    while True:
        par = input("Digite um par da relação no formato 'a b' (ou 'fim' para terminar): ")
        if par.lower() == 'fim':
            break
        a, b = par.split()
        relacao.add((a, b))
    return relacao

def verificar_reflexiva(conjunto, relacao):
    for elemento in conjunto:
        if (elemento, elemento) not in relacao:
            return False
    return True

def verificar_simetrica(relacao):
    for a, b in relacao:
        if (b, a) not in relacao:
            return False
    return True

def verificar_transitiva(relacao):
    for a, b in relacao:
        for c, d in relacao:
            if b == c and (a, d) not in relacao:
                return False
    return True

def main():
    conjunto = obter_conjunto("Conjunto")
    relacao = obter_relacao(conjunto)

    reflexiva = verificar_reflexiva(conjunto, relacao)
    simetrica = verificar_simetrica(relacao)
    transitiva = verificar_transitiva(relacao)

    print("\nA relação é:")
    print(f"Reflexiva: {'Sim' if reflexiva else 'Não'}")
    print(f"Simétrica: {'Sim' if simetrica else 'Não'}")
    print(f"Transitiva: {'Sim' if transitiva else 'Não'}")

if __name__ == "__main__":
    main()
