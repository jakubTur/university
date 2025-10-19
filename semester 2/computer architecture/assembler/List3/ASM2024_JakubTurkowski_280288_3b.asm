.data
array: .space 400
start: .asciiz "how many numbers do you want to input? (less than 100)\n"
give: .asciiz "give me a number\n"
comma: .asciiz ","


.text
la $a0, start
li $v0, 4
syscall
li $v0, 5
syscall
mul $s1, $v0, 4
li $t0, 0
input:
	la $a0, give
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	sw $v0, array($t0)
	addi $t0, $t0, 4
	blt $t0, $s1, input
jal sort

end:
	lw $t2, array($t6)
	li $v0,1
	add $a0, $zero,$t2
	syscall
	la $a0, comma
	li $v0, 4
	syscall
	addi $t6, $t6, 4
	blt $t6, $s1, end

li $v0, 10
syscall

sort:
li $t9, 0
for:
	add $t9, $t9, 4
	li $t7, 0
	li $t8, 0
	sub $t6, $s1, $t9
for2:
	addi $t8, $t8, 4
	lw $t4, array($t7)
	lw $t5, array ($t8)
	bge $t5, $t4, cont
	sw $ra, ($sp)
	jal swap
	lw $ra, ($sp)

cont:
	addi $t7, $t7, 4
	blt $t7, $t6, for2
	beq $t7, $t6, for
jr $ra

swap:
	sw $t4, array($t8)
	sw $t5, array($t7)
	jr $ra
