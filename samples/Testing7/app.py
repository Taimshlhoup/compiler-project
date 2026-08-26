product1_name = "Phone"
product1_price = 300
product2_name = "Laptop"
product2_price = 800

@app.route('/')
def index():
    return render_template('index.jinja', product1_name=product1_name, product1_price=product1_price, product2_name=product2_name, product2_price=product2_price)

@app.route('/add')
def add():
    if request.method == 'POST':
        return redirect('/')
    return render_template('add_product.jinja')

@app.route('/detail')
def detail():
    return render_template('detail.jinja', product1_name=product1_name, product1_price=product1_price)

@app.route('/delete')
def delete():
    return redirect('/')