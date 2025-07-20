const form = document.getElementById('productForm');
const list = document.getElementById('productList');
const categorySelect = document.getElementById('categoryId');
const newCategoryInput = document.getElementById('newCategoryInput');
const addCategoryBtn = document.getElementById('addCategoryBtn');

const API_URL = 'http://localhost:8080/api/products';
const CATEGORY_API = 'http://localhost:8080/api/categories';

let categoryMap = {}; // Map de id → nome da categoria

// Submissão do formulário
form.onsubmit = async (e) => {
    e.preventDefault();
    const id = form.productId.value;

    const data = {
        name: form.name.value,
        quantity: parseInt(form.quantity.value),
        price: parseFloat(form.price.value),
        categoryId: categorySelect.value ? parseInt(categorySelect.value) : null
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
        const catName = categoryMap[p.categoryId] || 'Nenhuma';

        item.textContent = `${p.name} - Qtd: ${p.quantity} - R$${p.price.toFixed(2)} - Categoria: ${catName}`;

        const editBtn = document.createElement('button');
        editBtn.textContent = 'Editar';
        editBtn.onclick = () => {
            form.productId.value = p.id;
            form.name.value = p.name;
            form.quantity.value = p.quantity;
            form.price.value = p.price;
            categorySelect.value = p.categoryId || '';
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

// Carregar categorias e atualizar o select
async function loadCategories() {
    try {
        const res = await fetch(CATEGORY_API);
        const categories = await res.json();

        // Atualiza o map: id → nome
        categoryMap = {};
        categories.forEach(cat => {
            categoryMap[cat.id] = cat.name;
        });

        categorySelect.innerHTML = '<option value="">-- Selecione uma categoria --</option>';
        categories.forEach(cat => {
            const opt = document.createElement("option");
            opt.value = cat.id;
            opt.textContent = cat.name;
            categorySelect.appendChild(opt);
        });
    } catch (err) {
        console.error("Erro ao carregar categorias:", err);
    }
}

// Adicionar nova categoria
async function createCategory() {
    const name = newCategoryInput.value.trim();
    if (!name) {
        alert("Informe o nome da nova categoria.");
        return;
    }

    try {
        const res = await fetch(CATEGORY_API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name })
        });

        if (!res.ok) throw new Error("Erro ao criar categoria");

        const newCategory = await res.json();
        newCategoryInput.value = '';
        await loadCategories();
        categorySelect.value = newCategory.id;
    } catch (err) {
        console.error("Erro ao adicionar categoria:", err);
        alert("Erro ao adicionar categoria. Veja o console.");
    }
}

addCategoryBtn.addEventListener('click', createCategory);

// Inicialização
(async () => {
    await loadCategories();
    await loadProducts();
})();
