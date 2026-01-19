from base_device import Device
from network_interface import NetworkInterface

class Switch(Device):
    """
    Representa um switch de rede de Camada 2.
    Gerencia mùltiplas interfaces, aprende endereços MAC e encaminha quadros.
    """
    def __init__(self, name):
        """
        Inicializa uma nova instância de Switch.

        Args:
            name (str): O nome do switch.
        """
        super().__init__(name) # Chama o construtor da classe base Device
        self.mac_table = {} # Tabela para armazenar mapeamentos MAC -> NetworkInterface

    def receive_frame(self, receiving_interface, source_mac, destination_mac, payload):
        """
        Simula o recebimento de um quadro em uma interface do switch.

        Args:
            receiving_interface (NetworkInterface): A interface onde o quadro foi recebido.
            source_mac (str): O endereço MAC de origem do quadro.
            destination_mac (str): O endereço MAC de destino do quadro.
            payload (str): O conteúdo dos dados do quadro.
        """
        print(f"[Switch:{self.name}] Quadro recebido na interface {receiving_interface.mac_address} de {source_mac} para {destination_mac}")

        # 1. Aprender endereço MAC de origem
        if source_mac not in self.mac_table:
            self.mac_table[source_mac] = receiving_interface
            print(f"[Switch:{self.name}] MAC {source_mac} aprendido na interface {receiving_interface.mac_address}")

        # 2. Encaminhar o quadro
        if destination_mac in self.mac_table:
            # Destino conhecido, encaminha para a interface específica
            forwarding_interface = self.mac_table[destination_mac]
            if forwarding_interface == receiving_interface:
                print(f"[Switch:{self.name}] Destino {destination_mac} está na mesma interface ({forwarding_interface.mac_address}), descartando o quadro.")
            else:
                print(f"[Switch:{self.name}] Encaminhando quadro para {destination_mac} através da interface {forwarding_interface.mac_address}")
                # Em um ambiente real, chamaríamos um método 'send_frame' na forwarding_interface
        elif destination_mac.upper() == 'FF:FF:FF:FF:FF:FF' or destination_mac.lower() == 'broadcast':
            # Endereço de broadcast, inundar para todas as interfaces exceto a de origem
            print(f"[Switch:{self.name}] Endereço de broadcast ({destination_mac}) - Inundando quadro para todas as interfaces (exceto {receiving_interface.mac_address})")
            for iface in self.interfaces:
                if iface != receiving_interface:
                    print(f"  -> Inundando para interface {iface.mac_address}")
        else:
            # Destino desconhecido, inundar para todas as interfaces exceto a de origem
            print(f"[Switch:{self.name}] Destino desconhecido ({destination_mac}) - Inundando quadro para todas as interfaces (exceto {receiving_interface.mac_address})")
            for iface in self.interfaces:
                if iface != receiving_interface:
                    print(f"  -> Inundando para interface {iface.mac_address}")