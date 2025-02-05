package terabite.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUBTIPO")
data class Subtipo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_SUBT")
    private var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_TIPO_PAI", referencedColumnName = "ID_TIPO")
    private val tipoPai: Tipo? = null,

    @Column(name = "NOME_SUBT")
    private var nome: String? = null
)
