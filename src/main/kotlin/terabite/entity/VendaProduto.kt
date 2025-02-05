package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "VENDAS_PRODUTO")
data class VendaProduto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_VENP")
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_VEND_VENP", referencedColumnName = "ID_VEND")
    var venda: Venda? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROD_VENP", referencedColumnName = "ID_PROD")
    var produto: Produto? = null,

    @Column(name = "QTD_PRODUTOS_VENDIDO")
    var qtdProdutosVendido: Int? = null
)
