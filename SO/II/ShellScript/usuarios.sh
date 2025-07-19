#!/bin/bash
# usuarios.sh
# Listar usuários

getent passwd > output_usuarios

- Fazer um script que se um diretório não existe, então você cria ele (~/temporario)

nano temporario.sh

#!/bin/bash
# temporario.sh
# Criar diretório

if [ -d ./temporario ] ; then
  echo “Diretorio existe”
else
  mkdir temporario
fi