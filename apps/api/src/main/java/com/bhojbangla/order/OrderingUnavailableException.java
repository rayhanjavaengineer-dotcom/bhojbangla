package com.bhojbangla.order;
public class OrderingUnavailableException extends RuntimeException {
 public OrderingUnavailableException(){super("Ordering is unavailable until verified restaurant prices and delivery coverage are configured.");}
}
