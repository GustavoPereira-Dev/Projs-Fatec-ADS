.data
	msg1: .asciiz"\nDigite o valor do 1º Número "
	msg2: .asciiz"\nDigite o valor do 2º Número: "
	msg3: .asciiz"\nO resultado é: "
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
	
	beq $t0, $t1 else
if:

	mul $t2, $t0, $t1
	j fimse

else:
	
	add $t2, $t0, $t1

fimse:
	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 1
	add $a0, $t2, 0
	syscall 
	
	
	
	
	
