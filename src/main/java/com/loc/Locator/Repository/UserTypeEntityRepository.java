package com.loc.Locator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loc.Locator.Entity.UserTypeEntity;
@Repository
public interface UserTypeEntityRepository extends JpaRepository<UserTypeEntity, Long>{

	@Query(value = "select * from user_type u where u.diet_type = :dietType" , nativeQuery = true)
	public List<UserTypeEntity> findByDietType(String dietType);

}
