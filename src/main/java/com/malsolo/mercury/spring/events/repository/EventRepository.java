package com.malsolo.mercury.spring.events.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.malsolo.mercury.spring.events.domain.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, String> {
	
	public List<Event> findByCodeType(@Param("codeType") Integer codeType);

	public List<Event> findByDate(@Param("date") Date date);
	
}
