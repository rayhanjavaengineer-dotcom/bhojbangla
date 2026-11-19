package com.bhojbangla.security;
import static org.junit.jupiter.api.Assertions.*;import org.junit.jupiter.api.Test;
class JwtServiceTest{
@Test void issuesSignedTokensWithSubjectAndRole(){var service=new JwtService("test-secret-with-more-than-thirty-two-bytes-long",15);var claims=service.parse(service.issue("customer@example.test","CUSTOMER"));assertEquals("customer@example.test",claims.getSubject());assertEquals("CUSTOMER",claims.get("role"));}
@Test void rejectsWeakSigningSecret(){assertThrows(IllegalArgumentException.class,()->new JwtService("too-short",15));}
}