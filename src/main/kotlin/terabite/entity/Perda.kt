package terabite.entity

import jakarta.persistence.*
import lombok.*
import java.time.LocalDate

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PERDA")
data class Perda(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_PERD")
    var id: Int? = null,

    @Column(name = "QTD_PRODUTO_PERD")
    val qtdProduto: Int? = null,

    @Column(name = "DT_PERDA_PERD")
    var dataPerda: LocalDate? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROD_PERD", referencedColumnName = "ID_PROD")
    val produto: Produto? = null
)
