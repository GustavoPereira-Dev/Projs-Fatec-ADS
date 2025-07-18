# Processos e Threads

## myblkid.cpp

Obter o UUID do dispositivo de bloco

<code>sudo ./myblkid</code>

## thread.cpp

Criação e finalização de thread

<code>./thread</code>

## userfork.cpp

Uso de fork (um processo pai vai criar um processo filho, e cada processo irá escrever uma frase no output)

<code>./fork</code>

## usewait.cpp

Uso do wait em processos pais e filhos

<code>./usewait</code>

## usewait_exit.cpp

Uso do wait e exit em processos pais e filhos

<code>./usewait</code>

## waitpid.cpp

Uso do waitpid para um processo pai aguardar um grupo de processos filhos passando como parâmetro o PID do processo filho

<code>./waitpid</code>

## system.cpp

Executa um comando shell do sistema operacional conforme descrito por uma string contendo o comando literal.

<code>./system</code>

## pop.cpp

Uso do popopen (abre um processo criando e criando um pipe de comunicação (um arquivo tipo s dentro do sistema operacional), o fork() então executa o shell, que permite obter o output do comando executado)

<code>./pop</code>

## receivesignal.cpp

Faz o uso e captura de um signal

<code>./receivesignal</code>