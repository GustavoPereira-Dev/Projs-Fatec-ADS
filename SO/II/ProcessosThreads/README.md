# Processos e Threads

## Códigos

### myblkid.cpp

Obter o UUID do dispositivo de bloco

<code>sudo ./myblkid</code>

### thread.cpp

Criação e finalização de thread

<code>./thread</code>

### userfork.cpp

Uso de fork (um processo pai vai criar um processo filho, e cada processo irá escrever uma frase no output)

<code>./fork</code>

### usewait.cpp

Uso do wait em processos pais e filhos

<code>./usewait</code>

### usewait_exit.cpp

Uso do wait e exit em processos pais e filhos

<code>./usewait</code>

### waitpid.cpp

Uso do waitpid para um processo pai aguardar um grupo de processos filhos passando como parâmetro o PID do processo filho

<code>./waitpid</code>

### system.cpp

Executa um comando shell do sistema operacional conforme descrito por uma string contendo o comando literal.

<code>./system</code>

### pop.cpp

Uso do popopen (abre um processo criando e criando um pipe de comunicação (um arquivo tipo s dentro do sistema operacional), o fork() então executa o shell, que permite obter o output do comando executado)

<code>./pop</code>

### receivesignal.cpp

Faz o uso e captura de um signal

<code>./receivesignal</code>

## Atividades


### Processos

1. Nesta prática o aluno deve ter a expertise para saber localizar processos python;
- execute o comando cd ~/
- Digite o comando sudo locale-gen "en_US.UTF-8"
- Execute o comando script
- Com o comando PS liste os processos e filtre por python;
- Execute o comando exit

```
cd ~
sudo locale-gen "en_US.UTF-8"
script
ps aux | grep python
exit

cat typescript

```


### Instalação de Programas e Pacotes

1. Nesta prática o aluno deverá criar seu próprio pacote .deb e instalar no GNU/Linux utilizando dpkg, este pacote terá um programa escrito em C que retorna uma mensagem.
- Criar em /home/userlinux/ o diretório pacotes;
- Criar em /home/userlinux/pacotes/ o diretório aluno_0_0_amd64;
- Criar em /home/userlinux/pacotes/aluno_0_0_amd64/ os diretórios DEBIAN e usr/bin/;
- Escreva um arquivo em linguagem C (/home/userlinux/aluno.c) que escreve com printf a seguinte mensagem no output: “sou o aluno XXXXXXX” (atenção, se é aluno de algum curso presencial, utilize seu Registro Acadêmico no lugar dos X);
- Compile com
- /home/userlinux/pacotes/aluno_0_0_amd64/usr/bin/ (exemplo: gcc /home/userlinux/aluno.c -o /home/userlinux/pacotes/aluno_0_0_amd64/usr/bin/aluno)
- Crie o arquivo control (ver figura abaixo) e o preinst no diretório DEBIAN;
- Execute o comando chmod dando a permissão 775 ao arquivo preinst;
- Execute o comando dpkg-deb e crie seu novo pacote .deb;
- Execute o comando dpkg e instale o seu novo pacote .deb;

mkdir /home/userlinux/pacotes
mkdir /home/userlinux/pacotes/aluno_0_0_amd64
mkdir -p /home/userlinux/pacotes/aluno_0_0_amd64/DEBIAN
mkdir -p /home/userlinux/pacotes/aluno_0_0_amd64/usr/bin

Aluno.c
#include <stdio.h>

int main() {
    printf("sou o aluno XXXXXXX\n");
    return 0;
}


gcc /home/userlinux/aluno.c -o /home/userlinux/pacotes/aluno_0_0_amd64/usr/bin/aluno


Package: Aluno
Version: 0.0
Architecture: all
Essential: no
Priority: optional
Maintainer: Aied
Description: Pratica do aluno (conteúdo do arquivo control em /home/userlinux/pacotes/aluno_0_0_amd64/DEBIAN)


#!/bin/bash
echo "Instalando o pacote aluno..." (/home/userlinux/pacotes/aluno_0_0_amd64/DEBIAN/preinst)

chmod 775 /home/userlinux/pacotes/aluno_0_0_amd64/DEBIAN/preinst


dpkg-deb --build /home/userlinux/pacotes/aluno_0_0_amd64

sudo dpkg -i /home/userlinux/pacotes/aluno_0_0_amd64.deb


aluno


2. Nesta prática o aluno deverá remover seu próprio pacote .deb utilizando dpkg.
- Remova o pacote aluno completamente;

sudo dpkg -r aluno ou sudo dpkg --purge aluno

dpkg -l | grep aluno


3. Nesta prática o aluno deverá utilizar o comando apt para instalar o pacote net-tools.
- Utilizando o comando apt, instale o pacote chamado net-tools;

sudo apt update
sudo apt install net-tools

ifconfig


5. Desenvolva as seguintes práticas: 
- Baixar e instalar o pacote .deb do net-tools com dpkg

```
wget -O /tmp/net-tools.deb http://ftp.us.debian.org/debian/pool/main/n/net-tools/net-tools_*.deb
sudo dpkg -i /tmp/net-tools.deb
```

- Baixar e instalar o pacote .deb do libjsoncpp-dev com dpkg

```
wget -O /tmp/libjsoncpp-dev.deb http://ftp.us.debian.org/debian/pool/main/j/jsoncpp/libjsoncpp-dev_*.deb
sudo dpkg -i /tmp/libjsoncpp-dev.deb
```

- Listar os pacotes instalados no sistema

```
dpkg -l
```

- Remover o pacote libjsoncpp-dev com dpkg --purge

```
sudo dpkg --purge libjsoncpp-dev
```

- Instalar libjsoncpp-dev usando o apt

```
sudo apt update
sudo apt install libjsoncpp-dev
```

- Remover libjsoncpp-dev com apt purge

```
sudo apt purge libjsoncpp-dev
```

- Atualizar o sistema

```
sudo apt update
sudo apt upgrade
```

- Instalar ambiente gráfico XFCE com tasksel e reiniciar
a. Executar o tasksel: ``` sudo tasksel ```
b. Reiniciar o sistema: ``` sudo reboot ```






