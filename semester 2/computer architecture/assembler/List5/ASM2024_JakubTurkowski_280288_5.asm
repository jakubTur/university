.data
	array: .space 400
	give: .asciiz "give me value of the next index of the vector\n"
	secondvec: .asciiz "now for the second vector\n"
	result: .asciiz "the dot product is: "
.text
li $t0, -4 #array position
li $t1, -1 #index
li $t4, 6 #maxindex UPD
li $t9, 1 #vector no
li $t5, -1
load:
	add $t1, $t1, 1
	bgt $t1, $t4, second
	la $a0, give
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	beq $v0, 0, load
	add $t0, $t0, 4
	sw $t1, array($t0)
	add $t0, $t0, 4
	sw $v0, array($t0)
	beq $t1, $t4, second
	j load
	
second:
	beq, $t9, 2, cont
	la $a0, secondvec
	li $v0, 4
	syscall
	li $t1, -1
	add $t9, $t9, 1
	add $t7, $t0, 4
	j load
cont:
	li $t0, 0 #index for first vector
	li $t5, -1
	cont2:
	add $t5, ,$t5, 1
	bgt $t5, $t4, end
	lw $t2, array($t0)
	lw $t3, array($t7)
	blt $t3, $t2, branch1
	blt $t2, $t3, branch2
	add $t0, $t0, 4
	add $t7, $t7, 4
	lw $t2, array($t0)
	lw $t3, array($t7)
	mult $t2, $t3
	mflo $t6
	add $t8, $t8, $t6
	beq $t5, $t4, end   #UPD
	add $t0, $t0, 4
	add $t7, $t7, 4
	j cont2
	branch1:
	add $t7, $t7, 8
	j cont2
	branch2:
	add $t0, $t0, 8
	j cont2
end:
la $a0, result
li $v0, 4
syscall

add $a0, $zero,$t8
li $v0, 1
syscall
