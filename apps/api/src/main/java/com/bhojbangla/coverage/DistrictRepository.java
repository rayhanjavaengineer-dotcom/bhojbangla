package com.bhojbangla.coverage;
import java.util.List;import org.springframework.data.jpa.repository.JpaRepository;
public interface DistrictRepository extends JpaRepository<DistrictEntity,Long>{List<DistrictEntity> findAllByOrderByDivisionAscNameAsc();}
