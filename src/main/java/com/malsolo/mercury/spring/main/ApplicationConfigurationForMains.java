package com.malsolo.mercury.spring.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import({MongoDbRepositoryConfiguration.class}) It's not enough to get the repositories injected
@ComponentScan
//@Main //A @Configuration is a @Component so it's better to avoid it too
public class ApplicationConfigurationForMains {

}
