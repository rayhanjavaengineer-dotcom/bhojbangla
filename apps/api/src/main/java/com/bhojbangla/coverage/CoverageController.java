package com.bhojbangla.coverage;
import java.util.List;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/coverage") public class CoverageController{
 public record District(Long id,String name,String division){}
 private final DistrictRepository districts;
 public CoverageController(DistrictRepository districts){this.districts=districts;}
 @GetMapping("/districts") public List<District> districts(){return districts.findServiceableDistricts().stream().map(d->new District(d.getId(),d.getName(),d.getDivision())).toList();}
}

