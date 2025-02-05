package terabite.entity

import jakarta.persistence.*
import lombok.*

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MARCA")
data class Marca(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_MARCA")
    private var id: Int? = null,

    @Column(name = "NOME_MARCA")
    private val nome: String? = null
)
