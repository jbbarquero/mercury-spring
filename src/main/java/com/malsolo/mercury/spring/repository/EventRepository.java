package com.malsolo.mercury.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.malsolo.mercury.spring.domain.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, String> {
	
	public List<Event> findByCodeType(Integer codeType);

	public List<Event> findByDate(Date date);

}
