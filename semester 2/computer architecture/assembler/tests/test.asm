.data
arr: .space, 1600
check: .ascii "\nis your word ready? 0-yes, 1-no\n"
let: .ascii "\ngive me a letter\n"
null: .ascii ""

.text
li $t9, 0 #amount of letters
li $t8, 0


inloop:
li $v0, 4
la $a0, check
syscall
li $v0, 5
syscall
beq $v0, 0, lol
add $t9, $t9, 1
li $v0, 4
la $a0, let
syscall
la $v0, 8
syscall
sw $v0, arr($t8)
add $t8, $t8, 4
j inloop

lol:
li $t0, -8 #index
mul $t4, $t9, 4
loop:
add $t0, $t0, 8
bgt $t0, $t8, end
lw $s2, arr($t0)
la $a0, ($s2)
li $v0, 4
syscall
j loop


end:
li $v0, 10
syscall
