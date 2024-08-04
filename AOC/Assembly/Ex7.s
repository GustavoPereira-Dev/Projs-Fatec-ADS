.data
	msg1: .asciiz"\nDigite o valor de compra: "
	msg2: .asciiz"\nDigite a quantidade de parcelas: "
	msg3: .asciiz"\nSerá feita "
	msg4: .asciiz" parcelas de "
.text
compra:
	li $v0, 4
	la $a0, msg1
	syscall 
	li $v0, 5
	syscall
	add $t0, $v0, 0
	
	blt $t0, 1000 compra
	bgt $t0, 9999 compra

parcela:
	li $v0, 4
	la $a0, msg2
	syscall 
	li $v0, 5
	syscall
	add $t1, $v0, 0
	
	div $t2, $t0, $t1
	
	blt $t2, 100 parcela
	bgt $t2, 500 parcela
	
	
	
	li $v0, 4
	la $a0, msg3
	syscall 
	li $v0, 1
	add $a0, $t1, 0
	syscall
	
	li $v0, 4
	la $a0, msg4
	syscall 
	li $v0, 1
	add $a0, $t2, 0
	syscall
	
	