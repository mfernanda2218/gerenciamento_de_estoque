package com.estoque_mercado.estoque.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque_mercado.estoque.model.Categoria;
import com.estoque_mercado.estoque.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    // Injeta a dependência do repositório para interagir com o banco de dados.
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Salva uma nova categoria no banco de dados.
     * @param categoria O objeto Categoria a ser salvo.
     * @return A Categoria salva com o ID gerado.
     */
    public Categoria salvar(Categoria categoria) {
        // Aqui você poderia adicionar lógicas de validação antes de salvar.
        // Por exemplo, verificar se já existe uma categoria com o mesmo nome.
        return categoriaRepository.save(categoria);
    }

    /**
     * Busca todas as categorias cadastradas.
     * @return Uma lista de todas as categorias.
     */
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca uma categoria específica pelo seu ID.
     * @param id O ID da categoria a ser buscada.
     * @return um Optional contendo a categoria se encontrada, ou um Optional vazio caso contrário.
     */
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Atualiza os dados de uma categoria existente.
     * @param id O ID da categoria a ser atualizada.
     * @param categoriaDetalhes O objeto Categoria com as novas informações.
     * @return A Categoria atualizada.
     * @throws EntityNotFoundException se a categoria com o ID fornecido não for encontrada.
     */
    public Categoria atualizar(Long id, Categoria categoriaDetalhes) {
        // Busca a categoria existente no banco de dados.
        // O .orElseThrow() lança uma exceção se a categoria não for encontrada,
        // o que interrompe a execução e evita NullPointerException.
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));

        // Atualiza os campos da entidade existente com os novos dados.
        categoriaExistente.setNome(categoriaDetalhes.getNome());
        categoriaExistente.setDescricao(categoriaDetalhes.getDescricao());

        // Nota: A gestão da lista de 'produtos' geralmente é feita em seus próprios endpoints
        // para evitar complexidade e cargas de dados desnecessárias aqui.

        // Salva a entidade atualizada de volta no banco de dados.
        return categoriaRepository.save(categoriaExistente);
    }

    /**
     * Deleta uma categoria pelo seu ID.
     * @param id O ID da categoria a ser deletada.
     * @throws EntityNotFoundException se a categoria com o ID fornecido não for encontrada.
     */
    public void deletar(Long id) {
        // Verifica se a categoria existe antes de tentar deletar.
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
