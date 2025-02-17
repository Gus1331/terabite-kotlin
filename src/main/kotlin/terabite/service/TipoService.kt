package terabite.service

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import terabite.entity.Tipo
import terabite.repository.TipoRepository

@Service
class TipoService(
    private val tipoRepository: TipoRepository
) {
    fun listarTipo(): List<Tipo> {
        val tipos: List<Tipo> = tipoRepository.findAll()
        if (tipos.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NO_CONTENT)
        }

        return tipos
    }

    fun buscarPorId(id: Int): Tipo {
        return tipoRepository.findById(id).orElseThrow{ResponseStatusException(HttpStatus.NOT_FOUND)}
    }

    fun buscarNomePorTipo(nomeTipo: String): Tipo{
        if(nomeTipo.isBlank()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        val tipo: Tipo? = tipoRepository.findByNomeIgnoreCase(nomeTipo)

        if(tipo == null){
            return tipoRepository.save(Tipo(null, nomeTipo))
        }

        return tipo
    }

    fun criarTipo(novoTipo: Tipo): Tipo{
        return tipoRepository.save(novoTipo)
    }
}