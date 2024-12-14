def probabilidade_ocorrer_evento(casos_favoraveis, casos_totais):
    return casos_favoraveis / casos_totais

def probabilidade_nao_ocorrer_evento(probabilidade_evento):
    return 1 - probabilidade_evento

def probabilidade_condicional(probabilidade_interseccao, probabilidade_b):
    return probabilidade_interseccao / probabilidade_b

def probabilidade_interseccao(probabilidade_a, probabilidade_condicional_b_dado_a):
    return probabilidade_a * probabilidade_condicional_b_dado_a

def probabilidade_uniao(probabilidade_a, probabilidade_b, probabilidade_interseccao):
    return probabilidade_a + probabilidade_b - probabilidade_interseccao

def teorema_de_bayes(probabilidade_b_dado_a, probabilidade_a, probabilidade_b):
    return (probabilidade_b_dado_a * probabilidade_a) / probabilidade_b

def lei_da_probabilidade_total(probabilidades_condicionais, probabilidades_a):
    return sum(p_cond * p_a for p_cond, p_a in zip(probabilidades_condicionais, probabilidades_a))


def main():
    casos_favoraveis = 3
    casos_totais = 10
    prob_evento = probabilidade_ocorrer_evento(casos_favoraveis, casos_totais)
    print("Probabilidade de ocorrer o evento:", prob_evento)

    prob_nao_evento = probabilidade_nao_ocorrer_evento(prob_evento)
    print("Probabilidade de não ocorrer o evento:", prob_nao_evento)

    prob_interseccao = probabilidade_interseccao(0.5, 0.2)
    print("Probabilidade de interseção de eventos:", prob_interseccao)

    prob_uniao = probabilidade_uniao(0.5, 0.4, prob_interseccao)
    print("Probabilidade de união de eventos:", prob_uniao)

    prob_condicional = probabilidade_condicional(prob_interseccao, 0.4)
    print("Probabilidade condicional:", prob_condicional)

    prob_bayes = teorema_de_bayes(0.8, 0.5, 0.4)
    print("Teorema de Bayes:", prob_bayes)

    prob_total = lei_da_probabilidade_total([0.2, 0.3], [0.5, 0.5])
    print("Lei da Probabilidade Total:", prob_total)


if __name__ == "__main__":
    main()
