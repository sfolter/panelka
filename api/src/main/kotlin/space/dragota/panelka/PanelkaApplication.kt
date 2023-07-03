package space.dragota.panelka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class PanelkaApplication

fun main(args: Array<String>) {
	runApplication<PanelkaApplication>(*args)
}
