package com.malsolo.mercury.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.malsolo.mercury.spring.domain.Type;

public interface TypeRepositoryForRest extends TypeRepository, CrudRepository<Type, String> {
	
	@Override
	public Type findByCode(@Param("code") Integer code);
	
	@Override
	public Type findById(@Param("id") String id);

}
