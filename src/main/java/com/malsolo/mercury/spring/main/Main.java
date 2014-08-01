package com.malsolo.mercury.spring.main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation intended to mark a class as a Main class.
 * Unnecesary if MongoDbRepositoryConfiguration is moved to its own package.
 * But this class was born as a general configuration.
 * @author jbeneito
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Main {

}
