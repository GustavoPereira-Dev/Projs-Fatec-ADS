# Usuários GNU/Linux

1. Nesta prática o aluno deve ter a expertise de criar usuários e grupos, mas antes o aluno deve ter o comando sudo devidamente instalado e configurado conforme link abaixo. O aluno deve:
- Criar um grupo chamado alunos;
- Criar um usuário chamado joao e durante a criação defina como grupo default o grupo alunos, utilize o comando useradd para isso;
- Crie um usuário chamado leticia sem informar grupo default;
- Crie um usuário chamado wanderson sem informar grupo default;
- Crie um novo diretório chamado /hd2/usuarios/ na raiz, vai precisar ter permissão de acesso;
- Crie um usuário chamado bia sem informar grupo default mas informando que o default directory deste usuário é /hd2/usuarios/bia;
- Informar um password para os usuários: wanderson, bia e leticia

```
sudo apt update
sudo apt install sudo

sudo usermod -aG sudo userlinux

sudo useradd -m -g alunos joao
sudo useradd -m leticia
sudo useradd -m wanderson

sudo mkdir -p /hd2/usuarios/
sudo chmod 755 /hd2/usuarios/

sudo useradd -m -d /hd2/usuarios/bia bia

sudo passwd wanderson
sudo passwd bia
sudo passwd leticia

```
```
cat /etc/passwd | grep -E 'joao|leticia|wanderson|bia
```
```
getent group alunos

```

2. Nesta prática o aluno deve ter a expertise de alterar usuários, a lista de atividades da prática:
- Com usermod faça com que o grupo default de leticia, wanderson e bia seja alunos;
- Defina como diretório padrão para wanderson o diretório /hd2/usuarios/wanderson;
- Defina como diretório padrão para leticia o diretório /hd2/usuarios/leticia;
- Defina como diretório padrão para joao o diretório /hd2/usuarios/joao;
- Trave a conta do usuário leticia com usermod e parâmetro L;
- Defina que o usuário leticia deve ter como default shell /bin/false

```
sudo usermod -g alunos leticia
sudo usermod -g alunos wanderson
sudo usermod -g alunos bia

sudo usermod -d /hd2/usuarios/wanderson wanderson
sudo usermod -d /hd2/usuarios/leticia leticia
sudo usermod -d /hd2/usuarios/joao joao

sudo usermod -L leticia

sudo usermod -s /bin/false leticia

getent passwd leticia
getent passwd wanderson
getent passwd joao

sudo passwd -S leticia

```

3. Nesta prática o aluno deve ter a expertise de alterar usuários tanto no arquivo shadow quanto no arquivo passwd:
- Para o usuário root, altere a senha para * no arquivo shadow;
- Altere no passwd o shell padrão do usuário www-data como /bin/false

```
sudo nano /etc/shadow
```
Localizar a linha que começar com:
root:...

Substituir o campo da senha (entre root: e o próximo :) por *
root:*:...

```
sudo nano /etc/passwd
```
Localizar a linha que começa com:
www-data:x:...

Substituir o final da linha (geralmente /usr/sbin/nologin) por /bin/false:
www-data:x:33:33:www-data:/var/www:/bin/false

```
getent passwd www-data
sudo grep '^root:' /etc/shadow

```

4. Nesta prática o aluno deve ter a expertise para criar um usuário de serviço:
- Crie um grupo chamado tomcat;
- Crie um usuário chamado tomcat, mas com as seguintes características:
- Não possuir senha
- Diretório default /opt/tomcat
- Shell padrão /bin/false
- Grupo default tomcat
- Crie o diretório /opt/tomcat e altere o diretório tenha como grupo o grupo tomcat.

```
sudo groupadd tomcat
sudo useradd -M -d /opt/tomcat -s /bin/false -g tomcat tomcat

sudo mkdir -p /opt/tomcat
sudo chown root:tomcat /opt/tomcat
sudo chmod 775 /opt/tomcat

getent passwd tomcat

ls -ld /opt/tomcat

```