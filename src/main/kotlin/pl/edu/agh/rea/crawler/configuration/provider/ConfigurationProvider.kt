package pl.edu.agh.rea.crawler.configuration.provider

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import java.net.URL
import kotlin.system.exitProcess

@Component
class ConfigurationProvider(@Value("\${spring.profiles.active}") private val profile: String) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ConfigurationProvider::class.java)
    }

    val vendorConfigurationProperties: VendorConfigurationProperties = readVendorConfigurationProperties()

    private fun readVendorConfigurationProperties(): VendorConfigurationProperties {
        val configurationFileUrl = getConfigurationFileUrlForVendor()

        if (configurationFileUrl == null) {
            LOGGER.error("The configuration file for specified profile $profile was not found")
            LOGGER.error("Did you add --spring.profiles.active argument?")
            LOGGER.error("The app will terminate")
            exitProcess(-1)
        }

        LOGGER.info("Reading configuration for profile $profile from $configurationFileUrl")
        val vendorConfigurationProperties: VendorConfigurationProperties = jacksonObjectMapper().readValue(configurationFileUrl)
        LOGGER.info("The config is: $vendorConfigurationProperties")
        return vendorConfigurationProperties
    }

    private fun getConfigurationFileUrlForVendor(): URL? {
        return this.javaClass::class.java.getResource("/configuration/$profile.json")
    }
}