# Redes

## Códigos

### resolvegoogle.cpp

Coletar endereço IPv4 do endereço do site usando a URL também exibidNo o alias

<code>./resolvergoogle</code>

### resolvegoogleipv4.cpp

Coletar endereço IPv4 do endereço do site usando a URL de forma resumida

<code>./resolvergoogle</code>


### testport.cpp

Testador de portas que avalia se uma porta está aberta ou fechada

<code>./testport</code>

### getcurl.cpp

Como usar o cURL no seu código C++, da mesma forma do wget e cURL

### postjson.cpp

Como enviar dados para um servidor por meio do JSON

<code>./postjson</code>

### getcurl.cpp

Uso da libcurl para abrir um arquivo local e gravar os dados via curl para o aberto

<code>./getcurl</code>

## Atividades

### Redes

1. Nesta prática o aluno deve configurar uma máquina Virtual com um adaptador em modo NAT com IP estático em sua rede.
- Configure o Adaptador de Rede para modo NAT;
- Ligue a Virtual Machine;
- Edite o arquivo /etc/network/interfaces:
- Endereço: 10.0.2.3;
- Máscara: 24 bits;
- Gateway: 10.0.2.2;
- DNS server: 8.8.8.8;

```
sudo nano /etc/network/interfaces
```

Adicione isso no final do arquivo:

```
auto enp0s3
iface enp0s3 inet static
    address 10.0.2.3
    netmask 255.255.255.0
    gateway 10.0.2.2
    dns-nameservers 8.8.8.8 (no documento escreva isso)
```

```
ip link show

ip a
ping 8.8.8.8
ping www.google.com
```

2. Nesta prática o aluno deve configurar uma máquina Virtual com um adaptador em modo NAT com IP estático em sua rede.
- Configure o Adaptador de Rede para modo NAT;
- Ligue a Virtual Machine;
- Apague os campos abaixo da interface enp0s3:
  - address
  - netmask
  - network
  - broadcast
  - gareway
  - dns-nameservers
- Configure a interface enp0s3 para usar auto dhcp;
- Reinicie a máquina virtual;
- Configure por comando IP:
  - Endereço: 10.0.2.3;
  - Máscara: 24 bits;
  - Gateway: 10.0.2.2;
  - DNS server: 8.8.8.8;

```
sudo nano /etc/network/interfaces
```

Remova ou comente as linhas:
- address
- netmask
- network
- broadcast
- gateway
- dns-nameservers


Substitua por:

```
auto enp0s3
iface enp0s3 inet dhcp

```

```
sudo reboot

sudo ip addr flush dev enp0s3
sudo ip addr add 10.0.2.3/24 dev enp0s3
sudo ip route add default via 10.0.2.2
sudo nano /etc/resolv.conf
nameserver 8.8.8.8

ip a show enp0s3
ping 8.8.8.8
ping www.google.com

```
3. Nesta prática o aluno deve baixar um arquivo com wget e salvar em /tmp
- Faça download do arquivo http://www.aied.com.br/linux/download/install.py com wget, salve o arquivo com o nome /tmp/install.py

wget -O /tmp/install.py http://www.aied.com.br/linux/download/install.py

```
ls -l /tmp/install.py
cat /tmp/install.py
```