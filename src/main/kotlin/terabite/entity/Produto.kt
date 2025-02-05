package terabite.entity

import jakarta.persistence.*
import lombok.*

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRODUTO")
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_PROD")
    private var id:Int? = null,

    @Column(name = "NOME_PROD")
    private var nome: String? = null,

    @ManyToOne @JoinColumn(name = "FK_ID_SUBT_PROD", referencedColumnName = "ID_SUBT")
    private var subtipo: Subtipo? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_MARCA_PROD", referencedColumnName = "ID_MARCA")
    private val marca: Marca? = null,

    @Column(name = "PRECO_PROD")
    private var preco: Double? = null,

    @Column(name = "IS_ATIVO_PROD")
    private var isAtivo: Boolean = true,

    @Column(name = "EM_ESTOQUE_PROD")
    private var emEstoque: Boolean? = null
)
