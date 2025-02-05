package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Lote

@Repository
interface LoteRepository : JpaRepository<Lote, Int> {
    fun findByProdutoId(produtoId: Int): List<Lote>
}