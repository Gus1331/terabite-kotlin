package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Subtipo

@Repository
interface SubtipoRepository: JpaRepository<Subtipo, Int> {
    fun findByNomeIgnoreCase(subtipo: String): Subtipo?
}