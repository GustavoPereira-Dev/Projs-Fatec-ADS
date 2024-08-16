.data
	msg1: .asciiz"\nDigite a altura da "
	msg2: .asciiz"º pessoa: "
	msg3: .asciiz"\nA maior altura é: "
	msg4: .asciiz"\nA menor altura é: "
.text
main:
	add $t0, $t0, 1
	
	beq $t0, 16 fimpara
	
	li $v0, 4
	la $a0, msg1
	syscall
	li $v0, 1
	add $a0, $t0, 0
	syscall 
	li $v0, 4
	la $a0, msg2
	syscall
	li $v0, 5
	syscall
	add $t1, $v0, 0

	beq $t0, 1 ifInicio
	bgt $t1, $t2 ifMaior
	blt $t1, $t3 ifMenor
	
	j main
ifInicio:
	add $t2, $t1, 0
	add $t3, $t1, 0
	j main

ifMaior:
	add $t2, $t1, 0
	j main
	
ifMenor:
	add $t3, $t1, 0
	j main
	
fimpara:
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
	
	
	
	
	
