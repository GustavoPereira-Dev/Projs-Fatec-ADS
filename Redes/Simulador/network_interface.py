class NetworkInterface:
    """
    Representa uma interface de rede de um dispositivo.
    Contém informações como endereço MAC, endereço IP, status de conexão
    e uma referência à interface conectada.
    """
    def __init__(self, mac_address, ip_address=None, is_connected=False, connected_to=None):
        """
        Inicializa uma nova instância de NetworkInterface.

        Args:
            mac_address (str): O endereço MAC da interface (ex: '00:1A:2B:3C:4D:5E').
            ip_address (str, optional): O endereço IP da interface (ex: '192.168.1.1').
                                        Pode ser None se a interface não tiver um IP atribuído.
            is_connected (bool, optional): Indica se a interface está fisicamente conectada a outra interface.
                                           Padrão para False.
            connected_to (NetworkInterface, optional): Uma referência à outra NetworkInterface à qual esta
                                                       interface está conectada. Padrão para None.
        """
        if not isinstance(mac_address, str) or not mac_address:
            raise ValueError("MAC address must be a non-empty string.")

        self.mac_address = mac_address
        self.ip_address = ip_address
        self.is_connected = is_connected
        self.connected_to = connected_to

    def __str__(self):
        return f"MAC: {self.mac_address}, IP: {self.ip_address}, Connected: {self.is_connected}"

    def __repr__(self):
        return f"NetworkInterface(mac_address='{self.mac_address}', ip_address='{self.ip_address}', is_connected={self.is_connected})"