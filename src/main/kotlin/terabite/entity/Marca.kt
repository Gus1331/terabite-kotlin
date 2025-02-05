package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "MARCA")
data class Marca(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_MARCA")
    var id: Int? = null,

    @Column(name = "NOME_MARCA")
    var nome: String? = null
)
