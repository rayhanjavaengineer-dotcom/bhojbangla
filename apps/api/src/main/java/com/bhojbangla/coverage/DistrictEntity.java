package com.bhojbangla.coverage;
import jakarta.persistence.*;
@Entity @Table(name="districts") public class DistrictEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String division;
 @Column(nullable=false) private boolean enabled;
 protected DistrictEntity(){}
 public String getName(){return name;}
 public String getDivision(){return division;}
 public boolean isEnabled(){return enabled;}
}
