.data
	msg1: .asciiz"\nDigite o valor do 1º Número (Valor entre 100 e 999): "
	msg2: .asciiz"\nCENTENA: "
	msg3: .asciiz"\nDEZENA: "
	msg4: .asciiz"\nUNIDADE: "

.text
main:
	li $v0, 4
	la $a0, msg1
	syscall
	li $v0, 5
	syscall
	add $t0, $v0, 0


	blt $t0, 100 main
	bgt $t0, 999 main
	
	div $t1, $t0, 100
	rem $t4, $t0, 100
	
	div $t2, $t4, 10
	
	rem $t3, $t4, 10
	
	li $v0, 4
	la $a0, msg2
	syscall
	li $v0, 1
	add $a0, $t1, 0
	syscall 
	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 1
	add $a0, $t2, 0
	syscall 
	li $v0, 4
	la $a0, msg4
	syscall
	li $v0, 1
	add $a0, $t3, 0
	syscall 
	
	
	
	
	
	
	
