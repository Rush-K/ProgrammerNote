input_string = input().split(" ")

A = int(input_string[0])
B = int(input_string[1])
C = int(input_string[2])

print((A + B) % C)
print(((A % C) + (B % C)) % C)
print((A * B) % C)
print(((A % C) * (B % C)) % C)