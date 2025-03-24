package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Destaque
import java.time.LocalDate

@Repository
interface DestaqueRepository: JpaRepository<Destaque, Int> {
    fun findByDtDestaque(dtDestaque: LocalDate): Destaque?

    fun findByDtDestaqueBefore(dtDestaque: LocalDate): List<Destaque>
}