import math
from base_device import Device
from network_interface import NetworkInterface

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

def is_ip_in_cidr(ip_address, cidr_network):
    """
    Checks if an IP address is within a given CIDR network.
    """
    try:
        ip_parts = ip_address.split('.')
        ip_int = int(decimal_to_binary_ip(ip_address).replace('.', ''), 2)

        network_parts = cidr_network.split('/')
        network_ip = network_parts[0]
        cidr_prefix = int(network_parts[1])

        network_ip_int = int(decimal_to_binary_ip(network_ip).replace('.', ''), 2)

        mask = (0xFFFFFFFF << (32 - cidr_prefix)) & 0xFFFFFFFF

        return (ip_int & mask) == (network_ip_int & mask)
    except Exception as e:
        # print(f"Error checking IP in CIDR: {e}")
        return False

class Router(Device):
    """
    Representa um roteador de rede de Camada 3.
    Gerencia múltiplas interfaces e uma tabela de roteamento para encaminhar pacotes IP.
    """
    def __init__(self, name):
        """
        Inicializa uma nova instância de Router.

        Args:
            name (str): O nome do roteador.
        """
        super().__init__(name) # Chama o construtor da classe base Device
        self.routing_table = {} # Dicionário para armazenar rotas {destination_network: {'next_hop_ip': ip, 'output_interface_mac': mac}}

    def add_route(self, destination_network, next_hop_ip, output_interface_mac):
        """
        Adiciona uma rota à tabela de roteamento do roteador.

        Args:
            destination_network (str): A rede de destino no formato CIDR (ex: '192.168.2.0/24').
            next_hop_ip (str): O endereço IP do próximo salto. Pode ser 'direct' para redes conectadas diretamente.
            output_interface_mac (str): O MAC address da interface de saída que o roteador deve usar.
        """
        if not isinstance(destination_network, str) or '/' not in destination_network:
            raise ValueError("Destination network must be in CIDR format (e.g., '192.168.2.0/24').")
        if not isinstance(next_hop_ip, str):
            raise ValueError("Next hop IP must be a string.")
        if not isinstance(output_interface_mac, str):
            raise ValueError("Output interface MAC must be a string.")

        # Basic validation for CIDR prefix
        try:
            ip_part, cidr_part = destination_network.split('/')
            cidr_prefix = int(cidr_part)
            if not (0 <= cidr_prefix <= 32):
                raise ValueError("CIDR prefix must be between 0 and 32.")
            # Validate IP part
            decimal_to_binary_ip(ip_part) # This will raise ValueError if IP is malformed
        except ValueError as e:
            raise ValueError(f"Invalid destination network format: {e}")

        self.routing_table[destination_network] = {
            'next_hop_ip': next_hop_ip,
            'output_interface_mac': output_interface_mac
        }
        print(f"[Router:{self.name}] Rota adicionada: {destination_network} via {next_hop_ip} pela interface {output_interface_mac}")

    def receive_packet(self, receiving_interface, source_ip, destination_ip, payload):
        """
        Simula o recebimento de um pacote IP em uma interface do roteador.

        Args:
            receiving_interface (NetworkInterface): A interface onde o pacote foi recebido.
            source_ip (str): O endereço IP de origem do pacote.
            destination_ip (str): O endereço IP de destino do pacote.
            payload (str): O conteúdo dos dados do pacote.
        """
        print(f"[Router:{self.name}] Pacote recebido na interface {receiving_interface.mac_address} de {source_ip} para {destination_ip} com conteúdo: '{payload}'")

        # 1. Verificar se o destination_ip é local (diretamente conectado ou o próprio roteador)
        is_local_destination = False
        for iface in self.interfaces:
            # Check if destination_ip is the IP of this interface
            if iface.ip_address == destination_ip:
                is_local_destination = True
                break
            # Check if destination_ip is in the same subnet as this interface
            if iface.ip_address and '/' in self.routing_table.get('direct_connect_placeholder', '0.0.0.0/0'): # Placeholder for directly connected network detection
                # For simplicity, assume directly connected networks correspond to the interface's IP with a /24
                # A more robust solution would require storing CIDR for each interface.
                # Here, we'll check against a common subnet like /24 around the interface's IP
                # This is a simplification; a real router knows its interface's network/mask.
                # Let's assume for direct connection check, we use the interface's IP as network ID and a /24 or something suitable.
                # A more accurate way: retrieve the subnet mask or CIDR for `iface.ip_address`
                # For now, let's assume we have `main_ip` and `main_cidr` for the directly connected interface if it was set.
                
                # Instead, check if the routing table has a direct route that matches this interface and destination IP
                for network_route, route_info in self.routing_table.items():
                    if route_info['output_interface_mac'] == iface.mac_address and route_info['next_hop_ip'] == 'direct':
                        if is_ip_in_cidr(destination_ip, network_route):
                            is_local_destination = True
                            break
                if is_local_destination:
                    break

        if is_local_destination:
            print(f"[Router:{self.name}] Pacote para {destination_ip} é local ou para o próprio roteador. Processando...")
            # In a real scenario, the packet would be processed by the router's OS or passed to the local host.
            return

        # 2. Encaminhar o pacote (procurar na tabela de roteamento)
        best_route = None
        longest_match_prefix = -1

        for network_route, route_info in self.routing_table.items():
            try:
                network_prefix = int(network_route.split('/')[1])
                if is_ip_in_cidr(destination_ip, network_route):
                    if network_prefix > longest_match_prefix:
                        longest_match_prefix = network_prefix
                        best_route = route_info
            except Exception:
                # Ignore malformed routes in table for this check
                pass

        if best_route:
            output_interface = self.get_interface(best_route['output_interface_mac'])
            if output_interface:
                print(f"[Router:{self.name}] Encaminhando pacote para {destination_ip} via {best_route['next_hop_ip']} pela interface {output_interface.mac_address}")
                # In a real environment, the packet would be sent out this interface.
            else:
                print(f"[Router:{self.name}] Erro: Interface de saída {best_route['output_interface_mac']} não encontrada para encaminhamento de {destination_ip}. Descartando pacote.")
        else:
            print(f"[Router:{self.name}] Destino {destination_ip} inalcançável. Nenhum rota encontrada. Descartando pacote.")