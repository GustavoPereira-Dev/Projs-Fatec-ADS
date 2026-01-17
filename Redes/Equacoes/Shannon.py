import math

def shannon_hartley_capacity(bandwidth, snr_linear):
    """
    Calcula a capacidade máxima de um canal (Shannon Capacity) de acordo
    com o Teorema de Shannon-Hartley.

    A Capacidade de Shannon (C) é a taxa máxima teórica de informação
    que pode ser transmitida através de um canal de comunicação
    com uma determinada largura de banda (B) e relação sinal-ruído (SNR),
    sem que haja erros. É dada por C = B * log2(1 + SNR).

    Args:
        bandwidth (float): A largura de banda do canal em Hertz (Hz).
        snr_linear (float): A relação sinal-ruído (SNR) em formato linear (não em dB).
                            Para converter de dB para linear: SNR_linear = 10^(SNR_dB / 10).

    Returns:
        float: A capacidade máxima do canal em bits por segundo (bps).
    Raises:
        ValueError: Se a largura de banda ou a SNR linear forem negativas.
    """
    if bandwidth < 0:
        raise ValueError("A largura de banda não pode ser negativa.")
    if snr_linear < 0:
        raise ValueError("A relação sinal-ruído (SNR) linear não pode ser negativa.")
    
    # A capacidade de Shannon é calculada por C = B * log2(1 + SNR)
    capacity = bandwidth * math.log2(1 + snr_linear)
    return capacity

# --- Exemplos de Uso do Teorema de Shannon-Hartley ---
print("### Teorema de Shannon-Hartley (Capacidade do Canal) ###")

# Exemplo 1: Canal telefônico
# Largura de banda típica: 3100 Hz
# SNR típica: 30 dB
# Convertendo SNR de dB para linear: 10^(30/10) = 1000
bandwidth_1 = 3100 # Hz
snr_db_1 = 30      # dB
snr_linear_1 = 10**(snr_db_1 / 10)
try:
    capacity_1 = shannon_hartley_capacity(bandwidth_1, snr_linear_1)
    print(f"\nPara um canal com Largura de Banda de {bandwidth_1} Hz e SNR de {snr_db_1} dB ({snr_linear_1:.0f} linear):")
    print(f"A capacidade máxima teórica é de {capacity_1:.2f} bps ({capacity_1 / 1000:.2f} kbps).")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 2: Canal de satélite
# Largura de banda: 1 MHz
# SNR: 20 dB
bandwidth_2 = 1 * 10**6 # Hz (1 MHz)
snr_db_2 = 20           # dB
snr_linear_2 = 10**(snr_db_2 / 10)
try:
    capacity_2 = shannon_hartley_capacity(bandwidth_2, snr_linear_2)
    print(f"\nPara um canal com Largura de Banda de {bandwidth_2/10**6:.0f} MHz e SNR de {snr_db_2} dB ({snr_linear_2:.0f} linear):")
    print(f"A capacidade máxima teórica é de {capacity_2:.2f} bps ({capacity_2 / 10**6:.2f} Mbps).")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 3: Tentativa com valores negativos
try:
    shannon_hartley_capacity(-100, 100)
except ValueError as e:
    print(f"\nErro esperado ao passar largura de banda negativa: {e}")
try:
    shannon_hartley_capacity(1000, -50)
except ValueError as e:
    print(f"Erro esperado ao passar SNR linear negativa: {e}")

print("""
**Explicação do Teorema de Shannon-Hartley:**
O Teorema de Shannon-Hartley, desenvolvido por Claude Shannon, é um dos pilares da teoria da informação.
Ele estabelece o limite superior teórico da taxa de dados que pode ser transmitida de forma confiável
(com uma taxa de erro arbitrariamente pequena) por um canal de comunicação de banda limitada,
sujeito a ruído gaussiano aditivo (AWGN).

A fórmula é: C = B * log2(1 + SNR)
Onde:
- C é a Capacidade do Canal em bits por segundo (bps).
- B é a Largura de Banda do canal em Hertz (Hz).
- SNR é a Relação Sinal-Ruído linear (não em decibéis).

Este teorema é crucial porque define o máximo que se pode alcançar em termos de velocidade
de comunicação sob condições ideais para um dado canal, independentemente da tecnologia
de modulação ou codificação utilizada. Ele mostra que a largura de banda e a qualidade do sinal
(medida pela SNR) são os fatores fundamentais que limitam o desempenho da comunicação.
""")