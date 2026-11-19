package com.bhojbangla.security;
import java.nio.charset.StandardCharsets;import java.time.Instant;import java.util.Date;import javax.crypto.SecretKey;import io.jsonwebtoken.Jwts;import io.jsonwebtoken.security.Keys;import org.springframework.beans.factory.annotation.Value;import org.springframework.stereotype.Service;
@Service public class JwtService{
private final SecretKey key;private final long ttl;
public JwtService(@Value("${security.jwt.secret}")String secret,@Value("${security.jwt.access-ttl-minutes}")long ttl){if(secret.getBytes(StandardCharsets.UTF_8).length<32)throw new IllegalArgumentException("JWT_SECRET must be at least 32 bytes");this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.ttl=ttl;}
public String issue(String subject,String role){var now=Instant.now();return Jwts.builder().subject(subject).claim("role",role).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(ttl*60))).signWith(key).compact();}
public io.jsonwebtoken.Claims parse(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
}
