package space.dragota.panelka.core

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class BillableEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(nullable = false)
        var type: BillableEntityType = BillableEntityType.APARTMENT,

)





