.data
	msg1: .asciiz"\nDigite o 1� N�mero: "
	msg2: .asciiz"\nDigite o 2� N�mero: "
	msg3: .asciiz"\nOs n�meros s�o iguais"
	msg4: .asciiz"\nOs n�meros s�o diferentes"
	msg5: .asciiz"\nMaior: "
	msg6: .asciiz"\nMenor: "
.text
	li $v0, 4
	la $a0, msg1
	syscall 
	li $v0, 5
	syscall
	add $t0, $v0, 0
	
	li $v0, 4
	la $a0, msg2
	syscall 
	li $v0, 5
	syscall
	add $t1, $v0, 0
	
	bne $t0, $t1 diferente
	
igual:
	li $v0, 4
	la $a0, msg3
	syscall 
	j fimse
diferente:
	li $v0, 4
	la $a0, msg4
	syscall 
	bgt $t1, $t0 segundoMaior
	
	primeiroMaior:
		add $t2, $t0 0
		add $t3, $t1, 0
		j imprimirDiferente
	segundoMaior:
		add $t2, $t1, 0
		add $t3, $t0, 0
	
	imprimirDiferente:
		li $v0, 4
		la $a0, msg5
		syscall 
		li $v0, 1
		add $a0, $t2, 0
		syscall
	
		li $v0, 4
		la $a0, msg6
		syscall 
		li $v0, 1
		add $a0, $t3, 0
		syscall
fimse:

	
	
	