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

def calculate_subnet_details(ip_address, cidr_prefix):
    """
    Recebe um endereço de rede (IP e prefixo CIDR) e retorna detalhes como
    endereço de rede, endereço de broadcast, primeiro host utilizável,
    último host utilizável e número total de hosts utilizáveis.
    """
    if not isinstance(cidr_prefix, int) or not (0 <= cidr_prefix <= 32):
        raise ValueError("CIDR prefix must be an integer between 0 and 32 inclusive.")

    # Convert IP to 32-bit binary string
    ip_binary_str = decimal_to_binary_ip(ip_address).replace('.', '')
    ip_int = int(ip_binary_str, 2)

    # Generate binary subnet mask
    binary_mask_str = '1' * cidr_prefix + '0' * (32 - cidr_prefix)
    mask_int = int(binary_mask_str, 2)

    # Calculate network address
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

# Exemplo 1: /24
ip_1 = '192.168.1.10'
cidr_1 = 24
try:
    details_1 = calculate_subnet_details(ip_1, cidr_1)
    print(f"\nIP: {ip_1}, CIDR: /{cidr_1}")
    for key, value in details_1.items():
        print(f"  {key}: {value}")
except ValueError as e:
    print(f"Erro: {e}")

# Exemplo 2: /8
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


def perform_vlsm_calculation(main_ip, main_cidr, department_requirements):
    """
    Implements the VLSM logic to allocate subnets based on host requirements.
    Args:
        main_ip (str): The main network IP address (e.g., '192.168.1.0').
        main_cidr (int): The CIDR prefix of the main network (e.g., 24).
        department_requirements (list): A list of tuples, where each tuple is
                                         (department_name, num_hosts_required).

    Returns:
        list: A list of dictionaries, each representing an allocated subnet's properties.
    """
    if not isinstance(main_cidr, int) or not (0 <= main_cidr <= 32):
        raise ValueError("Main CIDR prefix must be an integer between 0 and 32 inclusive.")

    # Calculate main network details
    main_network_details = calculate_subnet_details(main_ip, main_cidr)
    main_network_address_int = int(decimal_to_binary_ip(main_network_details['network_address']).replace('.', ''), 2)
    main_broadcast_address_int = int(decimal_to_binary_ip(main_network_details['broadcast_address']).replace('.', ''), 2)

    # Sort department requirements in descending order of hosts needed for optimal VLSM allocation
    sorted_requirements = sorted(department_requirements, key=lambda x: x[1], reverse=True)

    allocated_subnets = []
    current_available_ip_int = main_network_address_int

    for dept_name, num_hosts_needed in sorted_requirements:
        if not isinstance(num_hosts_needed, int) or num_hosts_needed < 0:
            raise ValueError(f"Invalid host requirement for {dept_name}: {num_hosts_needed}. Must be a non-negative integer.")

        # Calculate required total addresses (hosts + network + broadcast)
        required_total_addresses = num_hosts_needed + 2
        if num_hosts_needed == 0: # handle /31 or /32 cases where 0 hosts are needed
            required_total_addresses = 2 # For /31, 2 addresses total, 0 usable
        if num_hosts_needed == 1: # For /30, 4 addresses total, 2 usable
            required_total_addresses = 4 # Minimum for 1 usable host usually implies /30

        # Determine the number of host bits needed
        if required_total_addresses <= 1:
            # If only 0 or 1 total addresses are needed, implies /32 or special cases
            # For /32, no host bits, 1 address total, 0 usable
            # For /31, 1 host bit, 2 addresses total, 0 usable
            if num_hosts_needed == 0:
                host_bits = 1 # smallest usable subnet will be /31 if 0 usable hosts needed (2 addresses total)
            else:
                host_bits = 0 # this would be a /32 which is a single IP
        else:
            host_bits = math.ceil(math.log2(required_total_addresses))

        # Calculate the new CIDR prefix
        new_cidr_prefix = 32 - host_bits

        # Edge case for 0 or 1 hosts, to ensure minimal valid CIDR (e.g., /30 or /31 or /32)
        if num_hosts_needed == 0 and new_cidr_prefix < 31:
            new_cidr_prefix = 31 # Ensures /31 for 0 usable hosts (2 total addresses)
        elif num_hosts_needed == 1 and new_cidr_prefix < 30:
            new_cidr_prefix = 30 # Ensures /30 for 1-2 usable hosts (4 total addresses)
        elif num_hosts_needed > 0 and new_cidr_prefix == 32:
            # A /32 has 0 usable hosts, so if more than 0 are needed, /32 is not enough.
            # This case means previous calculation may lead to a /32 even if >0 hosts are needed.
            # If num_hosts_needed = 1, required_total_addresses = 3, log2(3) = 1.58 -> ceil(1.58) = 2 host_bits -> 32-2 = /30
            # If num_hosts_needed = 0, required_total_addresses = 2, log2(2) = 1 host_bits -> 32-1 = /31
            # Re-evaluate for specific hosts needed: 1 host requires 4 addresses (/30), 2 hosts requires 4 addresses (/30)
            # If new_cidr_prefix is 32 and hosts are needed, it's an error in logic or not enough space
            pass # The logic for required_total_addresses and host_bits should handle this.

        if new_cidr_prefix < main_cidr:
            raise ValueError(f"Requested subnet for {dept_name} (/{new_cidr_prefix}) is larger than the main network (/{main_cidr}).")

        subnet_size = 2**(32 - new_cidr_prefix)

        # Align current_available_ip_int to the next valid network address boundary
        if current_available_ip_int % subnet_size != 0:
            current_available_ip_int = (current_available_ip_int // subnet_size + 1) * subnet_size

        # Check if allocation exceeds main network broadcast address
        if (current_available_ip_int + subnet_size - 1) > main_broadcast_address_int:
            raise Exception(f"Insufficient space in main network for {dept_name} (needs /{new_cidr_prefix}).")

        # Convert to dotted decimal and get subnet details
        current_ip_dotted = _long_bin_to_dotted_decimal(bin(current_available_ip_int)[2:].zfill(32))
        subnet_details = calculate_subnet_details(current_ip_dotted, new_cidr_prefix)

        subnet_details['department'] = dept_name
        subnet_details['requested_hosts'] = num_hosts_needed
        subnet_details['allocated_cidr'] = new_cidr_prefix
        allocated_subnets.append(subnet_details)

        # Update current_available_ip_int for the next iteration
        current_available_ip_int += subnet_size

    return allocated_subnets

# --- Example Usage for VLSM Calculation ---
print("\n--- VLSM Subnet Allocation ---")

# Example 1: Basic allocation
main_ip_1 = '192.168.1.0'
main_cidr_1 = 24
departments_1 = [
    ('Sales', 100),
    ('IT', 50),
    ('Guest_Wifi', 25),
    ('Servers', 10),
    ('Printers', 5),
    ('Management', 2)
]

try:
    vlsm_result_1 = perform_vlsm_calculation(main_ip_1, main_cidr_1, departments_1)
    print(f"\nVLSM for Main Network: {main_ip_1}/{main_cidr_1}")
    for subnet in vlsm_result_1:
        print(f"  Department: {subnet['department']}, Requested Hosts: {subnet['requested_hosts']}, Allocated CIDR: /{subnet['allocated_cidr']}")
        print(f"    Network: {subnet['network_address']}, Broadcast: {subnet['broadcast_address']}")
        print(f"    First Host: {subnet['first_usable_host']}, Last Host: {subnet['last_usable_host']}, Usable Hosts: {subnet['num_usable_hosts']}")
except (ValueError, Exception) as e:
    print(f"Error during VLSM calculation: {e}")

# Example 2: Insufficient space
main_ip_2 = '10.0.0.0'
main_cidr_2 = 29 # Very small network
departments_2 = [
    ('Small_Dept', 5),
    ('Another_Small_Dept', 5) # Needs 2 /29 subnets
]

try:
    vlsm_result_2 = perform_vlsm_calculation(main_ip_2, main_cidr_2, departments_2)
    print(f"\nVLSM for Main Network: {main_ip_2}/{main_cidr_2}")
    for subnet in vlsm_result_2:
        print(f"  Department: {subnet['department']}, Requested Hosts: {subnet['requested_hosts']}, Allocated CIDR: /{subnet['allocated_cidr']}")
        print(f"    Network: {subnet['network_address']}, Broadcast: {subnet['broadcast_address']}")
        print(f"    First Host: {subnet['first_usable_host']}, Last Host: {subnet['last_usable_host']}, Usable Hosts: {subnet['num_usable_hosts']}")
except (ValueError, Exception) as e:
    print(f"\nError during VLSM calculation: {e}")

# Example 3: Department needing 0 hosts (e.g., a point-to-point link)
main_ip_3 = '172.16.0.0'
main_cidr_3 = 24
departments_3 = [
    ('Link1', 0),
    ('Link2', 0),
    ('Users', 20)
]

try:
    vlsm_result_3 = perform_vlsm_calculation(main_ip_3, main_cidr_3, departments_3)
    print(f"\nVLSM for Main Network: {main_ip_3}/{main_cidr_3}")
    for subnet in vlsm_result_3:
        print(f"  Department: {subnet['department']}, Requested Hosts: {subnet['requested_hosts']}, Allocated CIDR: /{subnet['allocated_cidr']}")
        print(f"    Network: {subnet['network_address']}, Broadcast: {subnet['broadcast_address']}")
        print(f"    First Host: {subnet['first_usable_host']}, Last Host: {subnet['last_usable_host']}, Usable Hosts: {subnet['num_usable_hosts']}")
except (ValueError, Exception) as e:
    print(f"\nError during VLSM calculation: {e}")

