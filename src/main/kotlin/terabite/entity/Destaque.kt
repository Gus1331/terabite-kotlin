package terabite.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "RECOMENDACAO")
data class Destaque(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_DEST")
    var id: Int? = null,

    @OneToOne
    @JoinColumn(name = "FK_ID_PROD_DEST", referencedColumnName = "ID_DEST")
    var produto: Produto? = null,

    @Column(name = "DATA_DEST")
    var dtDestaque: LocalDate? = null,

    @Column(name = "TEXTO_DEST")
    private val texto: String? = null
)
