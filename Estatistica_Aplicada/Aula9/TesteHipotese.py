import math


def ler_tabela_t():
    with open("TesteT.txt", "r") as file:
        linhas = file.readlines()
        
    niveis_confianca = list(map(float, linhas[1].split(';')[1:]))
    unicaudal_alfa = list(map(float, linhas[2].split(';')[1:]))
    bicaudal_alfa = list(map(float, linhas[3].split(';')[1:]))
    
    tabela_t = {}
    for linha in linhas[5:]:
        valores = list(map(float, linha.split(';')))
        gl = int(valores[0])
        tabela_t[gl] = valores[1:]
    
    return niveis_confianca, unicaudal_alfa, bicaudal_alfa, tabela_t

def valor_teste_t(nivel_confianca, tipo_cauda, gl):
    niveis_confianca, unicaudal_alfa, bicaudal_alfa, tabela_t = ler_tabela_t()
    
    if tipo_cauda == "Unicaudal":
        alfa = unicaudal_alfa[niveis_confianca.index(nivel_confianca)]
    elif tipo_cauda == "Bicaudal":
        alfa = bicaudal_alfa[niveis_confianca.index(nivel_confianca)]
    else:
        raise ValueError("Tipo de cauda não suportado.")
    
    if gl in tabela_t:
        return tabela_t[gl][niveis_confianca.index(nivel_confianca)]
    else:
        raise ValueError("Grau de liberdade não suportado.")

def valor_teste_z(alfa, cauda):
    valores_z = {
        0.10: {"Esquerda": -1.28, "Direita": 1.28, "Bicaudal": 1.645},
        0.05: {"Esquerda": -1.645, "Direita": 1.645, "Bicaudal": 1.96},
        0.01: {"Esquerda": -2.33, "Direita": 2.33, "Bicaudal": 2.575}
    }
    
    if alfa not in valores_z:
        raise ValueError("Nível de significância não suportado.")
    
    if cauda not in valores_z[alfa]:
        raise ValueError("Tipo de teste não suportado.")
    
    return valores_z[alfa][cauda]

def teste_hipotese(x, u, S, n, hipotese, alfa, tipo_teste, populacional=True):
    if populacional:
        # Teste Z
        Z = (x - u) / (S / math.sqrt(n))
        valor_critico = valor_teste_z(alfa, tipo_teste)
        print(f"Z calculado: {Z}, Valor crítico: {valor_critico}")
        
        if (tipo_teste == "Bicaudal" and abs(Z) > valor_critico) or \
           (tipo_teste == "Unicaudal esquerda" and Z < -valor_critico) or \
           (tipo_teste == "Unicaudal direita" and Z > valor_critico):
            print(f"No nível de significância de {alfa}, há evidências para discordar da hipótese nula.")
        else:
            print(f"No nível de significância de {alfa}, não há evidências para discordar da hipótese nula.")
    else:
        t = (x - u) / (S / math.sqrt(n))
        gl = n - 1
        valor_critico = valor_teste_t(alfa, tipo_teste, gl)
        print(f"t calculado: {t}, Valor crítico: {valor_critico}")
        
        if (tipo_teste == "Bicaudal" and abs(t) > valor_critico) or \
           (tipo_teste == "Unicaudal esquerda" and t < -valor_critico) or \
           (tipo_teste == "Unicaudal direita" and t > valor_critico):
            print(f"No nível de significância de {alfa}, há evidências para discordar da hipótese nula.")
        else:
            print(f"No nível de significância de {alfa}, não há evidências para discordar da hipótese nula.")

def teste_proporcao(P, p, q, n, alfa, tipo_teste):
    Z = (P - p) / math.sqrt(p * q / n)
    valor_critico = valor_teste_z(alfa, tipo_teste)
    print(f"Z calculado: {Z}, Valor crítico: {valor_critico}")
    
    if (tipo_teste == "Bicaudal" and abs(Z) > valor_critico) or \
       (tipo_teste == "Unicaudal esquerda" and Z < -valor_critico) or \
       (tipo_teste == "Unicaudal direita" and Z > valor_critico):
        print(f"No nível de significância de {alfa}, há evidências para discordar da hipótese nula.")
    else:
        print(f"No nível de significância de {alfa}, não há evidências para discordar da hipótese nula.")
