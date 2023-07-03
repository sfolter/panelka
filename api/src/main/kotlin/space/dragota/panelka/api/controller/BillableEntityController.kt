package space.dragota.panelka.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import space.dragota.panelka.core.BillableEntity
import space.dragota.panelka.core.reading.BillableEntityRepository
import space.dragota.panelka.core.reading.Reading
import space.dragota.panelka.core.reading.ReadingRepository
import space.dragota.panelka.core.reading.Type
import space.dragota.panelka.core.uc.AcceptNewReading
import space.dragota.panelka.core.uc.CreateNewApartment

@RestController
@RequestMapping("/apartments")
class BillableEntityController(
        private val readingRepository: ReadingRepository,
        private val billableEntityRepository: BillableEntityRepository
        ) {


    @PostMapping("")
    fun createNewApartment() : BillableEntity {
        val createNewApartment = CreateNewApartment(billableEntityRepository)
        //ResponseEntity.status(HttpStatus.OK).body(
        return createNewApartment.execute()
    }

    @PostMapping("/{apartmentId}/water-readings")
    fun submitWaterReading(@PathVariable apartmentId: Long, @RequestBody waterReading: WaterReadingDto): Reading {
        // TODO: Retrieve the apartment with the given id from the database
        val acceptNewReading = AcceptNewReading(readingRepository, billableEntityRepository)

        val reading: Reading = acceptNewReading.execute(apartmentId, waterReading.type, waterReading.reading)
//        // Retrieve the latest reading for this apartment and type
//        val latestReading = waterReadingRepository.findTopByApartmentAndTypeOrderByReadingDesc(apartment, waterReading.type)
//
//        // If the new reading is lower than the latest reading, return an error
//        if (latestReading != null && waterReading.reading < latestReading.reading) {
//            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "New reading must be greater than or equal to the latest reading")
//        }
//
//        // Create a new WaterReading entity
//        val newReading = WaterReading(0, apartment, waterReading.type, waterReading.reading)
//
//        // Save the new reading to the database
//        return waterReadingRepository.save(newReading)
        return reading;
    }

    // Other endpoints...
}

// We use a DTO for the request body to separate our API model from our database model
data class WaterReadingDto(val type: Type, val reading: Long)
