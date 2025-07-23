# Python em SOs

1. Nesta prática o aluno deverá configurar o ambiente python com um módulo necessário.
- Instale o pip3 para o python3;
- Após instalar o pip3 instale o módulo network;

```
sudo apt update
sudo apt install python3-

pip3 install network

python3 -c "import network"

```

2. Nesta prática o aluno deverá criar um script, de acordo com o roteiro abaixo.
- Crie com o nano um arquivo /home/userlinux/pythonscript1.py;
- No script aponte o Shebang para o python3;
- Import o módulo os;
- Imprima o usuário corrente;
- Adicione o poder de execução para o script /home/userlinux/pythonscript1.py;

```
nano /home/userlinux/pythonscript1.py

chmod +x /home/userlinux/pythonscript1.py
/home/userlinux/pythonscript1.py

```

3. Nesta prática o aluno deverá criar um script, de acordo com o roteiro abaixo.
- Crie com o nano um arquivo /home/userlinux/pythonscript2.py;
- No script aponte o Shebang para o python3;
- Import o módulo hashlib e sys;
- Receba o primeiro argumento, e gere um MD5 deste argumento, guarde em uma variável;
- Imprima esta variável com o MD5;
- Adicione o poder de execução para o script /home/userlinux/pythonscript2.py;

```
nano /home/userlinux/pythonscript2.py

chmod +x /home/userlinux/pythonscript2.py
/home/userlinux/pythonscript2.py Gustavo

```

4. Nesta prática o aluno deverá criar um script, de acordo com o roteiro abaixo.
- Crie com o nano um arquivo /home/userlinux/pythonscript3.py;
- No script aponte o Shebang para o python3;
- Abra o arquivo /etc/passwd para leitura, carregue as linhas em um array;
- Faça um laço de repetição, e imprima o usuário do Linux que possui com default shell o /bin/bash;
- Adicione o poder de execução para o script /home/userlinux/pythonscript3.py;

```
nano /home/userlinux/pythonscript3.py

chmod +x /home/userlinux/pythonscript3.py
/home/userlinux/pythonscript3.py
```

5. Nesta prática o aluno deverá criar um script, de acordo com o roteiro abaixo.
- Crie com o nano um arquivo /home/userlinux/pythonscript4.py;
- No script aponte o Shebang para o python3;
- Receba por parâmetro na posição 1 um valor que será passado;
- Salve o valor passado por argumento na posição 1 em um arquivo /tmp/argumento.txt
- Adicione o poder de execução para o script /home/userlinux/pythonscript4.py;

```
nano /home/userlinux/pythonscript4.py

chmod +x /home/userlinux/pythonscript4.py
/home/userlinux/pythonscript4.py Gustavo
```