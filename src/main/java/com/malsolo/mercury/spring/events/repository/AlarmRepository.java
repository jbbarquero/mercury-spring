package com.malsolo.mercury.spring.events.repository;

import java.util.List;

import com.malsolo.mercury.spring.events.domain.Alarm;

public interface AlarmRepository {

	public abstract void save(Alarm alarm);

	public abstract Alarm findById(String id);

	public abstract List<Alarm> findAll();

}