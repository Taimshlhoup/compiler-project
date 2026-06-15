@app.route('/')
def index():
    username = "Khaled"
    userEmail = "zgkhaled839@gmail.com"

    return render_template('index.html', username=username)