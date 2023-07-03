package space.dragota.panelka.core.reading

import jakarta.persistence.*
import space.dragota.panelka.core.BillableEntity

@Entity
class Reading constructor(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "apartment_id")
        val apartment: BillableEntity,
        val type: Type,
        val value: Long
)




