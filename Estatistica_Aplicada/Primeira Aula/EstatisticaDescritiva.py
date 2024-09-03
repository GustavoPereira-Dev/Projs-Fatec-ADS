# Classes e distâncias entre classes

#1|-5 (x>=1 && x <5)

classe = input("Digite o modelo da classe (1 para |-| ou 2 para |-): ")
tipo_classe = ["|-|","|-"]
classe = tipo_classe[int(classe) - 1]
distancia = input("Digite a distância entre cada classe (Ex: 1 5): ").split(" ")
quant_classe = int(input("Digite a quantidade de classes: "))
distancia_classe = int(distancia[1]) - int(distancia[0])
frequencias = []

total_frequencias = 0
for i in range(0,quant_classe,1):
    frequencia = int(input("Digite a frequência da classe "))
    frequencias.append(frequencia)
    total_frequencias += frequencias[i]


largura_classe = int(distancia[0])
acumulador_frequencias = 0
ponto_medio = int(distancia[1]) / int(distancia[0])
print("-------------------------------------------")
for i in range(0,quant_classe,1):
      acumulador_frequencias += frequencias[i]
      print(f"| {largura_classe}{classe}{(largura_classe + distancia_classe)} | {frequencias[i]} | {(frequencias[i] / total_frequencias):5.3f} | {acumulador_frequencias} | {largura_classe + ponto_medio} |")
      largura_classe += distancia_classe
      

print("-------------------------------------------")
      
