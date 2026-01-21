# Redes

## IPV4.py

Este script Python oferece funcionalidades para conversão e análise de endereços IPv4.

### Menu do algoritmo
```
--- Ferramenta de Análise e Conversão de IP ---
1. Converter IP Binário para Decimal
2. Converter IP Decimal para Binário
3. Analisar Propriedades de IP
4. Sair
```

### Exemplos de Conversão

#### Converter de Binário para Decimal
```python 
binary_ip_example = '11000000.10101000.00000001.00000001'
decimal_result = binary_to_decimal_ip(binary_ip_example)
print(f"IP Binário: {binary_ip_example} -> IP Decimal: {decimal_result}") 
```
- IP Binário: 11000000.10101000.00000001.00000001 -> IP Decimal: 192.168.1.1

#### Converter de Decimal para Binário
```python 
decimal_ip_example = '192.168.1.1'
binary_result = decimal_to_binary_ip(decimal_ip_example)
print(f"IP Decimal: {decimal_ip_example} -> IP Binário: {binary_result}")
```
- IP Decimal: 192.168.1.1 -> IP Binário: 11000000.10101000.00000001.00000001

#### Exemplo com outro IP
```python 
binary_ip_example_2 = '10101100.00010000.00000000.00000001'
decimal_result_2 = binary_to_decimal_ip(binary_ip_example_2)
print(f"IP Binário: {binary_ip_example_2} -> IP Decimal: {decimal_result_2}")
```
- IP Binário: 10101100.00010000.00000000.00000001 -> IP Decimal: 172.16.0.1

```python 
decimal_ip_example_2 = '172.16.0.1'
binary_result_2 = decimal_to_binary_ip(decimal_ip_example_2)
print(f"IP Decimal: {decimal_ip_example_2} -> IP Binário: {binary_result_2}")
```
- IP Decimal: 172.16.0.1 -> IP Binário: 10101100.00010000.00000000.00000001

### Exemplos de Propriedades de IP

- class: São as classes de um endereço IP
  - Classe A: Destinada a redes muito grandes, com um grande número de hosts. O primeiro bit é 0, e os endereços vão de 1.0.0.0 a 126.255.255.255.
  - Classe B: Para redes de tamanho médio. O primeiro bits são 10, e os endereços vão de 128.0.0.0 a 191.255.255.255.
  - Classe C: Para redes pequenas. O primeiro bits são 110, e os endereços vão de 192.0.0.0 a 223.255.255.255.
  - Classe D: Usada para multicast. O primeiro bits são 1110, e os endereços vão de 224.0.0.0 a 239.255.255.255.
  - Classe E: Reservada para uso futuro e pesquisa. O primeiro bits são 1111, e os endereços vão de 240.0.0.0 a 255.255.255.255. 

- is_private: É um endereço IP privado
- is_loopback: É um endereço de looqback (que o próprio host em si se chamado)
- is_multicast: É multicast (tipo de endereço que identifica grupos de dispositivos que desejam receber dados transmitidos)
- network_address: Endereço de rede do host
- broadcast_address: É um ip de Broadcast (que é sempre o último endereço ip de uma rede)
- is_network_address: É um endereço de rede, não de host (ou dispositivo)
- is_broadcast_address:
- is_host_address: É um endereço de um dispositivo (host)

#### Exemplo de IP privado
```python 
ip_to_analyze_1 = '192.168.1.10'
subnet_mask_1 = '255.255.255.0'
properties_1 = analyze_ip_properties(ip_to_analyze_1, subnet_mask_1)
print(f"IP: {ip_to_analyze_1}, Máscara: {subnet_mask_1}")
for key, value in properties_1.items():
    print(f"  {key}: {value}")
```

- IP: 192.168.1.10, Máscara: 255.255.255.0
  - class: C
  - is_private: True
  - is_loopback: False
  - is_multicast: False
  - network_address: 192.168.1.0
  - broadcast_address: 192.168.1.255
  - is_network_address: False
  - is_broadcast_address: False
  - is_host_address: True

#### Exemplo de IP público
```python 
ip_to_analyze_2 = '8.8.8.8'
subnet_mask_2 = '255.255.255.0'
properties_2 = analyze_ip_properties(ip_to_analyze_2, subnet_mask_2)
print(f"\nIP: {ip_to_analyze_2}, Máscara: {subnet_mask_2}")
for key, value in properties_2.items():
    print(f"  {key}: {value}")
```
- IP: 8.8.8.8, Máscara: 255.255.255.0
  - class: A
  - is_private: False
  - is_loopback: False
  - is_multicast: False
  - network_address: 8.8.8.0
  - broadcast_address: 8.8.8.255
  - is_network_address: False
  - is_broadcast_address: False
  - is_host_address: True

#### Exemplo de Loopback
```python 
ip_to_analyze_3 = '127.0.0.1'
subnet_mask_3 = '255.255.255.0'
properties_3 = analyze_ip_properties(ip_to_analyze_3, subnet_mask_3)
print(f"\nIP: {ip_to_analyze_3}, Máscara: {subnet_mask_3}")
for key, value in properties_3.items():
    print(f"  {key}: {value}")
```

- IP: 127.0.0.1, Máscara: 255.255.255.0
  - class: Reserved/Special
  - is_private: False
  - is_loopback: True
  - is_multicast: False
  - network_address: 127.0.0.0
  - broadcast_address: 127.0.0.255
  - is_network_address: False
  - is_broadcast_address: False
  - is_host_address: True

#### Exemplo de endereço de rede
```python 
ip_to_analyze_4 = '192.168.1.0'
subnet_mask_4 = '255.255.255.0'
properties_4 = analyze_ip_properties(ip_to_analyze_4, subnet_mask_4)
print(f"\nIP: {ip_to_analyze_4}, Máscara: {subnet_mask_4}")
for key, value in properties_4.items():
    print(f"  {key}: {value}")
```
- IP: 192.168.1.0, Máscara: 255.255.255.0
  - class: C
  - is_private: True
  - is_loopback: False
  - is_multicast: False
  - network_address: 192.168.1.0
  - broadcast_address: 192.168.1.255
  - is_network_address: True
  - is_broadcast_address: False
  - is_host_address: False

#### Exemplo de endereço de broadcast
```python 
ip_to_analyze_5 = '192.168.1.255'
subnet_mask_5 = '255.255.255.0'
properties_5 = analyze_ip_properties(ip_to_analyze_5, subnet_mask_5)
print(f"\nIP: {ip_to_analyze_5}, Máscara: {subnet_mask_5}")
for key, value in properties_5.items():
    print(f"  {key}: {value}")
```
- IP: 192.168.1.255, Máscara: 255.255.255.0
  - class: C
  - is_private: True
  - is_loopback: False
  - is_multicast: False
  - network_address: 192.168.1.0
  - broadcast_address: 192.168.1.255
  - is_network_address: False
  - is_broadcast_address: True
  - is_host_address: False

## IPV6MAC.py

Este script Python oferece funcionalidades para conversão e análise de endereços IPv6 e MAC.

### Menu do algoritmo
```
--- Ferramenta de Análise e Conversão IPv6/MAC ---
1. Converter IPv6 Hexadecimal para Decimal
2. Converter IPv6 Decimal para Hexadecimal
3. Analisar Propriedades IPv6
4. Converter MAC Hexadecimal para Decimal
5. Converter MAC Decimal para Hexadecimal
6. Analisar Propriedades MAC
7. Sair
Escolha uma opção (1-7):
```

### Exemplos de Conversão

#### Converter IPv6 Hexadecimal para Decimal
```python
ipv6_hex_example_full = "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
ipv6_dec_full = ipv6_hex_to_decimal(ipv6_hex_example_full)
print(f"IPv6 Hex (full): {ipv6_hex_example_full} -> Decimal: {ipv6_dec_full}")
```
- IPv6 Hex (full): 2001:0db8:85a3:0000:0000:8a2e:0370:7334 -> Decimal: 42540766452641154071740215577757643572

#### Converter IPv6 Decimal para Hexadecimal
```python
ipv6_hex_back_full = ipv6_decimal_to_hex(ipv6_dec_full)
print(f"IPv6 Decimal: {ipv6_dec_full} -> Hex: {ipv6_hex_back_full}")
```
- IPv6 Decimal: 42540766452641154071740215577757643572 -> Hex: 2001:db8:85a3::8a2e:370:7334

#### Converter MAC Hexadecimal para Decimal
```python
mac_hex_example = "00:1A:2B:3C:4D:5E"
mac_dec = mac_hex_to_decimal(mac_hex_example)
print(f"MAC Hex (colon): {mac_hex_example} -> Decimal: {mac_dec}")
```
- MAC Hex (colon): 00:1A:2B:3C:4D:5E -> Decimal: 112394521950

#### Converter MAC Decimal para Hexadecimal
```python
mac_hex_back = mac_decimal_to_hex(mac_dec)
print(f"MAC Decimal: {mac_dec} -> Hex: {mac_hex_back}")
```
- MAC Decimal: 112394521950 -> Hex: 00:1A:2B:3C:4D:5E

### Exemplos de Propriedades de Endereço

#### Análise de Propriedades IPv6
O script analisa as seguintes propriedades de um endereço IPv6:
- **Endereço de Loopback**: Indica se o endereço é o loopback `::1`.
- **Endereço Multicast**: Indica se o endereço pertence ao bloco multicast (primeiros 8 bits são `0xFF`).
- **Endereço Global Unicast**: Indica se o endereço pertence ao bloco global unicast (primeiros 3 bits são `001`).

```python
print(f"\nAnálise para IPv6: {ipv6_hex_example_full}")
properties_full = analyze_ipv6_properties(ipv6_dec_full)
for key, value in properties_full.items():
    print(f"  {key}: {value}")
```
- Análise para IPv6: 2001:0db8:85a3:0000:0000:8a2e:0370:7334
  - is_loopback: False
  - is_multicast: False
  - is_global_unicast: True


- Análise para IPv6: 2001:db8::1
  - is_loopback: False
  - is_multicast: False
  - is_global_unicast: True

- Análise para IPv6: ::1
  - is_loopback: True
  - is_multicast: False
  - is_global_unicast: False

- Análise para IPv6: ::
  - is_loopback: False
  - is_multicast: False
  - is_global_unicast: False

#### Análise de Propriedades MAC
O script analisa as seguintes propriedades de um endereço MAC:
- **Unicast/Multicast**: Indica se o endereço MAC é unicast (LSB do primeiro octeto é 0) ou multicast (LSB do primeiro octeto é 1).
- **OUI (Organizationally Unique Identifier)**: Extrai os primeiros 24 bits do endereço MAC, que representam o identificador exclusivo do fabricante.

```python
print(f"\nAnálise para MAC: {mac_hex_example}")
properties_mac = analyze_mac_properties(mac_dec)
for key, value in properties_mac.items():
    print(f"  {key}: {value}")
```
- Análise para MAC: 00:1A:2B:3C:4D:5E
  - is_multicast: False
  - is_unicast: True
  - oui: 001A2B

- Análise para MAC: AB-CD-EF-01-23-45
  - is_multicast: True
  - is_unicast: False
  - oui: ABCDEF

- Análise para MAC: aabb.ccdd.eeff
  - is_multicast: False
  - is_unicast: True
  - oui: AABBCC

## Subredes.py

Este script Python oferece funcionalidades para conversão, análise e alocação de sub-redes IPv4 utilizando CIDR (Classless Inter-Domain Routing) e VLSM (Variable Length Subnet Masking).

### Menu Interativo

O script inclui um menu interativo para facilitar o uso das suas funcionalidades:
```
--- Ferramenta de Análise e Conversão de IP ---
1. Converter IP Binário para Decimal
2. Converter IP Decimal para Binário
3. Analisar Propriedades de IP
4. Realizar Cálculo VLSM
5. Sair
Escolha uma opção (1-5):
```

### CIDR para Máscara de Sub-rede

A função `cidr_to_subnet_mask` converte um prefixo CIDR (por exemplo, /24) em uma máscara de sub-rede decimal pontilhada (por exemplo, 255.255.255.0).

#### Exemplos de Conversão
```python
# Exemplo 1: /24
cidr_prefix_1 = 24
subnet_mask_1 = cidr_to_subnet_mask(cidr_prefix_1)
print(f"CIDR /{cidr_prefix_1} -> Subnet Mask: {subnet_mask_1}")

# Exemplo 2: /8
cidr_prefix_2 = 8
subnet_mask_2 = cidr_to_subnet_mask(cidr_prefix_2)
print(f"CIDR /{cidr_prefix_2} -> Subnet Mask: {subnet_mask_2}")

# Exemplo 3: /30
cidr_prefix_3 = 30
subnet_mask_3 = cidr_to_subnet_mask(cidr_prefix_3)
print(f"CIDR /{cidr_prefix_3} -> Subnet Mask: {subnet_mask_3}")
```
**Saída:**
```
--- CIDR to Subnet Mask Conversion ---
CIDR /24 -> Subnet Mask: 255.255.255.0
CIDR /8 -> Subnet Mask: 255.0.0.0
CIDR /30 -> Subnet Mask: 255.255.255.252
```

### Análise de Detalhes de Sub-rede

A função `calculate_subnet_details` recebe um endereço IP e um prefixo CIDR, retornando detalhes abrangentes da sub-rede, como endereço de rede, endereço de broadcast, primeiro e último hosts utilizáveis, e o número total de hosts utilizáveis.

#### Exemplos de Análise
```python
# Exemplo 1: /24
ip_1 = '192.168.1.10'
cidr_1 = 24
details_1 = calculate_subnet_details(ip_1, cidr_1)
print(f"\nIP: {ip_1}, CIDR: /{cidr_1}")
for key, value in details_1.items():
    print(f"  {key}: {value}")

# Exemplo 3: /30 (link ponto a ponto)
ip_3 = '172.16.1.5'
cidr_3 = 30
details_3 = calculate_subnet_details(ip_3, cidr_3)
print(f"\nIP: {ip_3}, CIDR: /{cidr_3}")
for key, value in details_3.items():
    print(f"  {key}: {value}")

# Exemplo 4: /31 (nenhum host utilizável)
ip_4 = '192.168.10.0'
cidr_4 = 31
details_4 = calculate_subnet_details(ip_4, cidr_4)
print(f"\nIP: {ip_4}, CIDR: /{cidr_4}")
for key, value in details_4.items():
    print(f"  {key}: {value}")
```
**Saída:**
```
--- Análise de Detalhes de Sub-rede ---

IP: 192.168.1.10, CIDR: /24
  network_address: 192.168.1.0
  broadcast_address: 192.168.1.255
  first_usable_host: 192.168.1.1
  last_usable_host: 192.168.1.254
  num_usable_hosts: 254

IP: 172.16.1.5, CIDR: /30
  network_address: 172.16.1.4
  broadcast_address: 172.16.1.7
  first_usable_host: 172.16.1.5
  last_usable_host: 172.16.1.6
  num_usable_hosts: 2

IP: 192.168.10.0, CIDR: /31
  network_address: 192.168.10.0
  broadcast_address: 192.168.10.1
  first_usable_host: N/A
  last_usable_host: N/A
  num_usable_hosts: 0
```

### Cálculo VLSM (Variable Length Subnet Masking)

A função `perform_vlsm_calculation` implementa a lógica VLSM para alocar sub-redes com base nos requisitos de hosts dos departamentos. Ela ordena os requisitos, aloca sub-redes de forma eficiente e retorna suas propriedades.

#### Exemplos de Alocação VLSM
```python
# Exemplo 1: Alocação básica
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

vlsm_result_1 = perform_vlsm_calculation(main_ip_1, main_cidr_1, departments_1)
print(f"\n--- Resultados do Cálculo VLSM para {main_ip_1}/{main_cidr_1} ---")
for subnet in vlsm_result_1:
    print(f"  Departamento: {subnet['department']}, Hosts Solicitados: {subnet['requested_hosts']}, CIDR Alocado: /{subnet['allocated_cidr']}")
    print(f"    Rede: {subnet['network_address']}, Broadcast: {subnet['broadcast_address']}")
    print(f"    Primeiro Host: {subnet['first_usable_host']}, Último Host: {subnet['last_usable_host']}, Hosts Utilizáveis: {subnet['num_usable_hosts']}")

# Exemplo 3: Departamentos que precisam de 0 hosts (links ponto a ponto)
main_ip_3 = '172.16.0.0'
main_cidr_3 = 24
departments_3 = [
    ('Link1', 0),
    ('Link2', 0),
    ('Users', 20)
]

vlsm_result_3 = perform_vlsm_calculation(main_ip_3, main_cidr_3, departments_3)
print(f"\n--- Resultados do Cálculo VLSM para {main_ip_3}/{main_cidr_3} ---")
for subnet in vlsm_result_3:
    print(f"  Departamento: {subnet['department']}, Hosts Solicitados: {subnet['requested_hosts']}, CIDR Alocado: /{subnet['allocated_cidr']}")
    print(f"    Rede: {subnet['network_address']}, Broadcast: {subnet['broadcast_address']}")
    print(f"    Primeiro Host: {subnet['first_usable_host']}, Último Host: {subnet['last_usable_host']}, Hosts Utilizáveis: {subnet['num_usable_hosts']}")
```
**Saída:**
```
--- VLSM Subnet Allocation ---

--- Resultados do Cálculo VLSM para 192.168.1.0/24 ---
  Departamento: Sales, Hosts Solicitados: 100, CIDR Alocado: /25
    Rede: 192.168.1.0, Broadcast: 192.168.1.127
    Primeiro Host: 192.168.1.1, Último Host: 192.168.1.126, Hosts Utilizáveis: 126
  Departamento: IT, Hosts Solicitados: 50, CIDR Alocado: /26
    Rede: 192.168.1.128, Broadcast: 192.168.1.191
    Primeiro Host: 192.168.1.129, Último Host: 192.168.1.190, Hosts Utilizáveis: 62
  Departamento: Guest_Wifi, Hosts Solicitados: 25, CIDR Alocado: /27
    Rede: 192.168.1.192, Broadcast: 192.168.1.223
    Primeiro Host: 192.168.1.193, Último Host: 192.168.1.222, Hosts Utilizáveis: 30
  Departamento: Servers, Hosts Solicitados: 10, CIDR Alocado: /28
    Rede: 192.168.1.224, Broadcast: 192.168.1.239
    Primeiro Host: 192.168.1.225, Último Host: 192.168.1.238, Hosts Utilizáveis: 14
  Departamento: Printers, Hosts Solicitados: 5, CIDR Alocado: /29
    Rede: 192.168.1.240, Broadcast: 192.168.1.247
    Primeiro Host: 192.168.1.241, Último Host: 192.168.1.246, Hosts Utilizáveis: 6
  Departamento: Management, Hosts Solicitados: 2, CIDR Alocado: /30
    Rede: 192.168.1.248, Broadcast: 192.168.1.251
    Primeiro Host: 192.168.1.249, Último Host: 192.168.1.250, Hosts Utilizáveis: 2

--- Resultados do Cálculo VLSM para 172.16.0.0/24 ---
  Departamento: Users, Hosts Solicitados: 20, CIDR Alocado: /27
    Rede: 172.16.0.0, Broadcast: 172.16.0.31
    Primeiro Host: 172.16.0.1, Último Host: 172.16.0.30, Hosts Utilizáveis: 30
  Departamento: Link1, Hosts Solicitados: 0, CIDR Alocado: /31
    Rede: 172.16.0.32, Broadcast: 172.16.0.33
    Primeiro Host: N/A, Último Host: N/A, Hosts Utilizáveis: 0
  Departamento: Link2, Hosts Solicitados: 0, CIDR Alocado: /31
    Rede: 172.16.0.34, Broadcast: 172.16.0.35
    Primeiro Host: N/A, Último Host: N/A, Hosts Utilizáveis: 0
```

## [Equações em Redes](./Equacoes/)
Códigos que possuem algorítmos de fórmulas e outros tipos de cálculos avulsos no contexto da matéria (Fourier, Hamming, Nyquist, Shannon, CheckSum, Convolutional, etc.)

## [Simulador de Redes de Computadores](./Simulador/)
Simulação de Redes, o que incluí criação de Dispositivos, Criação/Alteração de endereços e propriedades de Aparelhos, conexões entre eles e entre outros itens, para se assemelhar de forma mais próxima à vida real e, especialmente, a modelagem utilizada pelo Cisco Packet Tracer