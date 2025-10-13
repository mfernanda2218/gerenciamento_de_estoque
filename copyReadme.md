O projeto foi pensado para simular o sistema de controle de estoque de um **depósito ou mercado**, com foco em **operações de CRUD, controle de entradas e saídas, relatórios exportáveis em CSV** e **alertas de estoque baixo**.

---

# 🧾 **Documentação do Projeto – Sistema de Gerenciamento de Estoque (Full Stack)**

## 📘 **1. Visão Geral**

O **Sistema de Gerenciamento de Estoque** tem como objetivo auxiliar na **organização e controle de produtos** armazenados em um depósito ou mercado.
O sistema permite **cadastrar, editar, excluir e consultar produtos**, **registrar movimentações de entrada e saída**, **gerar relatórios** e **exportar dados em formato CSV**, para fácil análise e integração com planilhas ou outros sistemas.

Este sistema será dividido em:

* **Backend:** Java com Spring Boot
* **Frontend:** HTML, CSS e JavaScript (puro)
* **Banco de dados:** MySQL
* **Comunicação:** REST API (JSON)

---

## 🎯 **2. Objetivos do Sistema**

* Centralizar o controle de produtos em um único sistema.
* Permitir o registro de entradas e saídas de estoque.
* Gerar relatórios e exportá-los em arquivos CSV.
* Emitir alertas visuais e lógicos de estoque baixo.
* Proporcionar uma interface amigável e responsiva.

---

## ⚙️ **3. Requisitos Funcionais**

| Código   | Requisito                  | Descrição                                                                                                              |
| -------- | -------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **RF01** | Cadastrar produto          | O sistema deve permitir cadastrar produtos com nome, código, preço, categoria, quantidade inicial e quantidade mínima. |
| **RF02** | Listar produtos            | O sistema deve listar todos os produtos cadastrados, exibindo informações relevantes.                                  |
| **RF03** | Atualizar produto          | O sistema deve permitir editar informações dos produtos.                                                               |
| **RF04** | Remover produto            | O sistema deve permitir excluir produtos do cadastro.                                                                  |
| **RF05** | Registrar entrada          | O sistema deve registrar a entrada de novas unidades de um produto.                                                    |
| **RF06** | Registrar saída            | O sistema deve registrar a saída (venda ou perda) de produtos, atualizando o estoque.                                  |
| **RF07** | Alerta de estoque baixo    | O sistema deve sinalizar produtos com quantidade abaixo do limite mínimo definido.                                     |
| **RF08** | Relatórios de estoque      | O sistema deve gerar relatórios com estatísticas (quantidades, valores totais, produtos críticos etc.).                |
| **RF09** | Exportar relatórios em CSV | O sistema deve exportar dados de produtos e relatórios para arquivos CSV.                                              |
| **RF10** | Busca e filtro             | O sistema deve permitir pesquisar produtos por nome, categoria ou código.                                              |
| **RF11** | Interface web              | O sistema deve possuir uma interface web responsiva e interativa.                                                      |

---

## 🚫 **4. Requisitos Não Funcionais**

| Tipo                | Descrição                                                                          |
| ------------------- | ---------------------------------------------------------------------------------- |
| **Desempenho**      | O sistema deve responder a requisições em menos de 2 segundos.                     |
| **Segurança**       | O backend deve validar todas as requisições e proteger contra inserções inválidas. |
| **Persistência**    | Os dados devem ser armazenados em um banco MySQL.                                  |
| **Compatibilidade** | O sistema deve funcionar nos principais navegadores modernos.                      |
| **Usabilidade**     | A interface deve ser simples e intuitiva, com menus e feedback visual.             |
| **Exportação**      | A exportação deve seguir o padrão CSV com delimitador `;`.                         |

---

## 🧩 **5. Modelagem do Sistema**

### 🧱 **Entidade Produto**

| Campo              | Tipo          | Descrição                                              |
| ------------------ | ------------- | ------------------------------------------------------ |
| `id`               | Long          | Identificador único do produto                         |
| `codigo`           | String        | Código interno ou de barras                            |
| `nome`             | String        | Nome do produto                                        |
| `categoria`        | String        | Categoria do produto (ex: bebidas, alimentos, limpeza) |
| `precoUnitario`    | Double        | Preço unitário de venda                                |
| `quantidade`       | Integer       | Quantidade atual em estoque                            |
| `quantidadeMinima` | Integer       | Quantidade mínima para alerta                          |
| `dataCadastro`     | LocalDateTime | Data/hora do cadastro                                  |

---

### ⚙️ **Modelo ER (Entidade-Relacionamento)**

```
[Produto]
 ├── id (PK)
 ├── codigo
 ├── nome
 ├── categoria
 ├── precoUnitario
 ├── quantidade
 ├── quantidadeMinima
 └── dataCadastro
```

> (Opcionalmente, podem ser criadas entidades de “Movimentação” para registrar entradas e saídas detalhadamente.)

---

## 🧠 **6. Casos de Uso Principais**

### **UC01 - Cadastrar Produto**

**Ator:** Administrador
**Descrição:** Permite adicionar um novo produto ao sistema.
**Fluxo principal:**

1. Usuário acessa o formulário de cadastro.
2. Preenche os campos obrigatórios.
3. Sistema valida e salva no banco.
4. Exibe mensagem de sucesso.

---

### **UC02 - Registrar Entrada**

**Ator:** Funcionário do estoque
**Descrição:** Aumenta a quantidade de um produto no estoque.
**Regras:** Não permitir quantidades negativas.

---

### **UC03 - Registrar Saída**

**Ator:** Funcionário do estoque
**Descrição:** Diminui a quantidade em estoque devido a venda ou descarte.
**Regras:**

* Não permitir que a quantidade final seja menor que zero.
* Registrar a data da operação.

---

### **UC04 - Exportar Relatório CSV**

**Ator:** Administrador
**Descrição:** Gera um arquivo CSV com dados dos produtos e estatísticas gerais.
**Formato do CSV:**

```
Código;Nome;Categoria;Preço;Quantidade;Qtd. Mínima;Valor Total
1234;Arroz 5kg;Alimentos;25.90;15;5;388.50
...
```

---

## 🖥️ **7. Arquitetura do Sistema**

### **Backend (Spring Boot)**

* **Camadas:**

    * `controller` → expõe endpoints REST
    * `service` → lógica de negócio
    * `repository` → interface com o banco via Spring Data JPA
    * `model` → classes de entidade (Produto)
    * `exporter` → utilitário para gerar arquivos CSV

* **Endpoints principais:**

    * `GET /api/produtos` → listar todos
    * `GET /api/produtos/{id}` → buscar por ID
    * `POST /api/produtos` → cadastrar
    * `PUT /api/produtos/{id}` → atualizar
    * `DELETE /api/produtos/{id}` → remover
    * `POST /api/produtos/{id}/entrada` → registrar entrada
    * `POST /api/produtos/{id}/saida` → registrar saída
    * `GET /api/produtos/relatorio` → gerar relatório
    * `GET /api/produtos/exportar` → exportar CSV

---

### **Frontend (HTML, CSS e JavaScript)**

* **Páginas:**

    * `index.html` → listagem e busca de produtos
    * `cadastro.html` → formulário de cadastro/edição
    * `movimentacao.html` → registrar entrada/saída
    * `relatorio.html` → exibir relatórios e exportar CSV

* **JavaScript (fetch API):**

    * `listarProdutos()` → consome `/api/produtos`
    * `cadastrarProduto()` → consome `POST /api/produtos`
    * `editarProduto()` → consome `PUT /api/produtos/{id}`
    * `exportarCSV()` → consome `/api/produtos/exportar` e força download

* **CSS:**

    * Layout responsivo (flex/grid)
    * Cores indicativas de estoque:

        * Verde → normal
        * Amarelo → próximo ao mínimo
        * Vermelho → abaixo do mínimo

---

## 📊 **8. Relatórios e Exportação CSV**

### **Relatórios disponíveis:**

* Produtos com estoque baixo
* Valor total em estoque (`Σ (quantidade * preço)`)
* Quantidade total de produtos
* Categorias com mais produtos

### **Exportação CSV (backend):**

```java
@GetMapping("/api/produtos/exportar")
public ResponseEntity<Resource> exportarCsv() {
    String csv = produtoService.gerarCsv();
    ByteArrayResource resource = new ByteArrayResource(csv.getBytes(StandardCharsets.UTF_8));
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_estoque.csv")
        .contentType(MediaType.parseMediaType("text/csv"))
        .body(resource);
}
```

---

## 🧱 **9. Regras de Negócio**

* Quantidade mínima ≥ 0
* Quantidade atual ≥ 0
* Preço unitário > 0
* Não permitir duplicação de código de produto
* Não permitir saída de estoque acima da quantidade existente
* Alertar visualmente quando `quantidade < quantidadeMinima`

---

## 📂 **10. Estrutura de Pastas (sugerida)**

```
estoque/
├── backend/
│   ├── src/main/java/com/estoque
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── util/exporter/
│   └── src/main/resources/
│       └── application.properties
├── frontend/
│   ├── index.html
│   ├── cadastro.html
│   ├── relatorio.html
│   ├── css/
│   └── js/
└── README.md
```

---

## 🚀 **11. Extensões Futuras**

* Autenticação de usuários (login e permissões).
* Dashboard com gráficos (via Chart.js).
* Registro de movimentações detalhadas (histórico de entradas e saídas).
* Exportação em PDF.
* Deploy na nuvem (Railway, Render, ou AWS).

---

## ✅ **12. Conclusão**

Este projeto representa um **ERP simplificado para controle de estoque**, aplicável a qualquer depósito ou mercado.
Sua arquitetura modular e tecnologias amplamente usadas (Spring Boot + HTML/JS) permitem **fácil manutenção, escalabilidade e integração com outros sistemas**.

---
