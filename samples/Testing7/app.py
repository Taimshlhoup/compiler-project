products = [{"name": "Phone", "price": 300}, {"name": "Laptop", "price": 800}]

@app.route('/')
def index():
    return render_template('index.jinja', products=products)

@app.route('/add')
def add():
    if request.method == 'POST':
        return redirect('/')
    return render_template('add_product.jinja')

@app.route('/detail')
def detail():
    return render_template('detail.jinja')

@app.route('/delete')
def delete():
    return redirect('/')