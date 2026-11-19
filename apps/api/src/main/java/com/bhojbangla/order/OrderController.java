package com.bhojbangla.order;
import java.util.List;import jakarta.validation.Valid;import jakarta.validation.constraints.Min;import jakarta.validation.constraints.NotBlank;import jakarta.validation.constraints.NotEmpty;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/orders") public class OrderController{
public record Line(@Min(1)long menuItemId,@Min(1)int quantity){}public record PlaceOrder(@Min(1)long restaurantId,@NotEmpty List<@Valid Line> items,@NotBlank String district,@NotBlank String city,@NotBlank String address,@NotBlank String phone,String paymentMethod){}
@PostMapping public void place(@Valid @RequestBody PlaceOrder o,Authentication auth){if(!"COD".equals(o.paymentMethod()))throw new IllegalArgumentException("Only COD is currently supported");throw new IllegalStateException("Ordering is unavailable until a verified restaurant catalog and delivery coverage are configured.");}
}