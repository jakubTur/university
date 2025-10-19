.data
start: .asciiz "give me some N bigger than 0\n" 
error: .asciiz "you've reached overflow"
.text
input:
	la $a0, start
	li $v0, 4
	syscall
	li $v0, 5
	syscall
checkinput:
	blt $v0, 1, input
add $t0, $v0, $zero
li $t2, 1
do:
	add $t1,$t1,1
	mult $t2, $t1
	mfhi $t3
	bne $zero, $t3, ov
	mflo $t2
	beq $t1, $t0, end
	
while:
	beq $t1, $t0, end
	j do
end:	
	li $v0,1
	add $a0, $zero,$t2
	syscall
	li $v0, 10
	syscall
ov:
	la $a0, error
	li $v0, 4
	syscall
	li $v0, 10
	syscall