### Cron, Crontab e agendamento de taredas

1. Nesta prática o aluno deverá instalar o comando at e criar um agendamento.
- Utilize o apt e instale o comando at;
- utilize o comando at para executar o comando touch /tmp/teste.txt exatamente as 12:00;

```
sudo apt update
sudo apt install at

sudo systemctl start atd
sudo systemctl enable atd

echo "touch /tmp/teste.txt" | at 12:00

atq
```

ls -l /tmp/teste.txt

2. Nesta prática o aluno deverá criar um script de backup conforme exemplificado neste capítulo e agendar na crontab do usuário usuario.
- Crie um script chamado script.sh e posicione ele em /home/userlinux/
- Neste script edite o código de acordo com o tópico “Comando crontab”
- Agende com o comando crontab a execução do script para ocorrer todos os dias exatamente às 12:00

```
nano /home/userlinux/script.sh

chmod +x /home/userlinux/script.sh
crontab -e

```
Dentro da Crontab:
``` 0 12 * * * /home/userlinux/script.sh ```

```
crontab -l
```

3. Fazer um script que acha todos os arquivos alterado (na última hora) do tipo (txt, sh, odt) no diretório do usuário ~/, e então salvar em um relatório /home/userlinux/relatorio.rel, cada linha deve ter o caminho (path) do arquivo bem como horário. Faça esse arquivo relatorio.rel ser um arquivo incremental, apendando o novo relatório no relatório anterior. Use o agendamento recorrente de hora em hora para isso.

```
nano /home/userlinux/relatorio.sh

chmod +x /home/userlinux/relatorio.sh
```

crontab -e

Dentro da crontab:
``` 0 * * * * /home/userlinux/relatorio.sh ```

```
cat /home/userlinux/relatorio.rel
crontab -l
```

4. Todo minuto 0 da hora 10 e da hora 15, faça um update no sistema.

``` crontab -e ```

Dentro da crontab: ```0 10,15 * * * sudo apt update```

```
sudo visudo
userlinux ALL=(ALL) NOPASSWD: /usr/bin/apt

crontab -l
```

5. A cada minuto, usar agendamento. Faça um relatório de portas abertas no servidor, vai precisar fazer um script com netstat.

```
chmod +x /home/userlinux/portas_abertas.sh

crontab -e
```

Dentro da crontab:

``` * * * * * /home/userlinux/portas_abertas.sh ```

```
cat /home/userlinux/portas_abertas.rel
crontab -l
```