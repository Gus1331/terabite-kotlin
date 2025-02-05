package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Produto

@Repository
interface ProdutoRepository: JpaRepository<Produto, Int> {
    fun findByNomeIgnoreCase(nome: String): Produto?

    fun findByIsAtivoTrue(): List<Produto>

    fun findByNomeContainingIgnoreCaseOrMarca_NomeContainingIgnoreCase(termo: String, termoMarca: String): List<Produto>

    fun findByNomeIgnoreCaseContainingOrSubtipo_TipoPai_NomeIgnoreCaseContaining(termo: String, termoTipo: String): List<Produto>
}