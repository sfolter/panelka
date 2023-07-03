package space.dragota.panelka.core.uc

import space.dragota.panelka.core.BillableEntity
import space.dragota.panelka.core.reading.BillableEntityRepository

class CreateNewApartment(private val billableEntityRepository: BillableEntityRepository) {

    fun execute(): BillableEntity {
        val billableEntity = BillableEntity()
        val apartment: BillableEntity = billableEntityRepository.save(billableEntity)
        return apartment
    }
}