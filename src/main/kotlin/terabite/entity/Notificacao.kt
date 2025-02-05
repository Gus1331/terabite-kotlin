package terabite.entity

import jakarta.persistence.*

@Entity
@Table(name = "NOTIFICACAO")
data class Notificacao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID_NOTI")
    private var id: Int? = null,

    @Column(name = "EMAIL_NOTI")
    private val email: String? = null,

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROD_NOTI", referencedColumnName = "ID_PROD")
    private val produto: Produto? = null
)
