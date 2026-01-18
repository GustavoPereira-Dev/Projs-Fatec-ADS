def convolutional_encode(message_bits, generator_polynomials, initial_state=None):
    """
    Performs simplified convolutional encoding.

    Args:
        message_bits (str): A string of '0's and '1's representing the input message.
        generator_polynomials (list): A list of strings, each representing a generator
                                     polynomial in binary form (e.g., ['1101', '1111']).
        initial_state (str, optional): The initial state of the shift register. Defaults to
                                       '0's with length equal to len(generator_polynomials[0]) - 1.

    Returns:
        str: The encoded bit stream.

    Raises:
        ValueError: If input validation fails.
    """
    # 2. Input Validation
    if not all(c in '01' for c in message_bits):
        raise ValueError("message_bits must contain only '0's and '1's.")

    if not isinstance(generator_polynomials, list) or not generator_polynomials:
        raise ValueError("generator_polynomials must be a non-empty list.")

    poly_length = len(generator_polynomials[0])
    for poly in generator_polynomials:
        if not all(c in '01' for c in poly):
            raise ValueError(f"Generator polynomial '{poly}' must contain only '0's and '1's.")
        if len(poly) != poly_length:
            raise ValueError("All generator polynomials must have the same length.")

    num_memory_elements = poly_length - 1

    if initial_state is None:
        initial_state = '0' * num_memory_elements
    else:
        if not all(c in '01' for c in initial_state):
            raise ValueError("initial_state must contain only '0's and '1's.")
        if len(initial_state) != num_memory_elements:
            raise ValueError(f"initial_state length ({len(initial_state)}) must match the number of memory elements ({num_memory_elements}).")

    # 3. Initialize shift register
    # Convert initial_state string to a list of ints, most significant bit first
    shift_register = [int(b) for b in initial_state]

    encoded_bits = []

    # Add dummy zeros to message_bits for flushing the shift register
    message_bits_with_flush = message_bits + '0' * num_memory_elements

    # 5. Iterate through each bit in message_bits_with_flush
    for input_bit_char in message_bits_with_flush:
        input_bit = int(input_bit_char)

        # a. Prepend the current bit to the shift register
        shift_register.insert(0, input_bit)
        # Keep the register length correct by removing the oldest bit if it grew too large
        if len(shift_register) > poly_length: # poly_length is 1 (input) + num_memory_elements
            shift_register.pop() # Remove the oldest bit

        current_output_bits = []
        for poly_str in generator_polynomials:
            output_bit = 0
            for i in range(poly_length):
                # i represents the position in the generator polynomial, from left to right
                # Corresponding element in shift_register is also from left to right (most recent to oldest)
                if poly_str[i] == '1':
                    output_bit ^= shift_register[i] # XOR operation
            current_output_bits.append(output_bit)

        encoded_bits.extend(current_output_bits)

    # 7. Convert the encoded_bits (list of integers) back into a single binary string.
    return ''.join(map(str, encoded_bits))

def run_convolutional_tool():
    print("\n--- Ferramenta de Codificação Convolucional Simplificada ---")
    while True:
        print("\nEscolha uma opção:")
        print("1. Realizar Codificação Convolucional")
        print("2. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            try:
                message = input("Digite a mensagem binária (ex: 1011): ")
                num_polynomials = int(input("Quantos polinômios geradores você tem? "))
                generator_polynomials_input = []
                for i in range(num_polynomials):
                    poly = input(f"Digite o polinômio gerador {i+1} (binário, ex: 1101): ")
                    generator_polynomials_input.append(poly)

                initial_state_choice = input("Deseja especificar um estado inicial para o registrador de deslocamento? (s/n, padrão 'n'): ")
                initial_state = None
                if initial_state_choice.lower() == 's':
                    # Determine expected length based on first polynomial's length
                    poly_length_for_state = len(generator_polynomials_input[0])
                    num_memory_elements_for_state = poly_length_for_state - 1
                    initial_state = input(f"Digite o estado inicial (binário, {num_memory_elements_for_state} bits): ")

                encoded_output = convolutional_encode(message, generator_polynomials_input, initial_state)
                print(f"\nMensagem Original: {message}")
                print(f"Polinômios Geradores: {generator_polynomials_input}")
                print(f"Saída Codificada: {encoded_output}")

            except ValueError as e:
                print(f"Erro: {e}. Por favor, verifique as entradas.")
            except Exception as e:
                print(f"Ocorreu um erro inesperado: {e}")

        elif choice == '2':
            print("Saindo da ferramenta de Codificação Convolucional. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha '1' ou '2'.")

# Chama a ferramenta interativa
run_convolutional_tool()
