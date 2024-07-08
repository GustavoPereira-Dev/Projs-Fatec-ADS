import itertools

def isLower(char):
    return char.islower()

def substituir_variaveis(variables, expression):
    for var in variables:
        expression = expression.replace(var, str(variables[var]))
    return expression

def eval_expression(expression):
    # Substituir operadores lógicos por operadores Python
    expression = expression.replace('~', ' not ')
    expression = expression.replace('^', ' != ')
    expression = expression.replace('V', ' or ')
    expression = expression.replace('A', ' and ')
    expression = expression.replace('<->', ' == ')
    expression = expression.replace('->', ' <= ')

    # Avaliar a expressão lógica
    try:
        return eval(expression)
    except Exception as e:
        print(f"Erro ao avaliar a expressão: {e}")
        return None

def gerar_tabela_verdade(condicional):
    # Extrair variáveis únicas
    variaveis = sorted(set(filter(isLower, condicional)))

    # Gerar todas as combinações possíveis de valores booleanos para as variáveis
    combinacoes = list(itertools.product([False, True], repeat=len(variaveis)))

    # Exibir a tabela verdade
    print("\nTabela Verdade")
    print(" | ".join(variaveis) + " | " + condicional)
    print("-" * (10 * len(variaveis) + len(condicional) + 3))

    for combinacao in combinacoes:
        valores = dict(zip(variaveis, combinacao))
        expressao_substituida = substituir_variaveis(valores, condicional)
        resultado = eval_expression(expressao_substituida)
        
        if resultado is not None:
            linha = " | ".join(str(int(valores[var])) for var in variaveis) + " | " + str(int(resultado))
        else:
            linha = " | ".join(str(int(valores[var])) for var in variaveis) + " | ERRO"
        print(linha)

def main():
    print("A = and; ~ = not; ^ = xor; V = or; -> = implicação; <-> = bicondicional")

    while True:
        condicional = input("\nDigite a condicional para a tabela verdade (ou 'sair' para encerrar): ")
        if condicional.lower() == 'sair':
            print("Programa encerrado.")
            break
        gerar_tabela_verdade(condicional)

if __name__ == "__main__":
    main()

