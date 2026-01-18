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

def run_shannon_hartley_tool():
    print("\n--- Ferramenta do Teorema de Shannon-Hartley ---")
    while True:
        print("\nEscolha uma opção:")
        print("1. Calcular Capacidade de Shannon")
        print("2. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            try:
                bandwidth_input = float(input("Digite a largura de banda do canal em Hz (ex: 3100 para canal telefônico): "))
                snr_db_input = float(input("Digite a relação sinal-ruído (SNR) em dB (ex: 30 para canal telefônico): "))
                snr_linear = 10**(snr_db_input / 10)

                capacity = shannon_hartley_capacity(bandwidth_input, snr_linear)
                print(f"Para Largura de Banda de {bandwidth_input} Hz e SNR de {snr_db_input} dB ({snr_linear:.2f} linear):")
                print(f"A capacidade máxima teórica do canal é de {capacity:.2f} bps.")
            except ValueError as e:
                print(f"Erro: {e}. Por favor, digite números válidos e não negativos.")
        elif choice == '2':
            print("Saindo da ferramenta do Teorema de Shannon-Hartley. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha '1' ou '2'.")

# Chama a ferramenta interativa
run_shannon_hartley_tool()

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