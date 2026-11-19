package com.bhojbangla.auth;
public class InvalidCredentialsException extends RuntimeException { public InvalidCredentialsException(){this("Invalid credentials");} public InvalidCredentialsException(String message){super(message);} }

