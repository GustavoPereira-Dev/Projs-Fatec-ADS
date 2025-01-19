import math

def teste_hipotese(x, u, S, n, hipotese):
    t = (x - u) / (S / math.sqrt(n))
    gl = n - 1

    if "=" in hipotese:
        print("Hipótese nula")
    else:
        print("Hipótese alternativa")

    if "!=" in hipotese:
        print("Bicaudal")
    elif "<" in hipotese:
        print("Unicaudal esquerda")
    elif ">" in hipotese:
        print("Unicaudal direita")
    else:
        raise ValueError("Símbolo de hipótese inválido.")

    a = 1 - (c / 100)
    print(f"t: {t}, gl: {gl}, nível de significância: {a}")

def teste_proporcao(P, p, q, n):
    Z = (P - p) / math.sqrt(p * q / n)
    print(f"Z: {Z}")