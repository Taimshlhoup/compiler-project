products = []
@app.route('/')
def index():
    return render_template('index.jinja', products=products)