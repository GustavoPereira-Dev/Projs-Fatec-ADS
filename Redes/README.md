# Redes

## IPV4.py

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