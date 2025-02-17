package terabite.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import terabite.entity.Tipo
import terabite.repository.TipoRepository

@ExtendWith(MockitoExtension::class)
@DisplayName("Tipo")
class TipoServiceTest {

    @Mock
    private lateinit var tipoRepository: TipoRepository

    @InjectMocks
    private lateinit var tipoService: TipoService

    private val tipos: MutableList<Tipo> = mutableListOf()

    @BeforeEach
    fun setup(){
        tipos.removeAll(tipos)
        tipos.addAll(
            listOf(
                Tipo(1,"Gelado"),
                Tipo(2, "Quente"),
                Tipo(3, "Sólido"),
                Tipo(4, "Líquido"),
                Tipo(5, "Gasoso"),
            )
        )
    }
}