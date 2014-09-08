/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.malsolo.mercury.spring.delivery.domain.repository;

import com.malsolo.mercury.spring.delivery.domain.Addressee;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jbeneito
 */
public interface AddresseeRepository extends CrudRepository<Addressee, Long> {
    
}
