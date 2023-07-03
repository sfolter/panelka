package space.dragota.panelka.core.reading

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReadingRepository : CrudRepository<Reading, Long> {
    fun findByApartmentIdAndType(apartmentId: Long, type: Type): Reading?

}