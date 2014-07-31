package com.malsolo.mercury.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * For configuring Spring Data REST web when basic configuration is not enough.
 * Not necessary for the time being.
 * @author jbeneito
 *
 */
//@SuppressWarnings({"rawtypes"})
@Configuration
public class RestExporterWebConfiguration extends RepositoryRestMvcConfiguration {

}
