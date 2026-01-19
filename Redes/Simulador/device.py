from network_interface import NetworkInterface

class Device:
    """
    Representa um dispositivo de rede base (computador, roteador, switch).
    Gerencia as interfaces de rede do dispositivo.
    """
    def __init__(self, name):
        """
        Inicializa uma nova instância de Device.

        Args:
            name (str): O nome do dispositivo.
        """
        if not isinstance(name, str) or not name:
            raise ValueError("Device name must be a non-empty string.")

        self.name = name
        self.interfaces = [] # Lista para armazenar objetos NetworkInterface

    def add_interface(self, interface):
        """
        Adiciona uma interface de rede ao dispositivo.

        Args:
            interface (NetworkInterface): O objeto NetworkInterface a ser adicionado.
        Raises:
            ValueError: Se o argumento não for uma instância de NetworkInterface.
        """
        if not isinstance(interface, NetworkInterface):
            raise ValueError("Argument must be an instance of NetworkInterface.")
        self.interfaces.append(interface)

    def get_interface(self, lookup_value):
        """
        Retorna um objeto NetworkInterface que corresponde ao valor fornecido
        (MAC address ou IP address).

        Args:
            lookup_value (str): O endereço MAC ou IP da interface a ser encontrada.

        Returns:
            NetworkInterface: O objeto NetworkInterface correspondente, ou None se não for encontrado.
        """
        for interface in self.interfaces:
            if interface.mac_address == lookup_value or interface.ip_address == lookup_value:
                return interface
        return None

    def __str__(self):
        return f"Device Name: {self.name}, Interfaces: {len(self.interfaces)}"

    def __repr__(self):
        interface_reprs = ", ".join([repr(iface) for iface in self.interfaces])
        return f"Device(name='{self.name}', interfaces=[{interface_reprs}])"