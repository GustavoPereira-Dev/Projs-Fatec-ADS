from base_device import Device
from network_interface import NetworkInterface

class Server(Device):
    """
    Representa um servidor de rede.
    Herda da classe Device e adiciona funcionalidades específicas de servidor,
    como a capacidade de hospedar serviços.
    """
    def __init__(self, name):
        """
        Inicializa uma nova instância de Server.

        Args:
            name (str): O nome do servidor.
        """
        super().__init__(name) # Chama o construtor da classe base Device
        self.services = {} # Dicionário para armazenar serviços {port: service_name}

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
            print(f"[Server:{self.name}] Interface {mac_address} configurada com IP: {ip_address}")
        else:
            raise ValueError(f"Interface com MAC {mac_address} não encontrada no servidor {self.name}.")

    def start_service(self, service_name, port):
        """
        Simula o início de um serviço em uma porta específica.

        Args:
            service_name (str): O nome do serviço (ex: 'Web Server', 'DNS Server').
            port (int): A porta em que o serviço está escutando (ex: 80, 53).
        """
        if not isinstance(port, int) or not (0 < port <= 65535):
            raise ValueError("A porta deve ser um número inteiro válido entre 1 e 65535.")
        if port in self.services:
            print(f"[Server:{self.name}] Aviso: Serviço já está rodando na porta {port}.")
        else:
            self.services[port] = service_name
            print(f"[Server:{self.name}] Serviço '{service_name}' iniciado na porta {port}.")

    def receive_request(self, destination_ip, port, data):
        """
        Simula o recebimento de uma requisição para um serviço neste servidor.

        Args:
            destination_ip (str): O endereço IP de destino da requisição (deve ser um dos IPs do servidor).
            port (int): A porta de destino da requisição.
            data (str): Os dados da requisição.
        """
        # Verificar se o IP de destino pertence a uma das interfaces do servidor
        is_server_ip = False
        for iface in self.interfaces:
            if iface.ip_address == destination_ip:
                is_server_ip = True
                break

        if not is_server_ip:
            print(f"[Server:{self.name}] Requisição para {destination_ip}:{port} ignorada: IP não pertence a este servidor.")
            return

        # Verificar se o serviço está rodando na porta especificada
        if port in self.services:
            service_name = self.services[port]
            print(f"[Server:{self.name}] Requisição recebida para o serviço '{service_name}' na porta {port} com dados: '{data}'. Processando...")
            # Em um cenário real, o serviço processaria os dados e enviaria uma resposta.
        else:
            print(f"[Server:{self.name}] Requisição recebida para {destination_ip}:{port} ignorada: Nenhum serviço rodando nesta porta.")
