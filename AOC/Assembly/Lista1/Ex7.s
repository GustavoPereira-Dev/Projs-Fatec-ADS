.data
	msg1: .asciiz"\nDigite o valor do Número: "
	msg2: .asciiz"\nO resultado é: "

.text
main:
	li $v0, 4
	la $a0, msg1
	syscall
	li $v0, 5
	syscall
	add $t0, $v0, 0
	
	rem $t1, $t0, 2
	
	
	beq $t1, 0 else
if:
	add $t0, $t0, 8
	j fimse

else:
	
	add $t0, $t0, 5

fimse:
	li $v0, 4
	la $a0, msg2
	syscall
	li $v0, 1
	add $a0, $t0, 0
	syscall 