# CIDR - método de alocação de endereços IP ue melhora a eficiência do encaminhamento de dados na 
# Internet. Cada máquina, servidor e dispositivo de usuário final que se conecta à Internet tem um 
# número exclusivo, chamado endereço IP, associado a ele. Os dispositivos encontram e se comunicam 
# uns com os outros usando esses endereços IP. As organizações usam o CIDR para alocar endereços IP 
# de forma flexível e eficiente em suas redes.

# VLSW -  permite que os administradores de rede dividam um espaço de endereço IP em sub-redes de 
# vários tamanhos. Cada sub-rede pode ter uma contagem flexível de hosts e um número limitado de 
# endereços IP. Um endereço IP CIDR acrescenta um valor de sufixo informando o número de bits do 
# prefixo do endereço de rede a um endereço IP normal.
# CIDR usa a máscara de sub-rede de comprimento variável (VLSM) para alterar a proporção entre os 
# bits de endereço da rede e do host em um endereço IP.

def cidr_to_subnet_mask(cidr_prefix):
    """
    Converts a CIDR prefix (e.g., 24) to a dotted decimal subnet mask (e.g., 255.255.255.0).
    """
    if not isinstance(cidr_prefix, int) or not (0 <= cidr_prefix <= 32):
        raise ValueError("CIDR prefix must be an integer between 0 and 32 inclusive.")

    # Create a 32-bit binary string
    binary_mask = '1' * cidr_prefix + '0' * (32 - cidr_prefix)

    # Divide into four 8-bit octets and convert to decimal
    octets = []
    for i in range(0, 32, 8):
        octet_binary = binary_mask[i:i+8]
        octets.append(str(int(octet_binary, 2)))

    return '.'.join(octets)

# Example usage:
print("--- CIDR to Subnet Mask Conversion ---")

# Example 1: /24 (24 bits on, 8 bits off)
cidr_prefix_1 = 24
try:
    subnet_mask_1 = cidr_to_subnet_mask(cidr_prefix_1)
    print(f"CIDR /{cidr_prefix_1} -> Subnet Mask: {subnet_mask_1}")
except ValueError as e:
    print(f"Erro: {e}")

# Example 2: /8 (8 bits on, 24 bits off)
cidr_prefix_2 = 8
try:
    subnet_mask_2 = cidr_to_subnet_mask(cidr_prefix_2)
    print(f"CIDR /{cidr_prefix_2} -> Subnet Mask: {subnet_mask_2}")
except ValueError as e:
    print(f"Erro: {e}")

# Example 3: /30 (30 bits on, 2 bits off)
cidr_prefix_3 = 30
try:
    subnet_mask_3 = cidr_to_subnet_mask(cidr_prefix_3)
    print(f"CIDR /{cidr_prefix_3} -> Subnet Mask: {subnet_mask_3}")
except ValueError as e:
    print(f"Erro: {e}")

# Example 4: Invalid CIDR
cidr_prefix_invalid = 33
try:
    subnet_mask_invalid = cidr_to_subnet_mask(cidr_prefix_invalid)
    print(f"CIDR /{cidr_prefix_invalid} -> Subnet Mask: {subnet_mask_invalid}")
except ValueError as e:
    print(f"Erro: {e}")

def calculate_subnet_details(ip_address, cidr_prefix):
    """
    Recebe um endereço de rede (IP e prefixo CIDR) e retorna detalhes como
    endereço de rede, endereço de broadcast, primeiro host utilizável,
    último host utilizável e número total de hosts utilizáveis.
    """
    if not isinstance(cidr_prefix, int) or not (0 <= cidr_prefix <= 32):
        raise ValueError("CIDR prefix must be an integer between 0 and 32 inclusive.")

    # Convert IP to 32-bit binary string
    # decimal_to_binary_ip - IPV4.py function
    ip_binary_str = decimal_to_binary_ip(ip_address).replace('.', '')
    ip_int = int(ip_binary_str, 2)

    # Generate binary subnet mask
    binary_mask_str = '1' * cidr_prefix + '0' * (32 - cidr_prefix)
    mask_int = int(binary_mask_str, 2)

    # Calculate network address
    # _long_bin_to_dotted_decimal - IPV4.py function
    network_address_int = ip_int & mask_int
    network_address_dotted = _long_bin_to_dotted_decimal(bin(network_address_int)[2:].zfill(32))

    # Calculate broadcast address
    # Use 0xFFFFFFFF as a 32-bit mask for NOT operation
    broadcast_address_int = network_address_int | (~mask_int & 0xFFFFFFFF)
    broadcast_address_dotted = _long_bin_to_dotted_decimal(bin(broadcast_address_int)[2:].zfill(32))

    # Calculate number of usable hosts
    if cidr_prefix == 31 or cidr_prefix == 32:
        num_usable_hosts = 0
    else:
        num_usable_hosts = (2**(32 - cidr_prefix)) - 2

    # Determine first and last usable hosts
    if num_usable_hosts > 0:
        first_usable_host_int = network_address_int + 1
        first_usable_host_dotted = _long_bin_to_dotted_decimal(bin(first_usable_host_int)[2:].zfill(32))

        last_usable_host_int = broadcast_address_int - 1
        last_usable_host_dotted = _long_bin_to_dotted_decimal(bin(last_usable_host_int)[2:].zfill(32))
    else:
        first_usable_host_dotted = 'N/A'
        last_usable_host_dotted = 'N/A'

    details = {
        'network_address': network_address_dotted,
        'broadcast_address': broadcast_address_dotted,
        'first_usable_host': first_usable_host_dotted,
        'last_usable_host': last_usable_host_dotted,
        'num_usable_hosts': num_usable_hosts
    }

    return details

# --- Exemplos de uso ---
print("\n--- Análise de Detalhes de Sub-rede ---")

# Exemplo 1: /24 (24 bits on, 8 bits off)
ip_1 = '192.168.1.10'
cidr_1 = 24
try:
    details_1 = calculate_subnet_details(ip_1, cidr_1)
    print(f"\nIP: {ip_1}, CIDR: /{cidr_1}")
    for key, value in details_1.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 2: /8 (8 bits on, 24 bits off)
ip_2 = '10.0.0.1'
cidr_2 = 8
try:
    details_2 = calculate_subnet_details(ip_2, cidr_2)
    print(f"\nIP: {ip_2}, CIDR: /{cidr_2}")
    for key, value in details_2.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 3: /30 (point-to-point link)
ip_3 = '172.16.1.5'
cidr_3 = 30
try:
    details_3 = calculate_subnet_details(ip_3, cidr_3)
    print(f"\nIP: {ip_3}, CIDR: /{cidr_3}")
    for key, value in details_3.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 4: /31 (no usable hosts, but sometimes used for point-to-point)
ip_4 = '192.168.10.0'
cidr_4 = 31
try:
    details_4 = calculate_subnet_details(ip_4, cidr_4)
    print(f"\nIP: {ip_4}, CIDR: /{cidr_4}")
    for key, value in details_4.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 5: /32 (single host)
ip_5 = '192.168.10.1'
cidr_5 = 32
try:
    details_5 = calculate_subnet_details(ip_5, cidr_5)
    print(f"\nIP: {ip_5}, CIDR: /{cidr_5}")
    for key, value in details_5.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 6: CIDR inválido
ip_6 = '192.168.1.1'
cidr_6 = 33
try:
    details_6 = calculate_subnet_details(ip_6, cidr_6)
    print(f"\nIP: {ip_6}, CIDR: /{cidr_6}")
    for key, value in details_6.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")