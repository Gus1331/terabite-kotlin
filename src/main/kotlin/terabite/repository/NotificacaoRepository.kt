package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Notificacao

@Repository
interface NotificacaoRepository : JpaRepository<Notificacao, Int> {
    fun findByEmailAndProdutoId(email: String, produtoId: Int): List<Notificacao>

    fun findByProdutoId(produtoId: Int): List<Notificacao>

    fun deleteByProdutoId(produtoId: Int)
}