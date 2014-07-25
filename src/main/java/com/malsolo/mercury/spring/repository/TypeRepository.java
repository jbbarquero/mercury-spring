package com.malsolo.mercury.spring.repository;

import java.util.List;

import com.malsolo.mercury.spring.domain.Type;

public interface TypeRepository {

	public abstract void save(Type type);

	public abstract Type findById(Long id);

	public abstract List<Type> findAll();

}