package terabite.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import terabite.entity.VendaProduto

@Repository
interface VendaProdutoRepository: JpaRepository<VendaProduto, Int> {
    fun findByVendaId(vendaId: Int): List<VendaProduto>

    fun findByProdutoId(produtoId: Int): List<VendaProduto>

    fun deleteByVendaId(vendaId: Int)

    @Query("SELECT vp FROM Venda v JOIN v.produtos vp WHERE MONTH(v.dataCompra) = :mes AND YEAR(v.dataCompra) = :ano")
    fun procurarVendasPorMesEAno(@Param("mes") mes: Int, @Param("ano") ano: Int): List<VendaProduto>


    //@Query("SELECT new grupo.terabite.terabite.dto.internal.ProdutoQuantidadeDTO(vp.produto, SUM(vp.qtdProdutosVendido)) FROM VendaProduto vp WHERE MONTH(vp.venda.dataCompra) = :mes AND YEAR(vp.venda.dataCompra) = :ano GROUP BY vp.produto")
    //fun qtdVendidosPorMesEAno(@Param("mes") mes: Int, @Param("ano") ano: Int): List<ProdutoQuantidadeDTO>
}