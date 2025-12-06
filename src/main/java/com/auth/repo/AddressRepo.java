package com.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.model.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address,Integer > {

}
