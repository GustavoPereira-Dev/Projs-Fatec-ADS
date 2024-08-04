.data
	msg1: .asciiz"\nDigite o valor de A: "
	msg2: .asciiz"\nDigite o valor de B: "
	msg3: .asciiz"\nDigite o valor de C: "
	msg4: .asciiz"\nDUAS RAÍZES REAIS"
	msg5: .asciiz"\nUMA RAÍZ REAL"
	msg6: .asciiz"\nNÃO EXISTEM RAÍZES REAIS"
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
	
	li $v0, 4
	la $a0, msg3
	syscall 
	li $v0, 5
	syscall
	add $t2, $v0, 0
	
	mul $t3, $t1, $t1
	mul $t4, $t0, 4
	sub $t5, $t3, $t4
	
	li $v0, 4
	bgt $t5, 0 duasRaizes
	blt $t5, 0 nenhumaRaiz
	
umaRaiz:
	la $a0, msg5
	j fimse
duasRaizes:
	la $a0, msg4
	j fimse
nenhumaRaiz:
	la $a0, msg6
	
fimse:
	syscall 