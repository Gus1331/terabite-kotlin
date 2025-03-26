package terabite.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "LOTE")
data class Lote(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_LOTE")
    var id: Int? = null,

    @Column(name = "DATA_PEDIDO_LOTE")
    val dtPedido: LocalDate? = null,

    @Column(name = "DATA_ENTREGA_LOTE")
    var dtEntrega: LocalDate? = null,

    @Column(name = "DATA_VENCIMENTO_LOTE")
    var dtVencimento: LocalDate? = null,

    @Column(name = "QTD_PRODUTO_COMPRADO_LOTE")
    var qtdProdutoComprado: Int? = null,

    @Column(name = "VALOR_LOTE")
    var valorLote: Double? = null,

    @Column(name = "OBSERVACAO_LOTE")
    private var observacao: String? = null

    // adicionar fornecedor / status / loteProdutos
)
