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

# Parâmetros da simulação
period = 2 * np.pi  # Período da onda
t = np.linspace(-3 * period, 3 * period, 500) # Intervalo de tempo

# Gerar a onda quadrada original
original_wave = square_wave(t)

# Gerar aproximações da série de Fourier com diferentes números de termos
n_terms_1 = 1  # 1 termo
fourier_approx_1 = fourier_series_square_wave(t, n_terms_1)

n_terms_2 = 5  # 5 termos
fourier_approx_2 = fourier_series_square_wave(t, n_terms_2)

n_terms_3 = 20 # 20 termos
fourier_approx_3 = fourier_series_square_wave(t, n_terms_3)

# Plotar os resultados
plt.figure(figsize=(14, 8))

plt.subplot(3, 1, 1)
plt.plot(t, original_wave, label='Onda Quadrada Original', color='blue')
plt.plot(t, fourier_approx_1, label=f'Série de Fourier ({n_terms_1} termos)', linestyle='--', color='red')
plt.title(f'Aproximação da Série de Fourier para Onda Quadrada ({n_terms_1} termo)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.legend()

plt.subplot(3, 1, 2)
plt.plot(t, original_wave, label='Onda Quadrada Original', color='blue')
plt.plot(t, fourier_approx_2, label=f'Série de Fourier ({n_terms_2} termos)', linestyle='--', color='green')
plt.title(f'Aproximação da Série de Fourier para Onda Quadrada ({n_terms_2} termos)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.legend()

plt.subplot(3, 1, 3)
plt.plot(t, original_wave, label='Onda Quadrada Original', color='blue')
plt.plot(t, fourier_approx_3, label=f'Série de Fourier ({n_terms_3} termos)', linestyle='--', color='purple')
plt.title(f'Aproximação da Série de Fourier para Onda Quadrada ({n_terms_3} termos)')
plt.xlabel('Tempo')
plt.ylabel('Amplitude')
plt.grid(True)
plt.legend()

plt.tight_layout()
plt.show()

print("### Análise da Série de Fourier para Onda Quadrada ###")
print("O código acima demonstra a aproximação de uma onda quadrada por sua série de Fourier, utilizando diferentes números de termos harmônicos. Os gráficos ilustram os seguintes pontos:")
print("""1. **Convergência da Série:**
   - Com apenas 1 termo, a aproximação é uma senoide simples, capturando apenas a frequência fundamental da onda quadrada.
   - À medida que o número de termos aumenta para 5 e depois para 20, a forma de onda aproximada começa a se assemelhar cada vez mais à onda quadrada original. Isso ocorre porque termos de frequência mais alta são adicionados, que são essenciais para construir as "arestas" íngremes da onda quadrada.""")
print("""2. **Fenômeno de Gibbs:**
   - Em todas as aproximações, mas mais visivelmente nos gráficos com mais termos (5 e 20), pode-se observar o "Fenômeno de Gibbs". Este é o overshoot e undershoot nos pontos de descontinuidade da onda quadrada (as transições abruptas entre -1 e 1).
   - Embora a aproximação melhore com o aumento do número de termos, essa oscilação nos picos não desaparece completamente; ela se estreita, mas mantém uma amplitude quase constante, sendo uma característica inerente à aproximação de Fourier de funções com descontinuidades.""")
print("Em resumo, os gráficos mostram como a série de Fourier constrói funções complexas a partir de senoides e cossenos simples, e também destacam uma de suas limitações intrínsecas ao lidar com descontinuidades abruptas.")