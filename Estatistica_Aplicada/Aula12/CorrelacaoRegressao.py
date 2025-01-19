import math

def coeficiente_pearson(nsxy, xs, ys, nxse2, xyse2):
    r = (nsxy - xs * ys) / (math.sqrt(nxse2 - xs**2) * math.sqrt(nyse2 - xyse2))
    cd = r**2
    print(f"Coeficiente de correlação: {r}")
    print(f"Coeficiente de determinação: {cd}")

def coeficiente_spearman(dise2, n):
    tR = 1 - 6 * dise2 / (n * (n**2 - 1))
    print(f"Coeficiente de Spearman: {tR}")

def regressao_linear(x, m, b):
    Y = m * x + b
    print(f"Y = {Y}")