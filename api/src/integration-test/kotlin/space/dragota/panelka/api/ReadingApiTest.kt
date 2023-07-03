package space.dragota.panelka.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import space.dragota.panelka.api.controller.WaterReadingDto
import space.dragota.panelka.core.BillableEntity
import space.dragota.panelka.core.reading.Type

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReadingApiTest(
        @Autowired val restTemplate: TestRestTemplate,
        @LocalServerPort val port: Int,
        @Autowired val objectMapper: ObjectMapper
) {

    companion object {
        @Container
        val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:latest")
                .apply {
                    withDatabaseName("panelka")
                    withUsername("tad")
                    withPassword("tad")
                }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgreSQLContainer::getUsername)
            registry.add("spring.datasource.password", postgreSQLContainer::getPassword)
        }
    }

    @Test
    fun `can submit a new reading`() {
        val result: ResponseEntity<BillableEntity> = restTemplate.postForEntity("/apartments", HttpEntity("null"), BillableEntity::class.java)
        val body = result.body

        val newReading = WaterReadingDto(Type.COLD_WATER, 150L)
        val request: HttpEntity<WaterReadingDto> = HttpEntity(newReading)

        val response: ResponseEntity<Void> = restTemplate.postForEntity("/apartments/1/water-readings", request, Void::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}