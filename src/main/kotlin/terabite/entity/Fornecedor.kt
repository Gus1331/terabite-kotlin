package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "FORNECEDOR")
class Fornecedor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_FORN")
    private var id: Int? = null,

    @Column(name = "NOME_FORN")
    private var nome: String? = null
) {
}