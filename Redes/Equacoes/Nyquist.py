def nyquist_sampling_rate(max_frequency):
    """
    Calculates the minimum sampling rate (Nyquist frequency) required
    for a signal, according to the Nyquist Theorem.

    The Nyquist Theorem states that, to fully reconstruct an analog signal
    from its digital samples, the sampling rate must be at least twice
    the highest frequency present in the signal.

    Args:
        max_frequency (float): The highest frequency (in Hz) present in the signal.

    Returns:
        float: The minimum required sampling rate (in Hz).
    Raises:
        ValueError: If the maximum frequency is negative.
    """
    if max_frequency < 0:
        raise ValueError("The maximum frequency cannot be negative.")
    return 2 * max_frequency

def run_nyquist_tool():
    print("\n--- Ferramenta do Teorema de Nyquist ---")
    while True:
        print("\nEscolha uma opção:")
        print("1. Calcular Taxa de Amostragem de Nyquist")
        print("2. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            try:
                max_freq_input = float(input("Digite a frequência máxima do sinal em Hz (ex: 20000 para 20kHz): "))
                nyquist_rate = nyquist_sampling_rate(max_freq_input)
                print(f"Para um sinal com frequência máxima de {max_freq_input} Hz, a taxa de amostragem mínima (Nyquist) é de {nyquist_rate} Hz.")
            except ValueError as e:
                print(f"Erro: {e}. Por favor, digite um número válido e não negativo.")
        elif choice == '2':
            print("Saindo da ferramenta do Teorema de Nyquist. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha '1' ou '2'.")

# Chama a ferramenta interativa
run_nyquist_tool()

print("""
**Explicação do Teorema de Nyquist:**
O Teorema de Nyquist, também conhecido como teorema de amostragem de Nyquist-Shannon, é um princípio fundamental na teoria da informação e processamento de sinais.
Ele estabelece que, para que um sinal analógico seja perfeitamente reconstruído a partir de uma sequência discreta de amostras, a taxa de amostragem deve ser de pelo menos duas vezes a frequência máxima do sinal original.
Se a taxa de amostragem for menor que o dobro da frequência máxima, ocorrerá o fenômeno de aliasing, onde diferentes frequências se tornam indistinguíveis após a amostragem, levando à perda irreversível de informação.
""")