package com.malsolo.mercury.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.malsolo.mercury.spring.domain.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
