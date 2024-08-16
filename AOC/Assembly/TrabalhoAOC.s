.data
	# Mensagem para o resultado final
	msg1: .asciiz"\nO Resultado da soma dos 10 primeiros termos �: "
	
.text
	# Registrador do 2� Anterior ($t0)
	add $t0, $t0, 1
	
	# Registrador do 1� Anterior ($t1)
	add $t1, $t1, 1
	
	# Registrador do Contador/�ndice ($t2)
	add $t2, $t2, 2
	
	# Soma dos primeiros 2 termos de Fibonacci (2) para o Registrador do resultado da soma dos termos ($t4)
	add $t4, $t4, $t2
	
	# In�cio do loop/la�o
while:
	# Acrescentando o Contador em 1
	add $t2, $t2, 1
	
	# Registrador ($t3) da Fibonacci (resultado do termo)
	add $t3, $t0, $t1
	
	# Soma dos �ltimos termos de Fibonacci com o atual
	add $t4, $t4, $t3
	
	# 2� Anterior = 1� Anterior
	add $t0, $t1, 0
	
	# 1� Anterior = Fibonacci
	add $t1, $t3, 0
	
	# Se o Contador ainda n�o estiver no termo 10 (Contador != 10) continue o loop, caso contr�rio finalize-o
	bne $t2, 10 while
	
	# Fim do la�o e resultado final
	
	# Impress�o da mensagem (msg1)
	li $v0, 4
	la $a0, msg1
	syscall 
	
	# Impress�o do resultado da soma dos termos ($t4)
	li $v0, 1
	add $a0, $t4, 0
	syscall