.data
	msg1: .asciiz"\nDigite o valor do 1º Cateto: "
	msg2: .asciiz"\nDigite o valor do 2º Cateto: "
	msg3: .asciiz"\nO valor da hipotenusa é: "
	msg4: .asciiz"\nO valor da hipotenusa é aproximadamente: "
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
	
	mul $t0, $t0, $t0
	mul $t1, $t1, $t1
	
	add $t2, $t0, $t1
	
sqrLoop:
	add $t3, $t3, 1
	div $t4, $t2, $t3
	
	mul $t5, $t4, $t4
	bgt $t5, $t2 sqrLoop
 	beq $t5, $t2 raizExata
 	

raizAproximada:
 	li $v0, 4
	la $a0, msg4
	syscall
	j end
raizExata:
	li $v0, 4
	la $a0, msg3
	syscall
end:
	li $v0, 1
	add $a0, $t4, 0
	syscall 
