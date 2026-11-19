package com.bhojbangla.coverage;
import java.util.List;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/coverage") public class CoverageController{
public record District(String name,String division,boolean enabled){}
private static final List<District> DATA=List.of(new District("Dhaka","Dhaka",true),new District("Chattogram","Chattogram",false),new District("Sylhet","Sylhet",false));
@GetMapping("/districts") public List<District> districts(){return DATA;}
}
