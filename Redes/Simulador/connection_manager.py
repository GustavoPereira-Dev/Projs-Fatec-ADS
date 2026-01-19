from network_interface import NetworkInterface

def connect_interfaces(interface1, interface2):
    """
    Conecta logicamente duas interfaces de rede.
    Simula um 'cabo' que conecta dois dispositivos, garantindo que as interfaces
    estejam cientes de suas conexões.

    Args:
        interface1 (NetworkInterface): A primeira interface a ser conectada.
        interface2 (NetworkInterface): A segunda interface a ser conectada.

    Raises:
        ValueError: Se algum dos argumentos não for uma instância de NetworkInterface.
    """
    if not isinstance(interface1, NetworkInterface) or not isinstance(interface2, NetworkInterface):
        raise ValueError("Ambos os argumentos devem ser instâncias de NetworkInterface.")

    interface1.is_connected = True
    interface2.is_connected = True
    interface1.connected_to = interface2
    interface2.connected_to = interface1

    print(f"Interfaces {interface1.mac_address} e {interface2.mac_address} conectadas com sucesso.")

# Exemplo de uso dentro do connection_manager.py
if __name__ == '__main__':
    print("--- Exemplo de Uso de ConnectionManager ---")
    
    # Criando instâncias de NetworkInterface
    iface_a = NetworkInterface(mac_address='AA:BB:CC:DD:EE:F0', ip_address='192.168.1.10')
    iface_b = NetworkInterface(mac_address='AA:BB:CC:DD:EE:F1', ip_address='192.168.1.20')
    
    print(f"Antes da conexão: iface_a.is_connected={iface_a.is_connected}, iface_b.is_connected={iface_b.is_connected}")
    print(f"Antes da conexão: iface_a.connected_to={iface_a.connected_to}, iface_b.connected_to={iface_b.connected_to}")

    # Conectando as interfaces
    connect_interfaces(iface_a, iface_b)

    print(f"Depois da conexão: iface_a.is_connected={iface_a.is_connected}, iface_b.is_connected={iface_b.is_connected}")
    print(f"Depois da conexão: iface_a.connected_to.mac_address={iface_a.connected_to.mac_address if iface_a.connected_to else None}, iface_b.connected_to.mac_address={iface_b.connected_to.mac_address if iface_b.connected_to else None}")

    # Exemplo de tentativa de conexão com tipo inválido
    try:
        connect_interfaces(iface_a, "not_an_interface")
    except ValueError as e:
        print(f"Erro esperado ao tentar conectar com tipo inválido: {e}")