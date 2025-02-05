package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Recomendacao
import java.time.LocalDate

@Repository
interface RecomendacaoRepository: JpaRepository<Recomendacao, Int> {
    fun findByDtRecomendacao(dtRecomendacao: LocalDate): Recomendacao?

    fun findByDtRecomendacaoBefore(dtRecomendacao: LocalDate): List<Recomendacao>
}