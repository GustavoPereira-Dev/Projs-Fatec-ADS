#!/bin/bash
# nome: script.sh
# diretorio: “/”
# descricao: Faz o backup do diretório do usuário, é apenas um exemplo da aula

data=`/bin/date +%-%m-%Y`;

tar –cvzf /tmp/backup-${LOGNAME}-${data}.tar.gz /home/${LOGNAME}/;