import math

def ler_tabela_correlacao():
    with open("ValidacaoCoeficienteCorrelacao.txt", "r") as file:
        linhas = file.readlines()

    # Lendo os cabeçalhos e os dados
    cabecalho = linhas[1].strip().split(';')
    tabela_correlacao = {}
    for linha in linhas[2:]:
        valores = linha.strip().split(';')
        n = int(valores[0])
        alfa_005 = float(valores[1].replace(',', '.'))
        alfa_001 = float(valores[2].replace(',', '.'))
        tabela_correlacao[n] = {'0.05': alfa_005, '0.01': alfa_001}

    return tabela_correlacao

def validar_correlacao(r, n, alfa):
    tabela_correlacao = ler_tabela_correlacao()
    
    if n in tabela_correlacao:
        valor_critico = tabela_correlacao[n][alfa]
        if r > valor_critico:
            return "Coeficiente significante"
        else:
            return "Coeficiente não significante"
    else:
        raise ValueError("Grau de liberdade (n) não suportado.")

def classificar_correlacao(r):
    if r == 1:
        return "Correlação positiva perfeita"
    elif 0.95 <= r < 1:
        return "Correlação positiva muito forte"
    elif 0.8 <= r < 0.95:
        return "Correlação positiva forte"
    elif 0.5 <= r < 0.8:
        return "Correlação positiva moderada"
    elif 0 <= r < 0.5:
        return "Correlação positiva fraca"
    elif -0.5 <= r < 0:
        return "Correlação negativa fraca"
    elif -0.8 <= r < -0.5:
        return "Correlação negativa moderada"
    elif -0.95 <= r < -0.8:
        return "Correlação negativa forte"
    elif -1 <= r < -0.95:
        return "Correlação negativa muito forte"
    elif r == -1:
        return "Correlação negativa perfeita"
    else:
        return "Valor de r inválido"

def coeficiente_pearson(nsxy, xs, ys, nxse2, xyse2, n, alfa, usar_tabela_txt=True):
    r = (nsxy - xs * ys) / (math.sqrt(nxse2 - xs**2) * math.sqrt(nyse2 - xyse2))
    cd = r**2
    print(f"Coeficiente de correlação: {r}")
    print(f"Coeficiente de determinação: {cd}")
    
    if usar_tabela_txt:
        resultado_validacao = validar_correlacao(r, n, alfa)
    else:
        resultado_validacao = classificar_correlacao(r)
    
    print(f"Resultado da validação: {resultado_validacao}")

def coeficiente_spearman(dise2, n, alfa, usar_tabela_txt=True):
    tR = 1 - 6 * dise2 / (n * (n**2 - 1))
    print(f"Coeficiente de Spearman: {tR}")

    if usar_tabela_txt:
        resultado_validacao = validar_correlacao(tR, n, alfa)
    else:
        resultado_validacao = classificar_correlacao(tR)
    
    print(f"Resultado da validação: {resultado_validacao}")

def regressao_linear(x, m, b):
    Y = m * x + b
    print(f"Y = {Y}")