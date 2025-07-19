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

#### listaespecial.cpp

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

### devices.cpp

Qual partição que está sendo utilizada na montagem da raiz do GNU/Linux

<code>./devices.cpp </code>


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