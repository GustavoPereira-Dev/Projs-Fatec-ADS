.data
	msg1: .asciiz"\nDigite a quantidade de horas: "
	msg2: .asciiz"\nDigite a quantidade de hora extra: "
	msg3: .asciiz"\nDigite a porcentagem de desconto: "
	msg4: .asciiz"\nO O Salario Bruto é: "
	msg5: .asciiz"\nO O Salario Líquido é: "
.text
main:
	li $v0, 4
	la $a0, msg1
	syscall
	li $v0, 5
	syscall
	add $t0, $v0, 0
	mul $t0, $t0, 10
	

	li $v0, 4
	la $a0, msg2
	syscall
	li $v0, 5
	syscall
	add $t1, $v0, 0	
	mul $t1, $t1, 15
	
		
	
	
	add $t2, $t0, $t1
	
	
	
	li $v0, 4
	la $a0, msg4
	syscall
	li $v0, 1
	add $a0, $t2, 0
	syscall 
	
	li $v0, 4
	la $a0, msg3
	syscall
	li $v0, 5
	syscall
	add $t3, $v0, 0
	
	
	mul $t3, $t2, $t3
	div $t3, $t3, 100
	
	sub $t4, $t2, $t3
	
	
	
	li $v0, 4
	la $a0, msg5
	syscall
	li $v0, 1
	add $a0, $t4, 0
	syscall 
