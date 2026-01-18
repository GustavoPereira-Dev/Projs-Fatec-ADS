def calculate_internet_checksum(data_bytes):
    if not isinstance(data_bytes, bytes):
        raise ValueError("Input data must be a bytes object.")

    total_sum = 0
    # Iterate over data_bytes in 16-bit (2-byte) chunks
    # If the length is odd, pad the last byte with a zero.
    padded_data = bytearray(data_bytes)
    if len(padded_data) % 2 != 0:
        padded_data.append(0) # Pad with a null byte

    for i in range(0, len(padded_data), 2):
        word = (padded_data[i] << 8) + padded_data[i+1]
        total_sum += word

        # Handle carries that spill over 16 bits
        # If the sum exceeds 0xFFFF (16 bits), add the carry to the sum.
        while (total_sum >> 16) > 0:
            total_sum = (total_sum & 0xFFFF) + (total_sum >> 16)

    # Take the one's complement of the final sum
    checksum = ~total_sum & 0xFFFF

    return checksum

def run_checksum_tool():
    print("\n--- Ferramenta de Cálculo de Checksum de Redes ---")
    while True:
        print("\nEscolha uma opção:")
        print("1. Calcular Checksum")
        print("2. Sair")

        choice = input("Sua escolha: ")

        if choice == '1':
            try:
                hex_input = input("Digite os dados em formato hexadecimal (ex: C0A801010001): ")
                if not all(c in '0123456789abcdefABCDEF' for c in hex_input):
                    raise ValueError("Dados hexadecimais inválidos.")
                
                # Ensure even length for conversion to bytes
                if len(hex_input) % 2 != 0:
                    hex_input = '0' + hex_input # Pad with a leading zero if odd

                data_bytes = bytes.fromhex(hex_input)
                
                calculated_checksum = calculate_internet_checksum(data_bytes)
                print(f"Dados de Entrada (Hex): {hex_input.upper()}")
                print(f"Dados de Entrada (Bytes): {data_bytes}")
                print(f"Checksum Calculado: 0x{calculated_checksum:04X}")
            except ValueError as e:
                print(f"Erro: {e}. Por favor, verifique os dados digitados.")
            except Exception as e:
                print(f"Ocorreu um erro inesperado: {e}")
        elif choice == '2':
            print("Saindo da ferramenta de Checksum. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha '1' ou '2'.")

# Chama a ferramenta interativa
run_checksum_tool()
