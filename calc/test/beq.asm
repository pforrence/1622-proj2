.text

hump: 
addi $R0, $R0, 0x01
addi $R1, $R1, 0x01
add  $R2, $R1, $R2

disp $R0
disp $R1
disp $R2
disp $R3

beq $R2, $R0, hump

disp $R0
disp $R1
disp $R2
disp $R3