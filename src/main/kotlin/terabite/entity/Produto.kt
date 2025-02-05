package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "PRODUTO")
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_PROD")
    var id:Int? = null,

    @Column(name = "NOME_PROD")
    var nome: String? = null,

    @ManyToOne @JoinColumn(name = "FK_ID_SUBT_PROD", referencedColumnName = "ID_SUBT")
    var subtipo: Subtipo? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_MARCA_PROD", referencedColumnName = "ID_MARCA")
    var marca: Marca? = null,

    @Column(name = "PRECO_PROD")
    var preco: Double? = null,

    @Column(name = "IS_ATIVO_PROD")
    var isAtivo: Boolean = true,

    @Column(name = "EM_ESTOQUE_PROD")
    var emEstoque: Boolean? = null
)
