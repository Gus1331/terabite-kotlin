package terabite.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "RECOMENDACAO")
data class Recomendacao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_RECO")
    var id: Int? = null,

    @OneToOne
    @JoinColumn(name = "FK_ID_PROD_RECO", referencedColumnName = "ID_PROD")
    var produto: Produto? = null,

    @Column(name = "DATA_RECO")
    var dtRecomendacao: LocalDate? = null
)
