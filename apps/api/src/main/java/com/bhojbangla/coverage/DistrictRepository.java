package com.bhojbangla.coverage;
import java.util.List;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;
public interface DistrictRepository extends JpaRepository<DistrictEntity,Long>{
 @Query(value="SELECT DISTINCT d.* FROM districts d JOIN delivery_coverage c ON c.district_id=d.id WHERE d.enabled=true AND c.enabled=true ORDER BY d.division,d.name",nativeQuery=true)
 List<DistrictEntity> findServiceableDistricts();
}

