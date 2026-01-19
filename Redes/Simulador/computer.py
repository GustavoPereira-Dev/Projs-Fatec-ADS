from base_device import Device
from network_interface import NetworkInterface

class Computer(Device):
    """
    Representa um computador, um tipo de dispositivo de usuário final.
    Herda da classe Device e adiciona funcionalidades específicas de computador.
    """
    def __init__(self, name):
        """
        Inicializa uma nova instância de Computer.

        Args:
            name (str): O nome do computador.
        """
        super().__init__(name) # Chama o construtor da classe base Device

    def configure_ip(self, mac_address, ip_address):
        """
        Configura o endereço IP para a interface correspondente ao MAC address.

        Args:
            mac_address (str): O endereço MAC da interface a ser configurada.
            ip_address (str): O endereço IP a ser atribuído à interface.

        Raises:
            ValueError: Se a interface com o MAC address fornecido não for encontrada.
        """
        interface = self.get_interface(mac_address)
        if interface:
            interface.ip_address = ip_address
            print(f"[Computer:{self.name}] Interface {mac_address} configurada com IP: {ip_address}")
        else:
            raise ValueError(f"Interface com MAC {mac_address} não encontrada no computador {self.name}.")

    def send_ping(self, source_interface_mac, destination_ip, message):
        """
        Simula o envio de uma mensagem (ping) de uma interface específica.

        Args:
            source_interface_mac (str): O endereço MAC da interface de origem.
            destination_ip (str): O endereço IP de destino da mensagem.
            message (str): A mensagem a ser enviada.
        
        Raises:
            ValueError: Se a interface de origem não for encontrada ou não tiver um IP configurado.
        """
        source_interface = self.get_interface(source_interface_mac)
        if not source_interface:
            raise ValueError(f"Interface de origem com MAC {source_interface_mac} não encontrada no computador {self.name}.")
        if not source_interface.ip_address:
            raise ValueError(f"Interface de origem {source_interface_mac} no {self.name} não tem um endereço IP configurado.")

        print(f"[Computer:{self.name}] Enviando 'PING' de {source_interface.ip_address} ({source_interface_mac}) para {destination_ip} com mensagem: '{message}'")