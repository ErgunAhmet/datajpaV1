package com.datajpa.demo.repository;

import com.datajpa.demo.model.ZipCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long> {
}
