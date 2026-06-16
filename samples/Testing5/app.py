def greet(name, age):
    print(name)

def add(x, y):
    print(x)

# 1. Undefined variable
result = c + 5

# 2. Type Mismatch
x = 5
x = "hello"

# 3. Type Error
y = "text" - 2

# 4. Scope Error


# 5. Undefined Function
bye()

# 6. Wrong Number of Arguments
greet("Ali")

# 7. Division by Zero (literal)
z = 10 / 0

# 8. Division by Zero (variable)
a = 0
b = 10 / a

@app.route('/')
def index():
    username = "Khaled"
    age = 25
    return render_template('index.html', username=username)