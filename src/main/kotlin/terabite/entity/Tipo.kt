package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "TIPO")
data class Tipo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_TIPO")
    var id: Int? = null,

    @Column(name = "NOME_TIPO")
    var nome: String? = null
)
