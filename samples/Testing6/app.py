@app.route('/')
def index():
    username = "Khaled"
    age = 25
    return render_template('index.html', username=username, age=age)