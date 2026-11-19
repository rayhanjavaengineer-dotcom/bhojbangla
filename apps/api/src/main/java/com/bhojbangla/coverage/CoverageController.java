package com.bhojbangla.coverage;
import java.util.List;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/coverage") public class CoverageController{
 public record District(String name,String division,boolean enabled){}
 private final DistrictRepository districts;
 public CoverageController(DistrictRepository districts){this.districts=districts;}
 @GetMapping("/districts") public List<District> districts(){return districts.findAllByOrderByDivisionAscNameAsc().stream().map(d->new District(d.getName(),d.getDivision(),d.isEnabled())).toList();}
}
