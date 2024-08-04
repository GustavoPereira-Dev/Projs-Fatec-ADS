.data
	msg1: .asciiz"\nDigite o valor em dólar: "
	msg2: .asciiz"\nO valor em reais é: "
.text
	li $v0, 4
	la $a0, msg1
	syscall 
	li $v0, 5
	syscall
	add $t0, $v0, 0
	
	mul $t1, $t0, 5
	li $v0, 4
	la $a0, msg2
	syscall 
	li $v0, 1
	add $a0, $t1, 0
	syscall
	
	