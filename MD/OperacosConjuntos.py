def obter_conjunto(nome):
    return set(input(f"Digite os elementos do conjunto {nome} separados por espaço: ").split())

def complementar(conjunto, universo):
    return universo.difference(conjunto)

def calcular_operacao(conjunto1, conjunto2, operacao, universo):
    if operacao == 'U':
        return conjunto1.union(conjunto2)
    elif operacao == 'A':
        return conjunto1.intersection(conjunto2)
    elif operacao in ['M', '-']:
        return conjunto1.difference(conjunto2)
    elif operacao == '_':
        return complementar(conjunto1, universo)
    elif operacao == 'S':
        return conjunto1.symmetric_difference(conjunto2)
    else:
        return None

def main():
    universo = obter_conjunto("Universo (Un)")
    conjunto_a = obter_conjunto("A")
    conjunto_b = obter_conjunto("B")

    while True:
        print("\nOperações possíveis: União (U), Interseção (A), Diferença (M ou -), Complementar (_), Diferença Simétrica (S)")
        operacao = input("Digite a operação desejada (ou 'sair' para encerrar): ")

        if operacao.lower() == 'sair':
            print("Encerrando o programa.")
            break

        if operacao == '_':
            conjunto = input("Digite o conjunto para complementar (A ou B): ")
            if conjunto == 'A':
                resultado = complementar(conjunto_a, universo)
            elif conjunto == 'B':
                resultado = complementar(conjunto_b, universo)
            else:
                print("Conjunto inválido.")
                continue
        else:
            conjunto1 = input("Digite o primeiro conjunto (A ou B): ")
            conjunto2 = input("Digite o segundo conjunto (A ou B): ")

            if conjunto1 == 'A':
                conjunto1 = conjunto_a
            elif conjunto1 == 'B':
                conjunto1 = conjunto_b
            else:
                print("Conjunto inválido.")
                continue

            if conjunto2 == 'A':
                conjunto2 = conjunto_a
            elif conjunto2 == 'B':
                conjunto2 = conjunto_b
            else:
                print("Conjunto inválido.")
                continue

            resultado = calcular_operacao(conjunto1, conjunto2, operacao, universo)

        if resultado is not None:
            print(f"Resultado da operação: {resultado}")
        else:
            print("Operação inválida.")

if __name__ == "__main__":
    main()
