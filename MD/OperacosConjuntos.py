def obter_conjunto(nome):
    return set(input(f"Digite os elementos do conjunto {nome} separados por espaço: ").split())

def substituir_variaveis(variaveis, expressao):
    for var in variaveis:
        expressao = expressao.replace(var, f"variaveis['{var}']")
    return expressao

def eval_expression(expressao, variaveis):
    try:
        return eval(expressao)
    except Exception as e:
        print(f"Erro ao avaliar a expressão: {e}")
        return None

def main():
    universo = obter_conjunto("Universo (Un)")
    conjunto_a = obter_conjunto("A")
    conjunto_b = obter_conjunto("B")
    conjuntos = {
        'A': conjunto_a,
        'B': conjunto_b,
        'U': universo
    }

    while True:
        confirma = input("Deseja adicionar mais algum conjunto? (digite nao caso nao queira): ")
        if confirma == "nao":
            break;
        nome_conjunto = input("Digite o nome para o conjunto (deve ser em maiúsculo e não pode colidir com outros: ")
        conjuntos.update({nome_conjunto + '': obter_conjunto(nome_conjunto)})
        
    while True:
        print("\nOperações possíveis: União (U), Interseção (A), Diferença (M ou -), Complementar (_), Diferença Simétrica (S)")
        expressao = input("Digite a expressão de conjuntos (ou 'sair' para encerrar ou 'alterar' para adicionar novos valores em um conjunto já existente): ")

        if expressao.lower() == 'sair':
            print("Encerrando o programa.")
            break

        if expressao == 'alterar':
            nome_conjunto = input("Digite o nome do conjunto pré-existente: ")
            if conjuntos.get(nome_conjunto):
                conjuntos.update({nome_conjunto + '': obter_conjunto(nome_conjunto)})
            else:
                print("Determinado conjunto é inexistente")
            continue
        # Substituir operadores de conjuntos por funções Python
        expressao = expressao.replace('u', '|')
        expressao = expressao.replace('a', '&')
        expressao = expressao.replace('m', '-')
        expressao = expressao.replace('d', '-')
        expressao = expressao.replace('s', '^')
        expressao = expressao.replace('_', 'U ^')

        # Substituir variáveis de conjuntos
        expressao = substituir_variaveis(conjuntos, expressao)

        # Avaliar a expressão de conjuntos
        resultado = eval_expression(expressao, conjuntos)

        if resultado is not None:
            if len(resultado) > 0:
                print(f"Resultado da expressão: {resultado}")
            else:
                print("Conjunto vazio")
        else:
            print("Expressão inválida.")

if __name__ == "__main__":
    main()
