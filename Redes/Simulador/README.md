# README: Simulação de Rede Lúdica em Python

Este projeto implementa uma simulação básica de rede em Python, permitindo a criação e interação de diferentes dispositivos de rede, como computadores, switches e roteadores. O objetivo é fornecer uma representação lúdica e didática de como os pacotes de dados fluem em uma rede, similar ao ambiente do Cisco Packet Tracer.

## Estrutura do Projeto

O projeto é modular, com cada componente principal sendo definido em um arquivo Python separado:

### `network_interface.py`

Este arquivo define a classe `NetworkInterface`, que representa a "porta" de conexão de um dispositivo de rede. Cada interface possui um endereço MAC, um endereço IP (opcional), um status de conexão e uma referência à interface à qual está conectada.

**Funcionalidades:**
- Atributos para `mac_address`, `ip_address`, `is_connected` e `connected_to`.
- Validação básica para o `mac_address`.

**Exemplo de Uso Conceitual:**
```python
# iface = NetworkInterface(mac_address='00:11:22:33:44:55', ip_address='192.168.1.10')
```

### `base_device.py`

Este arquivo contém a classe base `Device`, da qual todos os outros tipos de dispositivos de rede (computadores, switches, roteadores, servidores) herdam. Ela fornece a estrutura fundamental para gerenciar interfaces de rede.

**Funcionalidades:**
- Inicialização com um `name` para o dispositivo.
- Método `add_interface()` para adicionar objetos `NetworkInterface`.
- Método `get_interface()` para recuperar uma interface pelo seu MAC ou IP.

**Exemplo de Uso Conceitual:**
```python
# my_device = Device("MeuDispositivo")
# iface = NetworkInterface(mac_address='00:11:22:33:44:55')
# my_device.add_interface(iface)
```

### `computer.py`

Implementa a classe `Computer`, que herda de `Device`. Representa um dispositivo de usuário final e possui funcionalidades para configurar IPs e simular o envio de mensagens.

**Funcionalidades:**
- `configure_ip(mac_address, ip_address)`: Atribui um endereço IP a uma interface específica.
- `send_ping(source_interface_mac, destination_ip, message)`: Simula o envio de um pacote ICMP (ping) para um endereço IP de destino a partir de uma interface de origem.

**Exemplo de Uso Conceitual:**
```python
# pc_a = Computer("PC-A")
# pc_a.add_interface(NetworkInterface(mac_address='00:AA:00:00:00:01'))
# pc_a.configure_ip('00:AA:00:00:00:01', '192.168.1.10')
# pc_a.send_ping('00:AA:00:00:00:01', '192.168.1.20', 'Hello!')
```

### `server.py`

Implementa a classe `Server`, que herda de `Device`. Representa um servidor de rede, capaz de hospedar serviços em portas específicas e simular o recebimento de requisições.

**Funcionalidades:**
- `configure_ip(mac_address, ip_address)`: Configura um IP para uma interface do servidor.
- `start_service(service_name, port)`: Inicia um serviço (ex: "Web Server") em uma porta designada.
- `receive_request(destination_ip, port, data)`: Simula o recebimento de uma requisição para um serviço, verificando o IP e a porta de destino.

**Exemplo de Uso Conceitual:**
```python
# web_server = Server("WebServer")
# web_server.add_interface(NetworkInterface(mac_address='00:BB:00:00:00:01'))
# web_server.configure_ip('00:BB:00:00:00:01', '192.168.1.100')
# web_server.start_service('Web Service', 80)
# web_server.receive_request('192.168.1.100', 80, 'GET /index.html')
```

### `switch.py`

Implementa a classe `Switch`, que herda de `Device`. Simula um dispositivo de Camada 2, responsável por aprender endereços MAC e encaminhar quadros (frames) entre as interfaces conectadas.

**Funcionalidades:**
- `mac_table`: Tabela interna para mapear MACs para interfaces.
- `receive_frame(receiving_interface, source_mac, destination_mac, payload)`: Aprende o `source_mac`, e encaminha o quadro para a `destination_mac` se conhecida, ou inunda (flood) para todas as outras interfaces se desconhecida ou broadcast.

**Exemplo de Uso Conceitual:**
```python
# sw1 = Switch("Switch-1")
# sw1.add_interface(NetworkInterface('00:S1:00:00:00:01'))
# sw1.receive_frame(iface_p1, '00:AA:00:00:00:01', '00:BB:00:00:00:02', 'Data')
```

### `router.py`

Implementa a classe `Router`, que herda de `Device`. Simula um dispositivo de Camada 3, responsável por gerenciar múltiplas interfaces e uma tabela de roteamento para encaminhar pacotes IP entre diferentes redes.

**Funcionalidades:**
- `routing_table`: Dicionário para armazenar rotas (`destination_network` -> `next_hop_ip`, `output_interface_mac`).
- `add_route(destination_network, next_hop_ip, output_interface_mac)`: Adiciona uma entrada à tabela de roteamento.
- `receive_packet(receiving_interface, source_ip, destination_ip, payload)`: Processa pacotes IP, decide se o destino é local ou procura a melhor rota na tabela de roteamento (usando o algoritmo de *longest prefix match*).
- Funções auxiliares como `decimal_to_binary_ip`, `_long_bin_to_dotted_decimal` e `is_ip_in_cidr` para manipulação de IP e CIDR.

**Exemplo de Uso Conceitual:**
```python
# r1 = Router("Router-1")
# r1.add_route('192.168.1.0/24', 'direct', '00:R1:00:00:00:01')
# r1.receive_packet(iface_r1_lan1, '192.168.1.10', '192.168.2.10', 'Ping Request')
```

### `connection_manager.py`

Este arquivo contém a função utilitária `connect_interfaces()`, que estabelece uma conexão lógica entre duas `NetworkInterface`s. Isso simula um cabo físico, atualizando o status de conexão de ambas as interfaces e suas referências mútuas.

**Funcionalidades:**
- `connect_interfaces(interface1, interface2)`: Conecta logicamente duas interfaces, definindo `is_connected` como `True` e `connected_to` como a outra interface.

**Exemplo de Uso Conceitual:**
```python
# iface1 = NetworkInterface('AA:BB:CC:DD:EE:F0')
# iface2 = NetworkInterface('AA:BB:CC:DD:EE:F1')
# connect_interfaces(iface1, iface2)
```

### `network_simulation.py`

Este é o script principal que orquestra toda a simulação. Ele importa todas as classes e funções desenvolvidas, instancia os dispositivos, configura suas interfaces e endereços IP, estabelece as conexões e demonstra um fluxo de pacotes.

**Funcionalidades:**
- Cria instâncias de `Computer`, `Switch`, `Router`.
- Adiciona interfaces a cada dispositivo.
- Configura endereços IP nas interfaces.
- Utiliza `connect_interfaces` para definir a topologia da rede.
- Configura a tabela de roteamento no `Router`.
- Simula um cenário de 'ping' de um `Computer` para outro, demonstrando o tráfego passando por um `Switch` e um `Router`.
- Funções auxiliares de IP/CIDR do `router.py` são incluídas para autossuficiência do script principal.

**Exemplo de Fluxo (PC-A para PC-B via Switch e Router):**
1. `PC-A` inicia um `ping` para `PC-B` (que está em outra rede).
2. `PC-A` encaminha o pacote para seu gateway (`Router-1`). O quadro (frame) sai da interface de `PC-A`.
3. O quadro chega ao `Switch-1`, que aprende o MAC de `PC-A` e encaminha o quadro para o `Router-1` (MAC de destino é o do gateway).
4. O `Router-1` recebe o pacote IP, verifica seu destino (`PC-B`), consulta sua tabela de roteamento e encaminha o pacote para a rede de `PC-B` pela interface apropriada.
5. `PC-B` recebe o pacote IP do `Router-1`.

---



### Guia Passo a Passo (Cenário Exemplo)
Para reproduzir uma topologia clássica (PC conectado a um Roteador através de um Switch), siga a sequência numérica do menu:

1. Criar Dispositivos (Opção 1)
- Crie os três elementos essenciais:
  - Tipo 1 (Computador) -> Nome: PC-A
  - Tipo 2 (Switch) -> Nome: Switch-1
  - Tipo 3 (Roteador) -> Nome: Router-1

2. Adicionar Interfaces (Opção 2)
- Adicione as "placas de rede" digitando os MACs manualmente:
  - Em PC-A: 00:0A:00:00:00:01
  - Em Switch-1: 00:S1:00:00:00:01 (Porta p/ PC)
  - Em Switch-1: 00:S1:00:00:00:02 (Porta p/ Router)
  - Em Router-1: 00:R1:00:00:00:01 (Gateway)

3. Conectar Cabos (Opção 4)
- Faça as ligações físicas (Link Layer):
  - Conecte 00:0A:00:00:00:01 com 00:S1:00:00:00:01
  - Conecte 00:R1:00:00:00:01 com 00:S1:00:00:00:02

4. Configurar IPs (Opção 3)
- Configure a lógica lógica de rede:
  - Interface do PC-A: 192.168.1.10
  - Interface do Router-1: 192.168.1.1

5. Configurar Rotas (Opção 5)
- Ensine o roteador a alcançar a rede:
  - Nome: Router-1
  - Rede: 192.168.1.0/24
  - Tipo: direct
  - Interface: 00:R1:00:00:00:01

6. Simular Ping (Opção 7)
- Execute a simulação. Note que ela é semi-manual para fins didáticos:
  - Defina a origem: PC-A (MAC 00:0A...).
  - Defina o IP destino: 192.168.1.1.
  - O sistema gerará o pacote.
    - O sistema perguntará: "Em qual switch o pacote entrou?" -> Indique Switch-1.
    - O sistema perguntará: "Em qual roteador o pacote chegou?" -> Indique Router-1.
Isso permite acompanhar logs detalhados de como cada dispositivo processa o cabeçalho do pacote.