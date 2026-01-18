def hamming_distance(binary_string1, binary_string2):
    """
    Calculates the Hamming distance between two binary strings.

    The Hamming distance between two strings of equal length is the number
    of positions at which the corresponding symbols are different.

    Args:
        binary_string1 (str): The first binary string.
        binary_string2 (str): The second binary string.

    Returns:
        int: The Hamming distance between the two strings.

    Raises:
        ValueError: If the strings are not binary or do not have the same length.
    """
    # 1. Input Validation
    if not all(c in '01' for c in binary_string1) or not all(c in '01' for c in binary_string2):
        raise ValueError("Both input strings must be binary (contain only '0's and '1's).")

    if len(binary_string1) != len(binary_string2):
        raise ValueError("Input strings must have the same length.")

    # 2. Hamming Distance Calculation
    distance = 0
    for i in range(len(binary_string1)):
        if binary_string1[i] != binary_string2[i]:
            distance += 1

    return distance

def run_hamming_tool():
    print("\n--- Ferramenta de Cálculo de Distância de Hamming ---")
    while True:
        print("\nEscolha uma opção:")
        print("1. Calcular Distância de Hamming")
        print("2. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            try:
                str1 = input("Digite a primeira string binária (ex: 1011001): ")
                str2 = input("Digite a segunda string binária (ex: 1010001): ")
                dist = hamming_distance(str1, str2)
                print(f"Strings: '{str1}', '{str2}'")
                print(f"Distância de Hamming: {dist}")
            except ValueError as e:
                print(f"Erro: {e}. Por favor, verifique as strings digitadas.")
        elif choice == '2':
            print("Saindo da ferramenta da Distância de Hamming. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha '1' ou '2'.")

# Chama a ferramenta interativa
run_hamming_tool()
