def binary_to_decimal_ip(binary_ip):
    """
    Converts a binary IP address string to a decimal IP address string.
    Example: '11000000.10101000.00000001.00000001' -> '192.168.1.1'
    """
    octets = binary_ip.split('.')
    decimal_octets = []
    for octet in octets:
        if len(octet) != 8 or not all(c in '01' for c in octet):
            raise ValueError(f"Invalid binary octet: {octet}. Each octet must be 8 bits.")
        decimal_octets.append(str(int(octet, 2)))
    return '.'.join(decimal_octets)

def decimal_to_binary_ip(decimal_ip):
    """
    Converts a decimal IP address string to a binary IP address string.
    Example: '192.168.1.1' -> '11000000.10101000.00000001.00000001'
    """
    octets = decimal_ip.split('.')
    binary_octets = []
    for octet in octets:
        try:
            decimal_val = int(octet)
            if not (0 <= decimal_val <= 255):
                raise ValueError
        except ValueError:
            raise ValueError(f"Invalid decimal octet: {octet}. Must be an integer between 0 and 255.")
        binary_octets.append(bin(decimal_val)[2:].zfill(8))
    return '.'.join(binary_octets)

def _long_bin_to_dotted_decimal(long_bin_str):
    """
    Helper to convert a 32-bit binary string to dotted decimal format.
    """
    if len(long_bin_str) != 32:
        raise ValueError("Input binary string must be 32 bits long.")
    octets = []
    for i in range(0, 32, 8):
        octets.append(str(int(long_bin_str[i:i+8], 2)))
    return '.'.join(octets)

def analyze_ip_properties(decimal_ip, subnet_mask_str='255.255.255.0'):
    """
    Analyzes properties of a given decimal IP address with a subnet mask.
    Returns a dictionary of properties.
    """
    properties = {}
    ip_octets = [int(o) for o in decimal_ip.split('.')]
    first_octet = ip_octets[0]

    # 1. IP Class
    if 1 <= first_octet <= 126:
        properties['class'] = 'A'
    elif 128 <= first_octet <= 191:
        properties['class'] = 'B'
    elif 192 <= first_octet <= 223:
        properties['class'] = 'C'
    elif 224 <= first_octet <= 239:
        properties['class'] = 'D (Multicast)'
    elif 240 <= first_octet <= 255:
        properties['class'] = 'E (Experimental)'
    else:
        properties['class'] = 'Reserved/Special'

    # 2. Private IP Ranges (RFC 1918)
    properties['is_private'] = False
    if (first_octet == 10) or \
       (first_octet == 172 and 16 <= ip_octets[1] <= 31) or \
       (first_octet == 192 and ip_octets[1] == 168):
        properties['is_private'] = True

    # 3. Loopback Address (127.0.0.0/8)
    properties['is_loopback'] = (first_octet == 127)

    # 4. Multicast Address (Class D range)
    properties['is_multicast'] = (224 <= first_octet <= 239)

    # 5. Network, Broadcast, and Host Address with given subnet mask
    try:
        ip_bin_str = decimal_to_binary_ip(decimal_ip).replace('.', '')
        mask_bin_str = decimal_to_binary_ip(subnet_mask_str).replace('.', '')

        ip_int = int(ip_bin_str, 2)
        mask_int = int(mask_bin_str, 2)

        network_int = ip_int & mask_int
        broadcast_int = network_int | (~mask_int & 0xFFFFFFFF) # Ensure 32-bit operation

        properties['network_address'] = _long_bin_to_dotted_decimal(bin(network_int)[2:].zfill(32))
        properties['broadcast_address'] = _long_bin_to_dotted_decimal(bin(broadcast_int)[2:].zfill(32))

        properties['is_network_address'] = (decimal_ip == properties['network_address'])
        properties['is_broadcast_address'] = (decimal_ip == properties['broadcast_address'])
        properties['is_host_address'] = not (properties['is_network_address'] or properties['is_broadcast_address'])
    except ValueError as e:
        properties['network_broadcast_error'] = str(e)

    return properties


def run_ip_tool():
    while True:
        print("\n--- Ferramenta de Análise e Conversão de IP ---")
        print("1. Converter IP Binário para Decimal")
        print("2. Converter IP Decimal para Binário")
        print("3. Analisar Propriedades de IP")
        print("4. Sair")

        choice = input("Escolha uma opção (1-4): ")

        if choice == '1':
            binary_ip = input("Digite o endereço IP binário (ex: 11000000.10101000.00000001.00000001): ")
            try:
                decimal_result = binary_to_decimal_ip(binary_ip)
                print(f"IP Binário: {binary_ip} -> IP Decimal: {decimal_result}")
            except ValueError as e:
                print(f"Erro: {e}")
        elif choice == '2':
            decimal_ip = input("Digite o endereço IP decimal (ex: 192.168.1.1): ")
            try:
                binary_result = decimal_to_binary_ip(decimal_ip)
                print(f"IP Decimal: {decimal_ip} -> IP Binário: {binary_result}")
            except ValueError as e:
                print(f"Erro: {e}")
        elif choice == '3':
            decimal_ip = input("Digite o endereço IP decimal para análise (ex: 192.168.1.10): ")
            subnet_mask = input("Digite a máscara de sub-rede (padrão: 255.255.255.0): ")
            if not subnet_mask:
                subnet_mask = '255.255.255.0'
            try:
                properties = analyze_ip_properties(decimal_ip, subnet_mask)
                print(f"\nAnálise para IP: {decimal_ip}, Máscara: {subnet_mask}")
                for key, value in properties.items():
                    print(f"  {key}: {value}")
            except ValueError as e:
                print(f"Erro: {e}")
        elif choice == '4':
            print("Saindo da ferramenta. Até logo!")
            break
        else:
            print("Opção inválida. Por favor, escolha um número entre 1 e 4.")

run_ip_tool()