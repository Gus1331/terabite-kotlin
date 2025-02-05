package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import terabite.entity.Usuario
import java.util.*

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Int> {
    fun findByEmail(email: String): Usuario?
}