# المتغيرات المعرفة (ستكون موجودة في pythonTable)
name = "Khaled"
age = 22
is_pro = True
price = 1500
# int + float
l = 1.5
n = 1
j = l + n

total = price * 2

# Undefined variable
print(c)

# type mismatch
info = name + age

se = "khaled"
a = 0
def test(a):
    y = a + se
    return y

x = 5

def t(b):
    z = b + x

t("string")

x = 5

def t(b):
    z = b + x

t(7.8)

# Scope error
if True:
    active_var = 100

print(active_var)

if False:
    dead_var = 500

print(dead_var)

if 5 > 3:
    active_var = 100

print(active_var)


if 3 > 5:
    dead_var = 500

print(dead_var)

number = 0
result = 0
def calculate_square(number):
    result = number * number
    return result


def fun():
    var = 100
    print(var)

print(var)

def numbers():
    for i in range(3)
        print(i)

numbers()
print(i)
