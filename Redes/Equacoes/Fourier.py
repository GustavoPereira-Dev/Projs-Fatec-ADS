import numpy as np
import matplotlib.pyplot as plt

def square_wave(t, amplitude=1):
    """Define uma onda quadrada periódica."""
    return amplitude * np.sign(np.sin(t))

def fourier_series_square_wave(t, n_terms, amplitude=1):
    """Calcula a série de Fourier para uma onda quadrada."""
    fourier_sum = 0
    for n in range(1, n_terms * 2, 2):  # Apenas termos ímpares para onda quadrada
        fourier_sum += (4 * amplitude / (n * np.pi)) * np.sin(n * t)
    return fourier_sum

def run_fourier_analysis():
    print("\n--- Análise da Série de Fourier para Onda Quadrada ---")
    period = 2 * np.pi  # Período da onda
    t = np.linspace(-3 * period, 3 * period, 500) # Intervalo de tempo
    original_wave = square_wave(t)

    while True:
        print("\nEscolha o número de termos para a aproximação (ou 'sair' para encerrar):")
        print("1. 1 termo")
        print("2. 5 termos")
        print("3. 20 termos")
        print("4. Custom (digite um número)")
        print("5. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            n_terms = 1
        elif choice == '2':
            n_terms = 5
        elif choice == '3':
            n_terms = 20
        elif choice == '4':
            try:
                n_terms = int(input("Digite o número de termos desejado: "))
                if n_terms <= 0:
                    raise ValueError("O número de termos deve ser positivo.")
            except ValueError:
                print("Entrada inválida. Por favor, digite um número inteiro positivo.")
                continue
        elif choice == '5' or choice.lower() == 'sair':
            print("Saindo da análise de Fourier. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha um número entre 1 e 5.")
            continue

        fourier_approx = fourier_series_square_wave(t, n_terms)

        plt.figure(figsize=(10, 6))
        plt.plot(t, original_wave, label='Onda Quadrada Original', color='blue')
        plt.plot(t, fourier_approx, label=f'Série de Fourier ({n_terms} termos)', linestyle='--', color='red')
        plt.title(f'Aproximação da Série de Fourier para Onda Quadrada ({n_terms} termos)')
        plt.xlabel('Tempo')
        plt.ylabel('Amplitude')
        plt.grid(True)
        plt.legend()
        plt.tight_layout()
        plt.show()

# Chama a função do menu interativo
run_fourier_analysis()

print("\n### Análise da Série de Fourier para Onda Quadrada ###")
print("O código acima demonstra a aproximação de uma onda quadrada por sua série de Fourier, utilizando diferentes números de termos harmônicos. Os gráficos ilustram os seguintes pontos:")
print("""1. **Convergência da Série:**\n   - Com apenas 1 termo, a aproximação é uma senoide simples, capturando apenas a frequência fundamental da onda quadrada.\n   - À medida que o número de termos aumenta para 5 e depois para 20, a forma de onda aproximada começa a se assemelhar cada vez mais à onda quadrada original. Isso ocorre porque termos de frequência mais alta são adicionados, que são essenciais para construir as "arestas" íngremes da onda quadrada.""")
print("""2. **Fenômeno de Gibbs:**\n   - Em todas as aproximações, mas mais visivelmente nos gráficos com mais termos (5 e 20), pode-se observar o "Fenômeno de Gibbs". Este é o overshoot e undershoot nos pontos de descontinuidade da onda quadrada (as transições abruptas entre -1 e 1).\n   - Embora a aproximação melhore com o aumento do número de termos, essa oscilação nos picos não desaparece completamente; ela se estreita, mas mantém uma amplitude quase constante, sendo uma característica inerente à aproximação de Fourier de funções com descontinuidades.""")
print("Em resumo, os gráficos mostram como a série de Fourier constrói funções complexas a partir de senoides e cossenos simples, e também destacam uma de suas limitações intrínsecas ao lidar com descontinuidades abruptas.")