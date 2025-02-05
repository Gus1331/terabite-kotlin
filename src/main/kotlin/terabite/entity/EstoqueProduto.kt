package terabite.entity

data class EstoqueProduto(
    var codigo: Int? = null,
    val produto: String? = null,
    val marca: String? = null,
    val valorUnitario: Double? = null,
    val qtdEstoque: Int? = null
)
