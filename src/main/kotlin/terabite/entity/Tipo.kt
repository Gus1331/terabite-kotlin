package terabite.entity

import jakarta.persistence.*
import lombok.*

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIPO")
data class Tipo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_TIPO")
    private var id: Int? = null,

    @Column(name = "NOME_TIPO")
    private val nome: String? = null
)
