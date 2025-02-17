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
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import terabite.entity.Marca
import terabite.repository.MarcaRepository
import java.util.Optional
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail

@ExtendWith(MockitoExtension::class)
@DisplayName("Marca")
class MarcaServiceTest {
    @Mock
    private lateinit var marcaRepository: MarcaRepository

    @InjectMocks
    private lateinit var marcaService: MarcaService

    private val marcasEsperadas: MutableList<Marca> = mutableListOf()

    @BeforeEach
    fun setup() {
        marcasEsperadas.removeAll(marcasEsperadas)
        marcasEsperadas.addAll(
            listOf(
                Marca(1, "Senhor Sorvete"),
                Marca(2, "PimPinella"),
                Marca(3, "Gelone"),
                Marca(4, "Artegel")
            )
        )
    }

    @Test
    @DisplayName("Quando o banco de dados não possui marcas, o serviço deve lançar ResponseStatusException com status 204 (NO_CONTENT)")
    fun deveLancarExcecaoQuandoNaoExistemMarcas() {
        whenever(marcaRepository.findAll()).thenReturn(mutableListOf<Marca>())

        val methodCall: () -> MutableList<Marca> = { marcaService.listarMarca() }
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.NO_CONTENT, exception.statusCode, "Status HTTP esperado é 204 (NO_CONTENT)")
    }

    @Test
    @DisplayName("Quando o banco de dados possui marcas, o serviço deve retornar a lista correta")
    fun deveRetornarListaDeMarcasQuandoExistirem() {
        whenever(marcaRepository.findAll()).thenReturn(marcasEsperadas)

        val marcasRetornadas: MutableList<Marca> = mutableListOf<Marca>()

        try {
            marcasRetornadas.addAll(marcaService.listarMarca())
        } catch (e: Exception) {
            fail("Erro ao buscar marca: " + e.message)
        }

        assertEquals(marcasEsperadas, marcasRetornadas, "As listas retornadas não são iguais às esperadas")
    }

    @Test
    @DisplayName("Quando buscar por ID inexistente, deve lançar ResponseStatusException com status 404 (NOT_FOUND)")
    fun deveLancarExcecaoQuandoNaoEncontrarMarcaPorID() {
        val methodCall: () -> Marca = { marcaService.buscarPorId(2) }
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode, "O status HTTP esperado é 404 (NOT_FOUND)")
    }

    @Test
    @DisplayName("Quando buscar por Id existente, deve retornar a marca correspondente")
    fun deveRetornarMarcaQuandoPassadoSeuId() {
        val marcaEsperada: Marca = marcasEsperadas[0]

        whenever(marcaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(marcaEsperada))

        val resultado: Marca = marcaService.buscarPorId(1)

        assertNotNull(resultado, "A marca retornada não deveria ser nula")
        assertEquals(marcaEsperada.id, resultado.id, "O ID da marca retornada não está correto")
        assertEquals(marcaEsperada.nome, resultado.nome, "O nome da marca retornada não está correto")
    }

    @Test
    @DisplayName("Quando buscar por marca isBlanck, deve lançar exceção 400 (BAD_REQUEST)")
    fun deveLancarExcecaoQuandoPassarNomeVazio() {
        val methodCall: () -> Marca = { marcaService.buscarPorNomeMarca(" ") }
        val exception: ResponseStatusException = assertThrows<ResponseStatusException> { methodCall() }

        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode, "O status HTTP esperado é 400 (BAD_REQUEST)")
    }

    @Test
    @DisplayName("Quando passar uma marca que não existe no banco de dados, deve cadastrar uma nova marca")
    fun deveCriarNovaMarcaQuandoBuscarPorNomeNaoExistente() {
        val novaMarca: String = "Nova Marca"
        val marcaEsperada: Marca = Marca(100, novaMarca)

        whenever(marcaRepository.findByNomeIgnoreCase(novaMarca)).thenReturn(null)
        whenever(marcaRepository.save(any<Marca>())).thenReturn(marcaEsperada)

        val resposta: Marca = marcaService.buscarPorNomeMarca(novaMarca)

        assertNotNull(resposta, "A marca retornada não deveria ser nula")
        assertEquals(resposta.nome, novaMarca, "O nome da marca não foi criado corretamente")
    }

    @Test
    @DisplayName("Quando buscar por nome existente em diferentes cases, deve retornar a marca correspondente")
    fun deveRetornarMarcaQuandoPassadoNomeComCasesDiferentes() {
        val marcaEsperada: Marca = Marca(1, "Señor Sorvete")

        whenever(marcaRepository.findByNomeIgnoreCase(Mockito.anyString())).thenReturn(marcaEsperada)
        val cenarios: List<Marca> = listOf(
            marcaService.buscarPorNomeMarca("SEÑOR SORVETE"),
            marcaService.buscarPorNomeMarca("señor sorvete"),
            marcaService.buscarPorNomeMarca("SeÑoR sORVeTE")
        )

        val validarNull: (marca: Marca) -> Unit =
            { marca -> assertNotNull(marca, "A marca retornada não deveria ser nula") }
        val validarId: (marca: Marca) -> Unit =
            { marca -> assertEquals(marcaEsperada.id, marca.id, "O ID da marca retornada (UPPERCASE) não está correto") }
        val validarNome: (marca: Marca) -> Unit =
            { marca -> assertEquals(marcaEsperada.nome, marca.nome, "O nome da marca retornada não está correto") }

        cenarios.forEach {
            validarNull(it)
            validarId(it)
            validarNome(it)
        }
    }

    @Test
    @DisplayName("Quando criar uma nova marca, deve definir o ID como null e salvar no repositório")
    fun deveCriarNovaMarca(){
        val marcaSalva = marcasEsperadas[0]
        val novaMarca: Marca = marcasEsperadas[0]
        novaMarca.id = null

        whenever(marcaRepository.save(any<Marca>())).thenReturn(marcaSalva)

        val marcaRetornada = marcaService.criarMarca(novaMarca)

        assertNotNull(marcaRetornada, "O resultado não deve ser nulo")
        assertEquals(marcaSalva.id, marcaRetornada.id, "O ID da marca salva não está correto")
        assertEquals(marcaSalva.nome, marcaRetornada.nome, "O nome da marca salva não está correto")

        verify(marcaRepository).save(any<Marca>())
    }

    @Test
    @DisplayName("Quando passar um Id que não existe no banco de dados para atualizar a marca, deve lançar exceção 404 (NOT_FOUND)")
    fun deveLancarExcecaoQuandoNaoExistirMarcaPorIdPassadoMetodoAtualizarMarca(){
        val exception: ResponseStatusException = assertThrows<ResponseStatusException>{marcaService.atualizarMarca(99, marcasEsperadas[0])}
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode, "O status HTTP esperado é 404 (NOT FOUND)")
    }

    @Test
    @DisplayName("Quando passar uma marca que existe no banco de dados, deve atualizar marca")
    fun deveAtualizarMarcaSeIdExistir(){
        val marcaAtualizada: Marca = Marca(12, "Nova Marca")

        whenever(marcaRepository.existsById(marcasEsperadas[0].id!!)).thenReturn(true)
        whenever(marcaRepository.save(any<Marca>())).thenReturn(marcaAtualizada)

        val marcaRetornada: Marca = marcaService.atualizarMarca(marcasEsperadas[0].id!!, marcaAtualizada)

        assertNotNull(marcaRetornada, "O resultado não deve ser nulo")
        assertEquals(marcaAtualizada.nome, marcaRetornada.nome, "O nome da marca retornado não é diferente do esperado")
    }

    @Test
    @DisplayName("Quando passar um Id que não existe no banco de dados para deletar a marca, deve lançar exceção 404 (NOT_FOUND)")
    fun deveLancarExcecaoQuandoNaoExistirMarcaPorIdPassadoNoMetodoDeletar(){
        val exception: ResponseStatusException = assertThrows<ResponseStatusException>{marcaService.deletarMarca(99)}
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode, "O status HTTP esperado é 404 (NOT FOUND)")
    }

    @Test
    @DisplayName("Quando passar um Id que existe no banco de dados, deve deletar se a marca por id")
    fun deveDeletarMarcaSeExistende(){
        whenever(marcaRepository.existsById(1)).thenReturn(true)

        marcaService.deletarMarca(1)

        verify(marcaRepository).existsById(1)
        verify(marcaRepository).deleteById(1)
    }
}