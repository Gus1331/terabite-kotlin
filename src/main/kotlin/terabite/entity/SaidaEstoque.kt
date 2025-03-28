package terabite.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "SAIDA_ESTOQUE")
data class SaidaEstoque(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_SAID")
    private var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROD_SAID", referencedColumnName = "ID_PROD")
    private val produto: Produto? = null,

    @Column(name = "DATA_SAID")
    private var dtSaida: LocalDate? = null,

    @Column(name = "QTD_CAIXAS_SAID")
    private var qtdCaixasSaida: Int? = null
)