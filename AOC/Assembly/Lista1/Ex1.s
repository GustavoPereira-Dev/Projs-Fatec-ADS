.data
	msg1: .asciiz"\nDigite o valor da altura: "
	msg2: .asciiz"\nDigite o valor da largura: "
	msg3: .asciiz"\nO valor da altura é: "
.text
main:
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
	
	mul $t2, $t0, $t1
	
	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 1
	add $a0, $t2, 0
	syscall 
	