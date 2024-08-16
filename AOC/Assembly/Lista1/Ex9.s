.data
	msg1: .asciiz"\nDigite o valor do 1º Número: "
	msg2: .asciiz"\nDigite o valor do 2º Número (não pode ser 0): "
	msg3: .asciiz"\nO resultado é: "

.text
main:
	li $v0, 4
	la $a0, msg1
	syscall
	li $v0, 5
	syscall
	add $t0, $v0, 0

while:	
	li $v0, 4
	la $a0, msg2
	syscall
	li $v0, 5
	syscall
	add $t1, $v0, 0
	
	beq $t1, 0 while
	div $t2, $t0, $t1

	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 1
	add $a0, $t2, 0
	syscall 

	
	
	
	
