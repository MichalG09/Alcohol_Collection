package pl.mgrzech.alcohols_collection.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration lets a get string from file value.properties.
 */
@Configuration
@PropertySource(value = "classpath:value.properties", encoding="UTF-8")
public class MessageConfig {

}
