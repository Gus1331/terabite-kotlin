package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Marca

@Repository
interface MarcaRepository : JpaRepository<Marca, Int> {
    fun findByNomeIgnoreCase(nomeMarca: String): Marca?
}