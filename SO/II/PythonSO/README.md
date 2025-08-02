# Python em SOs

## Códigos

<code></code>

### parametros.py

Exibe o primeiro argumento passado via linha de comando

<code>./parametros.py</code> ou <code>./parametros.py [parametro]</code>

### hash.py

Gera um hash SHA-1 a partir de uma string

<code>./hash.py</code>

### libos.py

Faz o uso da biblioteca os (arquitetura do SO, Variáveis de ambiente e ações com diretórios e arquivos)

<code>./libos.py</code>

### abrirarquivo.py

Tenta abrir um arquivo usando a lib os (abre para leitura e cria o arquivo se não existir) e retorna os dados dele

<code>./abrirarquivo.py</code>

### criararquivo.py

Cria um arquivo usando a lib os

<code>./abrirarquivo.py</code>

### inspect.py

Inspeciona o caminho do arquivo atual, o converte esse caminho para um formato absoluto e junta esse diretório com o nome "segundo.py" para formar o caminho completo do segundo script

<code>./inspect.py</code>

### pai.py

Chama o processo “filho” (filho.py) e manda uma mensagem via stdin, que faz o filho deve ler esse JSON, processar e imprimir algo de volta (e o pai capta essa resposta via stdout)

<code>./pai.py</code>

### filho.py

Faz parte de uma comunicação entre processos em que o “filho” recebe dados JSON do processo “pai” via entrada padrão (stdin). 

<code>./filho.py</code>

### popen_cmd.py

Ele lista os arquivos do diretório raiz (/) usando ls -lha, captura o resultado linha por linha e imprime no terminal com um prefixo.

<code>./popen_cmd.py</code>

### run_cmd.py

Usa o subprocess.run, que é mais direto quando não precisa de comunicação complexa com o processo filho

<code>./run.py</code>

### exemplo.py

Como usar o módulo argparse para lidar com argumentos de linha de comando em Python e o uso avançado de parâmetros opcionais com nargs='?', que aceita zero ou um valor após o argumento -e, const, usado se o argumento for passado sem valor, e default, usado se o argumento nem for passado.

<code></code>

### exemplo2.py

Cria um parser com ajuda embutida (--help) esperando que o usuário forneça um argumento. Se o argumento não for fornecido, o script exibe uma mensagem de erro automaticamente

<code>./exemplo2.py</code> ou <code>./exemplo2.py -f [argumento]</code>

### exemplo_banner.py

Usa a biblioteca pyfiglet para renderizar texto em estilo ASCII art

<code>./exemplo_banner.py</code>

### exemplo_cores.py

Exibe texto colorido no terminal usando códigos ANSI — que é muito útil pra destacar mensagens em scripts CLI

![Cores](image.png)

[Mais cores ANSI](https://hexdocs.pm/color_palette/color_table.html)

<code>./exemplo_cores.py</code>

## Atividades

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