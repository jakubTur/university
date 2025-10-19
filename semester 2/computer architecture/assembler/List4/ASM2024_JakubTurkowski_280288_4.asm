.data
start: .asciiz "give me a number N<1000\n"
pr: .asciiz "This is a prime number"
npr: .asciiz "This is not a prime number"
.text
la $a0, start
li $v0, 4
syscall
li $v0, 5
syscall
add $t1, $v0, $zero
beq $t1, 1, notprime
li $t4, 2
div $t1, $t4
mflo $t5
for:
	div $t1, $t4
	mfhi $t3
	bgt $t4, $t5, prime
	add $t4, $t4, 1
	bgt $t3, 0, for
notprime:
	la $a0, npr
	li $v0, 4
	syscall
	li $v0, 10
	syscall
prime:
	la $a0, pr
	li $v0, 4
	syscall
	li $v0, 10
	syscall
