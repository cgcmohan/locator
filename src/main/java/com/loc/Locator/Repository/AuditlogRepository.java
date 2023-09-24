package com.loc.Locator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loc.Locator.Entity.AuditLog;
@Repository
public interface AuditlogRepository extends JpaRepository<AuditLog, Long>{

	 @Query(value = "SELECT * FROM audit_log ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
	    AuditLog findTopByOrderByTimestampDesc();
}
