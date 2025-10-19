li $s0, 1 #x
li $s1, 1 #c
li $s2, 1 #b
li $s3, 1 #a
li $s4, 1 #d
mul $s1, $s0, $s1 #cx
add $s7, $s4, $s1 #+cx+d
mul $s5, $s0, $s0 #x^2
mul $s2, $s2, $s5 #b(x^2)
add $s7, $s7, $s2 #+b(x^2)
mul $s5, $s5, $s0 #x^3
mul $s3, $s5, $s3 #a(x^3)
add $s7, $s7, $s3 #+a(x^3)

li $v0,1
add $a0, $zero,$s7  
syscall

li $v0,10
syscall
