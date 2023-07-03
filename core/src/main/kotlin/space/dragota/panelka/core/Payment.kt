package space.dragota.panelka.core

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    val amount: BigDecimal? = null
}