.data
	msg1: .asciiz"\nDigite o 1� Valor: "
	msg2: .asciiz"\nDigite o 2� Valor: "
	msg3: .asciiz"\n1� Valor: "
	msg4: .asciiz"\n2� Valor: "
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
	
	add $t2, $t0, 0
	add $t0, $t1, 0
	add $t0, $t2, 0
	
	li $v0, 4
	la $a0, msg3
	syscall 
	li $v0, 1
	add $a0, $t0, 0
	syscall
	
	li $v0, 4
	la $a0, msg4
	syscall 
	li $v0, 1
	add $a0, $t1, 0
	syscall
	
	