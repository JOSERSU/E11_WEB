package com.upiiz.SecurityInDataBase.repository;

//import com.upiiz.SecurityDataBase.entities.PermissionEntity;
import com.upiiz.SecurityInDataBase.entities.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, Long> {
}
