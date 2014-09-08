package com.malsolo.mercury.spring.events.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.malsolo.mercury.spring.events.domain.Type;

@RestResource(rel = "tipos")
public interface TypeRepositoryForRest extends TypeRepository, CrudRepository<Type, String> {
	
	@Override
	@RestResource(path = "code")
	public Type findByCode(@Param("code") Integer code);
	
	@Override
	@RestResource(exported = false)
	public Type findById(@Param("id") String id);

	@RestResource(path = "active")
	public Type findByActiveIsTrue();
}
