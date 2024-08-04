.data
	msg1: .asciiz"\nDigite o mês de nascimento: "
	msg2: .asciiz"\nDigite o ano de nascimento: "
	msg3: .asciiz"\nDigite o mês atual: "
	msg4: .asciiz"\nDigite o ano atual: "
	msg5: .asciiz"\nA idade da pessoa em meses: "
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
	
	li $v0, 4
	la $a0, msg4
	syscall 
	li $v0, 5
	syscall
	add $t3, $v0, 0
	
	sub $t4, $t3, $t1
	
	
	sub $t4, $t4, 1
	mul $t4, $t4, 12
	
	bgt $t0, $t2 cond2
	beq $t0, $t2 cond3
cond1:
	sub $t5, $t2, $t0
	j fimse
cond2:
	sub $t5, $t0, $t2
cond3:
	add $t5, $t5, 12
fimse:

	
	add $t5, $t5, $t4
	li $v0, 4
	la $a0, msg5
	syscall 
	li $v0, 1
	add $a0, $t5, 0
	syscall
	
	