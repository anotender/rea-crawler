package pl.edu.agh.rea.crawler.configuration.provider

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import java.io.File
import java.io.FileNotFoundException
import javax.annotation.PostConstruct
import kotlin.system.exitProcess

@Component
class ConfigurationProvider(@Value("\${configurationFilePath}") private val configurationFilePath: String) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ConfigurationProvider::class.java)
    }

    lateinit var vendorConfigurationProperties: VendorConfigurationProperties

    @PostConstruct
    private fun readVendorConfigurationProperties() {
        try {
            LOGGER.info("Reading configuration from $configurationFilePath")
            vendorConfigurationProperties = jacksonObjectMapper().readValue(File(configurationFilePath))
            LOGGER.info("The config is: $vendorConfigurationProperties")
        } catch (e: FileNotFoundException) {
            LOGGER.error("The configuration file was not found. Please add --configurationFilePath argument or check if provided file exists.")
            LOGGER.error("The app will terminate.")
            exitProcess(-1)
        }
    }
}