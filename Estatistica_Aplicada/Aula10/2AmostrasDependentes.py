import math

def teste_duas_amostras_dependentes(x1, x2, ds, n):
    d = x1 - x2
    D = ds / n 
    Sd = math.sqrt((ds**2 - (ds**2 / n)) / (n - 1))
    t = (ds - 0) / (Sd / math.sqrt(n))

    gl = n - 1
    print(f"t: {t}, gl: {gl}")