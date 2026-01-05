import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import linprog
from itertools import combinations

def resolver_pl_completo():
    print("--- Solver de PL: Analítico, Intersecções e Gráfico ---")
    
    # 1. Entrada de Dados
    c1 = float(input("Coeficiente de x1 na função objetivo: "))
    c2 = float(input("Coeficiente de x2 na função objetivo: "))
    tipo = input("Maximizar (max) ou Minimizar (min)? ").lower()
    
    c_solver = [c1, c2] if tipo == 'min' else [-c1, -c2]

    n_rest = int(input("Quantas restrições (<=)? "))
    A, b = [], []
    for i in range(n_rest):
        r1 = float(input(f"Restrição {i+1} - Coeficiente de x1: "))
        r2 = float(input(f"Restrição {i+1} - Coeficiente de x2: "))
        lim = float(input(f"Restrição {i+1} - Valor limite (b): "))
        A.append([r1, r2])
        b.append(lim)

    A_matriz = np.array(A)
    b_vetor = np.array(b)

    # 2. Cálculo das Intersecções (Mesclagens)
    print("\n--- Analisando Cruzamentos e Vértices ---")
    # Incluímos as restrições de não-negatividade (x1>=0, x2>=0) como retas
    # Representadas por: -1x1 + 0x2 <= 0 e 0x1 - 1x2 <= 0
    A_comp = np.vstack([A_matriz, [[-1, 0], [0, -1]]])
    b_comp = np.append(b_vetor, [0, 0])
    
    vertices_viaveis = []
    
    # Combina todas as retas 2 a 2 para achar os cruzamentos
    for i, j in combinations(range(len(A_comp)), 2):
        M = np.array([A_comp[i], A_comp[j]])
        V = np.array([b_comp[i], b_comp[j]])
        
        try:
            ponto = np.linalg.solve(M, V)
            # Verifica se o ponto respeita todas as outras restrições
            if all(np.dot(A_comp, ponto) <= b_comp + 1e-9):
                # Evita duplicatas
                if not any(np.allclose(ponto, p) for p in vertices_viaveis):
                    vertices_viaveis.append(ponto)
                    print(f"Vértice Viável encontrado: x1={ponto[0]:.2f}, x2={ponto[1]:.2f}")
        except np.linalg.LinAlgError:
            continue # Retas paralelas

    # 3. Solver Analítico (Resultado Final)
    res = linprog(c_solver, A_ub=A_matriz, b_ub=b_vetor, bounds=(0, None), method='highs')

    # 4. Construção do Gráfico
    plt.figure(figsize=(10, 7))
    max_val = np.max(b_vetor) * 1.5 if len(b_vetor) > 0 else 10
    x_plot = np.linspace(0, max_val, 400)

    for i in range(n_rest):
        if A_matriz[i][1] != 0:
            y_rest = (b_vetor[i] - A_matriz[i][0] * x_plot) / A_matriz[i][1]
            plt.plot(x_plot, y_rest, label=f'Restrição {i+1}')
        else:
            plt.axvline(x=b_vetor[i]/A_matriz[i][0], label=f'Restrição {i+1} (Vertical)', color='orange')

    # Marcar todos os vértices viáveis
    for p in vertices_viaveis:
        plt.plot(p[0], p[1], 'ko', alpha=0.5)
        plt.annotate(f"({p[0]:.1f}, {p[1]:.1f})", (p[0], p[1]), textcoords="offset points", xytext=(5,5), fontsize=8)

    if res.success:
        val_z = res.fun if tipo == 'min' else -res.fun
        plt.plot(res.x[0], res.x[1], 'ro', markersize=12, label=f'ÓTIMO Z={val_z:.2f}')
        print(f"\n--- CONCLUSÃO ---")
        print(f"Melhor solução encontrada: x1={res.x[0]:.2f}, x2={res.x[1]:.2f}")
        print(f"Valor Ótimo de Z: {val_z:.2f}")

    plt.xlim(0, max_val)
    plt.ylim(0, max_val)
    plt.axhline(0, color='black', lw=2)
    plt.axvline(0, color='black', lw=2)
    plt.legend()
    plt.grid(True, linestyle=':', alpha=0.6)
    plt.title(f"Método Gráfico: {tipo.capitalize()}imização")
    plt.show()

if __name__ == "__main__":
    resolver_pl_completo()