nano /home/userlinux/portas_abertas.sh
#!/bin/bash

# Arquivo de relatório
RELATORIO="/home/userlinux/portas_abertas.rel"

# Timestamp atual
DATA=$(date "+%Y-%m-%d %H:%M:%S")

# Cabeçalho
echo "Relatório de portas abertas - $DATA" >> "$RELATORIO"

# Listar portas abertas com netstat
netstat -tuln | grep LISTEN >> "$RELATORIO"

# Linha separadora
echo "----------------------------------------" >> "$RELATORIO"