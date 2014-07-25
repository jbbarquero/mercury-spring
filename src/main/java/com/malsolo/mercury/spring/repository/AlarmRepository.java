package com.malsolo.mercury.spring.repository;

import java.util.List;

import com.malsolo.mercury.spring.domain.Alarm;

public interface AlarmRepository {

	public abstract void save(Alarm alarm);

	public abstract Alarm findById(Long id);

	public abstract List<Alarm> findAll();

}