package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "LOTE_PRODUTO")
data class LoteProduto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LP")
    private var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_LOTE_LP", referencedColumnName = "ID_LOTE")
    private val lote: Lote? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROD_LP", referencedColumnName = "ID_PROD")
    private val produto: Produto? = null,

    @Column(name = "QTD_CAIXAS_COMPRADAS_LP")
    private var qtdCaixasCompradas: Int? = null
)