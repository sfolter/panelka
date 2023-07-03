package space.dragota.panelka.core.uc

import space.dragota.panelka.core.BillableEntity
import space.dragota.panelka.core.BillableEntityType
import space.dragota.panelka.core.reading.BillableEntityRepository
import space.dragota.panelka.core.reading.Reading
import space.dragota.panelka.core.reading.ReadingRepository
import space.dragota.panelka.core.reading.Type

class AcceptNewReading(
        private val readingRepository: ReadingRepository,
        private val billableEntityRepository: BillableEntityRepository
        ) {

    fun execute(apartmentId: Long, readingType: Type, newReadingVal: Long): Reading {

        val billableEntity = billableEntityRepository.findByIdAndType(apartmentId, BillableEntityType.APARTMENT)
        val reading = Reading(apartment = billableEntity, type = readingType, value = newReadingVal)
        validateReading(reading)
        return readingRepository.save(reading)
    }

    private fun validateReading(reading: Reading) {
        val latestReading = readingRepository.findByApartmentIdAndType(reading.apartment.id!!, reading.type)

        if (latestReading != null && reading.value < latestReading.value) {
            throw IllegalArgumentException("New reading must be greater than or equal to the latest reading")
        }
    }
}



