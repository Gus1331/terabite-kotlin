package terabite.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import terabite.entity.Marca
import terabite.repository.MarcaRepository
import kotlin.test.assertEquals

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
}