package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Venda
import java.time.LocalDateTime

@Repository
interface VendaRepository: JpaRepository<Venda, Int> {
    fun findAllByOrderByDataCompraDesc(): List<Venda>
    fun findByDataCompraBetween(inicioDoDia: LocalDateTime, finalDoDia: LocalDateTime): List<Venda>
}