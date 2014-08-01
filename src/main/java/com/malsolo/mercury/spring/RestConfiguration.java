package com.malsolo.mercury.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.malsolo.mercury.spring.domain.Type;

@Configuration
public class RestConfiguration {
	
	public ResourceProcessor<Resource<Type>> typeProcesor() {
		return new ResourceProcessor<Resource<Type>>() {

			@Override
			public Resource<Type> process(Resource<Type> type) {
				type.add(new Link("http://localhost:8080/my-other-resource", "other.resource"));
				return type;
			}
			
		};
		
	}

}
