.text
addi $R3, $R0, 0x20
addi $R3, $R3, 0x16
sb $R3, 0x20($R0)

lb $R3, 0x20($R0)
disp $R3