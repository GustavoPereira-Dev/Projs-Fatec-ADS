def nyquist_sampling_rate(max_frequency):
    """
    Calcula a taxa de amostragem mínima (frequência de Nyquist) necessária
    para um sinal, de acordo com o Teorema de Nyquist.

    O Teorema de Nyquist afirma que, para reconstruir um sinal analógico
    completamente a partir de suas amostras digitais, a taxa de amostragem
    deve ser no mínimo o dobro da frequência mais alta presente no sinal.

    Args:
        max_frequency (float): A frequência mais alta (em Hz) presente no sinal.

    Returns:
        float: A taxa de amostragem mínima necessária (em Hz).
    Raises:
        ValueError: Se a frequência máxima for negativa.
    """
    if max_frequency < 0:
        raise ValueError("A frequência máxima não pode ser negativa.")
    return 2 * max_frequency

# --- Exemplos de Uso do Teorema de Nyquist ---
print("### Teorema de Nyquist ###")

# Exemplo 1: Sinal com frequência máxima de 100 Hz
max_freq_1 = 100 # Hz
nyquist_rate_1 = nyquist_sampling_rate(max_freq_1)
print(f"Para um sinal com frequência máxima de {max_freq_1} Hz, a taxa de amostragem mínima (Nyquist) é de {nyquist_rate_1} Hz.")

# Exemplo 2: Sinal de áudio com frequência máxima de 20 kHz (faixa audível humana)
max_freq_2 = 20000 # Hz (20 kHz)
nyquist_rate_2 = nyquist_sampling_rate(max_freq_2)
print(f"Para um sinal de áudio com frequência máxima de {max_freq_2/1000} kHz, a taxa de amostragem mínima (Nyquist) é de {nyquist_rate_2/1000} kHz.")

# Exemplo 3: Sinal de vídeo com frequência máxima de 5 MHz
max_freq_3 = 5 * 10**6 # Hz (5 MHz)
nyquist_rate_3 = nyquist_sampling_rate(max_freq_3)
print(f"Para um sinal de vídeo com frequência máxima de {max_freq_3/10**6} MHz, a taxa de amostragem mínima (Nyquist) é de {nyquist_rate_3/10**6} MHz.")

# Exemplo 4: Tentativa com frequência negativa
try:
    nyquist_sampling_rate(-50)
except ValueError as e:
    print(f"Erro esperado ao passar uma frequência negativa: {e}")

print("""
**Explicação do Teorema de Nyquist:**
O Teorema de Nyquist, também conhecido como teorema de amostragem de Nyquist-Shannon, é um princípio fundamental na teoria da informação e processamento de sinais.
Ele estabelece que, para que um sinal analógico seja perfeitamente reconstruído a partir de uma sequência discreta de amostras, a taxa de amostragem deve ser de pelo menos duas vezes a frequência máxima do sinal original.
Se a taxa de amostragem for menor que o dobro da frequência máxima, ocorrerá o fenômeno de aliasing, onde diferentes frequências se tornam indistinguíveis após a amostragem, levando à perda irreversível de informação.
""")