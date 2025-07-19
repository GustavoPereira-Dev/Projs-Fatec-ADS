#!/bin/bash

# Diretório base
DIR_BASE="$HOME"

# Arquivo de relatório
RELATORIO="/home/userlinux/relatorio.rel"

# Timestamp atual
DATA=$(date "+%Y-%m-%d %H:%M:%S")

# Cabeçalho do relatório
echo "Relatório gerado em $DATA" >> "$RELATORIO"

# Buscar arquivos modificados na última hora com extensões específicas
find "$DIR_BASE" -type f \( -name "*.txt" -o -name "*.sh" -o -name "*.odt" \) -mmin -60 -printf "%p - %TY-%Tm-%Td %TH:%TM:%TS\n" >> "$RELATORIO"

# Linha separadora
echo "----------------------------------------" >> "$RELATORIO"