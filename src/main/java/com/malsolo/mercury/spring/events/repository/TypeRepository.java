package com.malsolo.mercury.spring.events.repository;

import java.util.List;

import com.malsolo.mercury.spring.events.domain.Type;

public interface TypeRepository {

	public abstract Type save(Type type);

	public abstract Type findByCode(Integer code);

	public abstract Type findById(String id);

	public abstract List<Type> findAll();

}