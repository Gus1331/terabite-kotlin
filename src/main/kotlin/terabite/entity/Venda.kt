package terabite.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "VENDAS")
data class Venda(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_VEND")
    var id: Int? = null,

    @Column(name = "DATA_COMPRA_VEND")
    var dataCompra: LocalDateTime? = null,

    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY)
    var produtos: List<VendaProduto>? = null
){

}
