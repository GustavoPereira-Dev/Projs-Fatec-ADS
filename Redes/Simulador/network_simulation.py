import sys
import time

# --- MANTENDO AS IMPORTAÇÕES ORIGINAIS ---
try:
    from network_interface import NetworkInterface
    from base_device import Device
    from computer import Computer
    from switch import Switch
    from router import Router
    from connection_manager import connect_interfaces
except ImportError as e:
    print("ERRO CRÍTICO: Não foi possível importar as classes necessárias.")
    print(f"Detalhe: {e}")
    print("Certifique-se de que os arquivos (computer.py, switch.py, etc.) estão na mesma pasta.")
    sys.exit(1)

# --- FUNÇÕES AUXILIARES (DO SEU EXEMPLO) ---
def decimal_to_binary_ip(decimal_ip):
    octets = decimal_ip.split('.')
    binary_octets = []
    for octet in octets:
        try:
            decimal_val = int(octet)
            if not (0 <= decimal_val <= 255):
                raise ValueError
        except ValueError:
            raise ValueError(f"Invalid decimal octet: {octet}")
        binary_octets.append(bin(decimal_val)[2:].zfill(8))
    return '.'.join(binary_octets)

# --- ESTADO GLOBAL DA APLICAÇÃO ---
devices = {}       # Armazena objetos {nome: instancia}
interfaces = {}    # Armazena objetos {mac: instancia}

# --- FUNÇÕES DO MENU ---

def create_device():
    print("\n--- Criar Dispositivo ---")
    print("1. Computador")
    print("2. Switch")
    print("3. Roteador")
    tipo = input("Escolha o tipo (1-3): ")
    name = input("Nome do dispositivo (ex: PC-A): ")

    if name in devices:
        print("Erro: Já existe um dispositivo com esse nome.")
        return

    if tipo == '1':
        devices[name] = Computer(name)
    elif tipo == '2':
        devices[name] = Switch(name)
    elif tipo == '3':
        devices[name] = Router(name)
    else:
        print("Opção inválida.")
        return
    print(f"Dispositivo '{name}' criado com sucesso.")

def add_interface():
    print("\n--- Adicionar Interface de Rede ---")
    if not devices:
        print("Nenhum dispositivo criado.")
        return

    print("Dispositivos disponíveis:", list(devices.keys()))
    dev_name = input("Nome do dispositivo para adicionar a interface: ")
    
    if dev_name not in devices:
        print("Dispositivo não encontrado.")
        return

    mac = input("Digite o endereço MAC (ex: 00:0A:00:00:00:01): ")
    if mac in interfaces:
        print("Erro: Este MAC já está em uso.")
        return

    # Criação e vinculação
    iface = NetworkInterface(mac)
    devices[dev_name].add_interface(iface)
    
    # Armazena referência global
    interfaces[mac] = iface 
    print(f"Interface {mac} adicionada ao {dev_name}.")

def configure_ip_address():
    print("\n--- Configurar Endereço IP ---")
    mac = input("Digite o MAC da interface que deseja configurar: ")
    
    if mac not in interfaces:
        print("Interface não encontrada.")
        return
    
    ip = input("Digite o IP (ex: 192.168.1.10): ")
    
    # Encontrar qual dispositivo possui essa interface
    target_dev = None
    for dev in devices.values():
        try:
            if dev.get_interface(mac):
                target_dev = dev
                break
        except:
            continue
            
    if target_dev:
        target_dev.configure_ip(mac, ip)
        print(f"IP {ip} configurado na interface {mac} do dispositivo {target_dev.name}.")
    else:
        print("Erro ao localizar dispositivo proprietário da interface.")

def connect_cables():
    print("\n--- Conectar Interfaces (Cabos) ---")
    mac1 = input("MAC da Interface 1: ")
    mac2 = input("MAC da Interface 2: ")

    if mac1 not in interfaces or mac2 not in interfaces:
        print("Uma ou ambas as interfaces não existem.")
        return

    try:
        connect_interfaces(interfaces[mac1], interfaces[mac2])
        print(f"Conexão estabelecida entre {mac1} e {mac2}.")
    except Exception as e:
        print(f"Erro ao conectar: {e}")

def add_routing_entry():
    print("\n--- Adicionar Rota (Apenas Roteadores) ---")
    print("Roteadores disponíveis:", [d for d in devices if isinstance(devices[d], Router)])
    r_name = input("Nome do Roteador: ")
    
    if r_name not in devices or not isinstance(devices[r_name], Router):
        print("Roteador inválido.")
        return

    network = input("Rede CIDR (ex: 192.168.1.0/24): ")
    rtype = input("Tipo (direct/static): ")
    # No exemplo original, o terceiro argumento era o MAC da interface de saída ou next-hop
    interface_or_gw = input("MAC da Interface de Saída ou IP do Next Hop: ")

    devices[r_name].add_route(network, rtype, interface_or_gw)
    print("Rota adicionada.")

def show_topology():
    print("\n--- Estado Atual da Topologia ---")
    for name, dev in devices.items():
        print(f"\n[Dispositivo: {name}] ({type(dev).__name__})")
        print(dev) # Usa o __str__ da classe

def run_simulation_step_by_step():
    """
    Recria a lógica de simulação do exemplo, mas permitindo inputs do usuário
    para definir quem envia e para onde.
    """
    print("\n--- Simulação Passo a Passo (Ping) ---")
    print("Nota: Esta simulação segue a lógica manual do exemplo fornecido.")
    
    # 1. Configurar Origem
    src_name = input("Nome do Computador de Origem (PC-A): ")
    if src_name not in devices: return
    src_dev = devices[src_name]
    
    src_mac = input(f"MAC de origem de {src_name}: ")
    
    # 2. Configurar Destino Final
    dst_ip = input("IP de Destino Final: ")
    msg = input("Mensagem do Payload: ")

    # Passo A: Send Ping
    print(f"\n[PASSO 1] {src_name} gerando pacote...")
    src_dev.send_ping(src_mac, dst_ip, msg)
    
    # Passo B: Simulação do Switch (Intermediário)
    # No exemplo original, o usuário manually invocou o switch. Faremos o mesmo.
    print("\n--- Encaminhamento Manual (Camada 2) ---")
    sw_name = input("Nome do Switch conectado (ou Enter para pular): ")
    if sw_name in devices:
        sw = devices[sw_name]
        sw_in_mac = input(f"Em qual porta (MAC) do {sw_name} o pacote entrou?: ")
        dst_mac_frame = input(f"Para qual MAC de destino o quadro está indo? (ex: Gateway MAC): ")
        
        if sw_in_mac in interfaces:
             sw.receive_frame(interfaces[sw_in_mac], src_mac, dst_mac_frame, f"Packet to {dst_ip}")
    
    # Passo C: Simulação do Roteador (Gateway)
    print("\n--- Encaminhamento Manual (Camada 3) ---")
    r_name = input("Nome do Roteador Gateway (ou Enter para pular): ")
    if r_name in devices:
        router = devices[r_name]
        r_in_mac = input(f"Em qual interface (MAC) do {r_name} o pacote chegou?: ")
        
        # Recuperar IP de origem da interface usada
        src_ip = interfaces[src_mac].ip_address if src_mac in interfaces else "0.0.0.0"

        if r_in_mac in interfaces:
            router.receive_packet(interfaces[r_in_mac], src_ip, dst_ip, "Ping Request")

# --- LOOP PRINCIPAL ---

def main_menu():
    while True:
        print("\n" + "="*30)
        print("SIMULADOR DE REDE - MENU")
        print("="*30)
        print("1. Criar Dispositivo (PC/Switch/Router)")
        print("2. Adicionar Interface a Dispositivo")
        print("3. Configurar IP na Interface")
        print("4. Conectar Cabos (Link Interfaces)")
        print("5. Adicionar Rota (Router)")
        print("6. Mostrar Topologia")
        print("7. Simular Ping (Passo-a-Passo)")
        print("0. Sair")
        
        opt = input("Selecione: ")

        if opt == '1': create_device()
        elif opt == '2': add_interface()
        elif opt == '3': configure_ip_address()
        elif opt == '4': connect_cables()
        elif opt == '5': add_routing_entry()
        elif opt == '6': show_topology()
        elif opt == '7': run_simulation_step_by_step()
        elif opt == '0': 
            print("Encerrando...")
            break
        else:
            print("Opção inválida.")

if __name__ == "__main__":
    main_menu()