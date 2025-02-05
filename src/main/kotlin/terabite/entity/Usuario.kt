package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "USUARIO")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_USUARIO")
    var id: Int? = null,

    @Column(name = "EMAIL_USUARIO")
    var email: String? = null,

    @Column(name = "SENHA_USUARIO")
    var senha: String? = null
)
