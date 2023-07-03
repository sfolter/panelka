package space.dragota.panelka.core.reading

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import space.dragota.panelka.core.BillableEntity
import space.dragota.panelka.core.BillableEntityType

@Repository
interface BillableEntityRepository : CrudRepository<BillableEntity, Long> {
    fun findByIdAndType(apartmentId: Long, type: BillableEntityType): BillableEntity

}