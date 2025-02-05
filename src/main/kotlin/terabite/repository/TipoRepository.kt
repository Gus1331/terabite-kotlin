package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Tipo

@Repository
interface TipoRepository: JpaRepository<Tipo, Int> {
    fun findByNomeIgnoreCase(tipo: String): Tipo?
}