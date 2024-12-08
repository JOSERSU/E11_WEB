package com.upiiz.SecurityInDataBase.repository;

import com.upiiz.SecurityInDataBase.entities.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<RolEntity, Long> {

}
