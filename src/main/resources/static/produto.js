document.addEventListener("DOMContentLoaded", carregarProdutos);

async function carregarProdutos() {
    const produtos = await getProdutos();
    const tabela = document.querySelector("#tabela-produtos tbody");
    tabela.innerHTML = "";
    produtos.forEach(p => {
        tabela.innerHTML += `
            <tr>
                <td>${p.id}</td>
                <td>${p.nome}</td>
                <td>${p.quantidade}</td>
                <td>R$ ${p.preco.toFixed(2)}</td>
                <td><button onclick="deletar(${p.id})">Excluir</button></td>
            </tr>
        `;
    });
}

document.querySelector("#form-produto").addEventListener("submit", async e => {
    e.preventDefault();
    const produto = {
        nome: document.querySelector("#nome").value,
        quantidade: parseInt(document.querySelector("#quantidade").value),
        preco: parseFloat(document.querySelector("#preco").value)
    };
    await salvarProduto(produto);
    carregarProdutos();
});

async function deletar(id) {
    await deletarProduto(id);
    carregarProdutos();
}

function mostrarSecao(id) {
    document.querySelectorAll(".secao").forEach(sec => sec.style.display = "none");
    document.getElementById(id).style.display = "block";
}
