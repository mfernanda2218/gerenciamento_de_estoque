const API_URL = "http://localhost:8080/api"; // ajuste conforme seu backend

async function getProdutos() {
    const response = await fetch(`${API_URL}/produtos`);
    return await response.json();
}

async function salvarProduto(produto) {
    await fetch(`${API_URL}/produtos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(produto)
    });
}

async function deletarProduto(id) {
    await fetch(`${API_URL}/produtos/${id}`, { method: "DELETE" });
}
