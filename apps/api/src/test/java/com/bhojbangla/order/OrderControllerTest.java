package com.bhojbangla.order;
import static org.junit.jupiter.api.Assertions.*;import java.util.List;import org.junit.jupiter.api.Test;
class OrderControllerTest {
 @Test void codOrderIsNotAcceptedWithoutVerifiedCatalogAndCoverage(){
  var controller=new OrderController();
  var request=new OrderController.PlaceOrder(1,List.of(new OrderController.Line(1,1)),"Dhaka","Dhaka","address","01700000000","COD");
  assertThrows(OrderingUnavailableException.class,()->controller.place(request));
 }
 @Test void rejectsNonCodPayment(){
  var controller=new OrderController();
  var request=new OrderController.PlaceOrder(1,List.of(new OrderController.Line(1,1)),"Dhaka","Dhaka","address","01700000000","CARD");
  assertThrows(IllegalArgumentException.class,()->controller.place(request));
 }
}
