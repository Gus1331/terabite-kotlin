package terabite.service

import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import terabite.entity.Marca
import terabite.repository.MarcaRepository

@Service
class MarcaService(private val marcaRepository: MarcaRepository) {
    fun listarMarca(): MutableList<Marca>{
        var marcas: MutableList<Marca> = marcaRepository.findAll()

        if(marcas.isEmpty()){
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }

        return marcas
    }

    fun buscarPorId(id:Int): Marca{
        return marcaRepository.findById(id).orElseThrow{ResponseStatusException(HttpStatusCode.valueOf(404))}
    }

    fun buscarPorNomeMarca(nomeMarca: String): Marca{
        var marca = marcaRepository.findByNomeIgnoreCase(nomeMarca)

        if(marca != null){
            return  marca
        }

        return criarMarca(Marca(null,  nomeMarca))
    }

    fun criarMarca(novaMarca: Marca): Marca{
        novaMarca.id = null
        return marcaRepository.save(novaMarca)
    }

    fun atualizarMarca(id: Int, marca: Marca): Marca{
        if(!marcaRepository.existsById(id)){ throw ResponseStatusException(HttpStatusCode.valueOf(404))}

        marca.id = id
        return marcaRepository.save(marca);
    }

    fun deletarMarca(id: Int){
        if(!marcaRepository.existsById(id)){ throw ResponseStatusException(HttpStatusCode.valueOf(404))}

        marcaRepository.deleteById(id)
    }
}