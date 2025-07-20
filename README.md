## 📦 Sistema de Controle de Estoque

Um sistema web simples para cadastro, edição, visualização e exclusão de produtos. Desenvolvido com **Java + Spring Boot** no backend e **HTML + JavaScript puro + CSS** no frontend.

---
### 🛠️ Tecnologias Utilizadas

#### Backend

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Banco de dados: MySQL

#### Frontend

* HTML5
* CSS3
* JavaScript (sem frameworks)

---

### 🚀 Funcionalidades

* ✅ Adicionar novos produtos
* ✅ Listar produtos cadastrados
* ✅ Editar informações de produtos
* ✅ Excluir produtos

---

### 📂 Estrutura do Projeto

```
estoque/
├── backend/                  # Projeto Spring Boot
│   ├── model/
│   ├── dto/
│   ├── controller/
│   ├── repository/
│   └── application.java
├── frontend/
│   ├── index.html
│   ├── script.js
│   └── style.css (opcional)
```

---



### 🔗 API Endpoints

| Método | Rota                 | Descrição         |
| ------ | -------------------- | ----------------- |
| GET    | `/api/products`      | Listar produtos   |
| POST   | `/api/products`      | Criar produto     |
| PUT    | `/api/products/{id}` | Atualizar produto |
| DELETE | `/api/products/{id}` | Deletar produto   |

---