package terabite.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import terabite.entity.Marca
import terabite.repository.MarcaRepository
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

@ExtendWith(MockitoExtension::class)
@DisplayName("Marca")
class MarcaServiceTest{
    @Mock
    private lateinit var marcaRepository: MarcaRepository

    @InjectMocks
    private lateinit var marcaService:MarcaService

    private val marcasEsperadas: MutableList<Marca> = mutableListOf()

    @BeforeEach
    fun setup(){
        marcasEsperadas.addAll(listOf(
            Marca(1, "Senhor Sorvete"),
            Marca(2, "PimPinella"),
            Marca(3, "Gelone"),
            Marca(4, "Artegel")
        ))
    }

    @Test
    @DisplayName("Quando o banco de dados não possui marcas, o serviço deve lançar ResponseStatusException com status 204 (NO_CONTENT)")
    fun deveLancarExcecaoQuandoNaoExistemMarcas(){
        whenever(marcaRepository.findAll()).thenReturn(mutableListOf<Marca>())

        val methodCall: () -> MutableList<Marca> = { marcaService.listarMarca() }
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.NO_CONTENT, exception.statusCode, "Status HTTP esperado é 204 (NO_CONTENT)")
    }

    @Test
    @DisplayName("Quando o banco de dados possui marcas, o serviço deve retornar a lista correta")
    fun deveRetornarListaDeMarcasQuandoExistirem(){
        whenever(marcaRepository.findAll()).thenReturn(marcasEsperadas)

        val marcasRetornadas: MutableList<Marca> = mutableListOf<Marca>()

        try {
            marcasRetornadas.addAll(marcaService.listarMarca())
        } catch (e: Exception){
            fail("Erro ao buscar marca: " + e.message)
        }

        assertEquals(marcasEsperadas, marcasRetornadas, "As listas retornadas não são iguais às esperadas")
    }

    @Test
    @DisplayName("Quando buscar por ID inexistente, deve lançar ResponseStatusException com status 404 (NOT_FOUND)")
    fun deveLancarExcecaoQuandoNaoEncontrarMarcaPorID(){
        val methodCall: () -> Marca = {marcaService.buscarPorId(2)}
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode, "O status HTTP esperado é 404 (NOT_FOUND)")
    }

    @Test
    @DisplayName("Quando buscar por Id existente, deve retornar a marca correspondente")
    fun deveRetornarMarcaQuandoPassadoSeuId(){
        val marcaEsperada: Marca = marcasEsperadas[0]

        whenever(marcaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(marcaEsperada))

        val resultado: Marca = marcaService.buscarPorId(1)

        assertNotNull(resultado, "A marca retornada não deveria ser nula")
        assertEquals(marcaEsperada.id, resultado.id, "O ID da marca retornada não está correto")
        assertEquals(marcaEsperada.nome, resultado.nome, "O nome da marca retornada não está correto")
    }

    @Test
    @DisplayName("Quando buscar por marca isBlanck, deve lançar exceção 400 (BAD_REQUEST)")
    fun deveLancarExcecaoQuandoPassarNomeVazio(){
        val methodCall: () -> Marca = {marcaService.buscarPorNomeMarca(" ")}
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode, "O status HTTP esperado é 400 (BAD_REQUEST)")
    }

    @Test
    @DisplayName("Quando passar uma marca que não existe no banco de dados, deve cadastrar uma nova marca")
    fun deveCriarNovaMarcaQuandoBuscarPorNomeNaoExistente(){
        val novaMarca:String = "Nova Marca"

        whenever(marcaRepository.findByNomeIgnoreCase(Mockito.anyString())).thenReturn(null)

        val resposta: Marca = marcaService.buscarPorNomeMarca(novaMarca)

        assertNotNull(resposta)
        assertEquals(resposta.nome, novaMarca, "O nome da marca não foi criado corretamente")
    }

    @Test
    @DisplayName("Quando buscar por nome existente em diferentes cases, deve retornar a marca correspondente")
    fun deveRetornarMarcaQuandoPassadoNomeComCasesDiferentes(){
        val marcaEsperada: Marca = Marca(1, "Señor Sorvete")

        whenever(marcaRepository.findByNomeIgnoreCase(Mockito.anyString())).thenReturn(marcaEsperada)
        val resultadoUpperCase: Marca = marcaService.buscarPorNomeMarca("SEÑOR SORVETE")
        val resultadoLowerCase: Marca = marcaService.buscarPorNomeMarca("señor sorvete")
        val resultadoMixedCase: Marca = marcaService.buscarPorNomeMarca("SeÑoR sORVeTE")

        // val validarId: (id1: Int, id2: Int) -> Unit = { assertEquals(id1, id2) }

        assertNotNull(resultadoUpperCase, "A marca retornada com nome em UPPERCASE não deveria ser nula")
        assertNotNull(resultadoLowerCase, "A marca retornada com nome em lowercase não deveria ser nula")
        assertNotNull(resultadoMixedCase, "A marca retornada com nome em MixedCase não deveria ser nula")

        assertEquals(marcaEsperada.id, resultadoUpperCase.id, "O ID da marca retornada (UPPERCASE) não está correto\"")
    }
}