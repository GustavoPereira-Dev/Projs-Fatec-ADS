.data
	msg1: .asciiz"\nDigite a base menor: "
	msg2: .asciiz"\nDigite a base maior: "
	msg3: .asciiz"\nDigite a altura: "
	msg4: .asciiz"\nO valor da área do trapézio é: "
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
	
	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 5
	syscall
	add $t2, $v0, 0	
	
	add $t3, $t0, $t1
	mul $t3, $t3, $t2
	div $t3, $t3, 2
	
	li $v0, 4
	la $a0, msg4
	syscall
	li $v0, 1
	add $a0, $t3, 0
	syscall 