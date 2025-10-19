.data
 	prec: .double 0.000001
 	n: .asciiz "\n"
.text
	li $t3, 0
	li $v0, 7
	syscall
#$t0 - N   print double=3
	mov.d $f4, $f0
	sqrt.d $f4, $f4
	mov.d $f12, $f4
	li $v0, 3
	syscall
	mul.d $f12, $f12, $f12
	la $a0, n
	li $v0, 4
	syscall
	li $v0, 3
	syscall
	
