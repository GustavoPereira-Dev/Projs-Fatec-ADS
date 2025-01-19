import math

def teste_duas_amostras_independentes(x1, x2, o1, o2, n1, n2):
    ox1x2 = math.sqrt(o1**2 / n1 + o2**2 / n2)
    z = (x1 - x2) / ox1x2

    print(f"z: {z}")
    
    gl = n1 + n2 - 2
    print(f"Graus de liberdade (variância igual): {gl}")
    print(f"Graus de liberdade (variância diferente): {n1 - 1} < gl < {n2 - 1}")