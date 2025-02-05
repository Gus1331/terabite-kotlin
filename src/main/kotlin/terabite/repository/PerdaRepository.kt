package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Perda

@Repository
interface PerdaRepository : JpaRepository<Perda, Int> {
    fun findByProdutoId(produtoId: Int): List<Perda>
}