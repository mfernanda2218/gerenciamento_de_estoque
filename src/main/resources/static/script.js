const form = document.getElementById('productForm');
const list = document.getElementById('productList');

const API_URL = 'http://localhost:8080/api/products';

// Submissão do formulário
form.onsubmit = async (e) => {
    e.preventDefault();
    const id = form.productId.value;

    const data = {
        name: form.name.value,
        quantity: parseInt(form.quantity.value),
        price: parseFloat(form.price.value)
    };

    const method = id ? 'PUT' : 'POST';
    const endpoint = id ? `${API_URL}/${id}` : API_URL;

    await fetch(endpoint, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });

    form.reset();
    await loadProducts();
};

// Carregar produtos e exibir na lista
async function loadProducts() {
    const res = await fetch(API_URL);
    const products = await res.json();
    list.innerHTML = '';
    products.forEach(p => {
        const item = document.createElement('li');
        item.textContent = `${p.name} - Qtd: ${p.quantity} - R$${p.price.toFixed(2)}`;

        const editBtn = document.createElement('button');
        editBtn.textContent = 'Editar';
        editBtn.onclick = () => {
            form.productId.value = p.id;
            form.name.value = p.name;
            form.quantity.value = p.quantity;
            form.price.value = p.price;
        };

        const delBtn = document.createElement('button');
        delBtn.textContent = 'Excluir';
        delBtn.onclick = async () => {
            await fetch(`${API_URL}/${p.id}`, { method: 'DELETE' });
            loadProducts();
        };

        item.appendChild(editBtn);
        item.appendChild(delBtn);
        list.appendChild(item);
    });
}

// Inicialização
loadProducts();
