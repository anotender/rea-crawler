package pl.edu.agh.rea.crawler.configuration.provider

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import java.net.URL
import kotlin.system.exitProcess

@Component
class ConfigurationReader(@Value("\${VENDOR}") private val vendor: String,
                          private val resourceLoader: ResourceLoader) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ConfigurationReader::class.java)
    }

    fun readVendorConfiguration(): VendorConfiguration {
        val configurationFileUrl = getConfigurationFileUrlForVendor()

        if (configurationFileUrl == null) {
            LOGGER.error("The configuration file for specified vendor $vendor was not found")
            LOGGER.error("Is VENDOR environment variable set?")
            LOGGER.error("The app will terminate")
            exitProcess(-1)
        }

        LOGGER.info("Reading configuration for vendor $vendor from $configurationFileUrl")
        val vendorConfiguration: VendorConfiguration = jacksonObjectMapper().readValue(configurationFileUrl)
        LOGGER.info("The config is: $vendorConfiguration")
        return vendorConfiguration
    }

    private fun getConfigurationFileUrlForVendor(): URL? {
        return resourceLoader.getResource("classpath:/configuration/$vendor.json").url
    }
}