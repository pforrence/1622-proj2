.text

addi $R0, $R0, 0x01
addi $R1, $R1, 0x05

disp $R0
disp $R1
disp $R2
disp $R3

xor $R2, $R1, $R0
xor $R1, $R0, $R0


disp $R0
disp $R1
disp $R2
disp $R3