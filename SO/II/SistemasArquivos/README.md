# Sistemas de Arquivos

## Códigos 

### copiar.cpp

Copiar arquivos de um local/nome para outro

<code>./copiar [arquivo] [copia]</code>

### Listagem de arquivos/diretórios

#### listar.cpp

Listagem de arquivos por leitura padrão

#### listarrecursivo.cpp

Listagem de arquivos (de diretórios e subdiretórios de forma recursiva)

#### listarespecial.cpp

Listar os arquivos do diretório e imprimir no terminal com cout os arquivos que são de especiais de carácter e de bloco.

### exemplostat.cpp

Propriedades do diretório

### mypwd.cpp

Nome do diretório


### mover.cpp

Mover arquivos de um local para o outro

<code>./mover [localArquivo] [novoLocal]</code>

### Dados do arquivo

#### exibir.cpp

Exibir texto do arquivo

<code>./exibir [arquivo]</code>

#### exibirperm.cpp

Exibir permissões do arquivo

<code>./exibirperm [arquivo]</code>

#### devices.cpp

Qual partição que está sendo utilizada na montagem da raiz do GNU/Linux

<code>./devices.cpp </code>

### Partições

#### getuuid.cpp

Mostra os dados da partições do dispositivo como o UUID (identificador único) LABEL (nome) e TYPE (tipo de sistema de arquivos como ext4 e ntfs).

<code>./exibirperm [dispositivo]</code>

## Atividades


### Sistemas de Arquivos

1. Criando diretórios e arquivos no GNU/Linux Nesta prática o aluno deve ter a expertise de criar diretórios e arquivos, mas antes o aluno deve navegar até o diretório do usuário e seguir o roteiro:
- Tenha certeza que o diretório não possui arquivos do usuário;
- Criar dentro do diretório do usuário os seguintes diretórios (usando o comando mkdir): diretorio1, diretorio2, diretorio3 conforme figura:
- Dentro do diretorio1 crie um arquivo chamado arquivo1 com o conteúdo “111”;
- Dentro do diretorio1 crie um arquivo chamado arquivo2 com o conteúdo “222”;
- Dentro do diretorio1 crie um arquivo chamado arquivo3 com o conteúdo “333”;
- Dentro do diretorio2 crie um arquivo chamado arquivo4 com o conteúdo “444”;
- Dentro do diretorio2 crie um arquivo chamado arquivo5 com o conteúdo “555”;
- Dentro do diretorio2 crie um arquivo chamado arquivo6 com o conteúdo “666”;

```
cd ~
ls -a
mkdir diretorio1 diretorio2 diretorio3

echo "111" > diretorio1/arquivo1
echo "222" > diretorio1/arquivo2
echo "333" > diretorio1/arquivo3

echo "444" > diretorio2/arquivo4
echo "555" > diretorio2/arquivo5
echo "666" > diretorio2/arquivo6

sudo apt install tree
tree
```

2. Nesta prática o aluno deve utilizar os comandos cp, rm e mv para manipular os arquivos no Sistema de Arquivos, partindo da prática anterior o aluno deve:
- Copiar o arquivo arquivo1 que está em diretorio1 para o diretorio3;
- Remover o arquivo arquivo4 que está em diretorio2;
- Mover o arquivo arquivo5 que está em diretorio2 para o diretorio3;

```
cp diretorio1/arquivo1 diretorio3/
rm diretorio2/arquivo4
mv diretorio2/arquivo5 diretorio3/

tree
cat diretorio3/arquivo1
cat diretorio3/arquivo5
```

3. Nesta prática o aluno deve utilizar o comando ln para criar um link simbólico e um hard link, partindo da prática anterior o aluno deve:
- Criar um link simbólico com o nome arquivo3 no diretorio3 apontando para o arquivo arquivo3 no diretorio1;
- Criar um Hard Link com o nome arquivo6 no diretorio3 que aponta para o arquivo arquivo6 do diretorio2;

```
ln -s ../diretorio1/arquivo3 diretorio3/arquivo3
ln diretorio2/arquivo6 diretorio3/arquivo6

ls -l diretorio3/
ls -i diretorio2/arquivo6 diretorio3/arquivo6
```

4. Nesta prática o aluno deve utilizar o aprendizado sobre permissões especiais para customizar as permissões de arquivos do usuário (entenda-se como ~):
- Criar um diretório chamado tmp no diretório do usuário;
- Criar um diretório chamado bin no diretório do usuário;
- Para o diretório tmp criado, ative a permissão especial Stick Bit;
- Copie o arquivo binário copiar (feito neste capítulo) para dentro do diretório bin;
- Permita que somente o dono do arquivo possa executar, mas ninguém;
- Dê a permissão especial SetUID para o programa /etc/aied/aied_64;

```
mkdir ~/tmp
mkdir ~/bin
chmod +t ~/tmp
cp copiar ~/bin/
chmod 700 ~/bin/copiar
sudo chmod u+s /etc/aied/aied_64

ls -ld ~/tmp
ls -l /etc/aied/aied_64
```

### Formatação e Montagem de Partições e Blocos

1. Nesta prática o aluno deve ter a expertise para adicionar um novo disco, formatar e montar no /etc/fstab, mas antes o aluno deve ter o comando sudo devidamente instalado. O aluno deve:
- Adicione um novo disco na Virtual Machine pelo VirtualBox;
- Crie uma partição primária no novo disco;
- Formate a nova partição com padrão ext4;
- Altera o arquivo /etc/fstab para durante a inicialização do GNU/Linux seja feita a montagem em /backup, para editar o arquivo /etc/fstab use o sudo e o nano;
- Reinicie a máquina.

```
sudo fdisk /dev/sdb
sudo mkfs.ext4 /dev/sdb1
sudo mkdir /backup
sudo blkid /dev/sdb1 (coletar UUID)

sudo nano /etc/fstab
UUID=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx /backup ext4 defaults 0 2 (adicionar no final do arquivo)

sudo reboot
df -h | grep /backup
```

2. Nesta prática o aluno deve ter a expertise para usar o comando de montagem e montar um CD no CD-ROM já existente, essa montagem será feita no diretório /home/userlinux/cdrom. O aluno deve:
- Faça download da iso nesta url: (https://drive.google.com/file/d/1xk-6ZDSbB8d6JZzSqy7NjUyM6p3xBLHU/view?usp=sharing)
- Configure o Virtual Box para abrir no CDROM esta imagem iso;
- Crie o diretório /home/userlinux/cdrom
- Monte o CD no CD-ROM /dev/sr0 em /home/userlinux/cdrom/, para isso use o mount.

```
sudo mkdir -p /home/userlinux/cdrom
sudo mount /dev/sr0 /home/userlinux/cdrom

ls /home/userlinux/cdrom
sudo umount /home/userlinux/cdrom
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