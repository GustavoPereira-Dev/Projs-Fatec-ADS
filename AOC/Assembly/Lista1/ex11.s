.data
	msg1: .asciiz"\nDigite o valor do "
	msg2: .asciiz"º número (digite valor menor que 0 para finalizar): "
	msg3: .asciiz"\nA quantidade de números no intervalo [0-25] é: "
	msg4: .asciiz"\nA quantidade de números no intervalo [25-50] é: "
	msg5: .asciiz"\nA quantidade de números no intervalo [51-75] é: "
	msg6: .asciiz"\nA quantidade de números no intervalo [76-100] é: "
	msg7: .asciiz"O número deve estar entre 0 e 100"

.text
main:
	add $t0, $t0, 1
	
	
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

	blt $t1, 0, fimenquanto
	bgt $t1, 100, if
	
	blt $t1, 26, intervalo1
	blt $t1, 51, intervalo2
	blt $t1, 76, intervalo3
	blt $t1, 101, intervalo4

	
if:
	li $v0, 4
	la $a0, msg7
	syscall
	j main
intervalo1:
	add $t2, $t2, 1
	j main
intervalo2:
	add $t3, $t3, 1
	j main
intervalo3:
	add $t4, $t4, 1
	j main
intervalo4:
	add $t5, $t5, 1	
	j main

	
fimenquanto:
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
	
	li $v0, 4
	la $a0, msg5
	syscall
	li $v0, 1
	add $a0, $t4, 0
	syscall 
	
	li $v0, 4
	la $a0, msg6
	syscall
	li $v0, 1
	add $a0, $t5, 0
	syscall 

	
	
	
	
