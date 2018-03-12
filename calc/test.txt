.text

j beginning

label2: add $R0, $R1, $R0
sub $R0, $R1, $R3
sb $R0, 0x12($R0)
addi $R0, $R1, 0x20
lb $R0, 0x30($R3)
j end

label1: addi $R0, $R1, 0x20

j label2
# this is a comment

beginning: add $R1, $R1, $R1
sub $R0, $R1, $R3
sb $R0, 0x12($R0)
addi $R0, $R1, 0x20
lb $R0, 0x30($R3)
j label1

end: 
addi $R1, $R0, 0x20
addi $R1, $R1, 0x16

rand $R3, $R1
disp $R0
disp $R1
disp $R2
disp $R3