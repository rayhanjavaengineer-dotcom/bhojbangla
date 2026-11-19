package com.bhojbangla.auth;
import java.nio.charset.StandardCharsets;import java.security.MessageDigest;import java.security.SecureRandom;import java.time.Instant;import java.time.temporal.ChronoUnit;import java.util.Base64;import org.springframework.beans.factory.annotation.Value;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
@Service public class AuthService{
private final UserRepository users;private final RefreshSessionRepository sessions;private final PasswordEncoder encoder;private final JwtService jwt;private final long days;private final SecureRandom random=new SecureRandom();
public AuthService(UserRepository u,RefreshSessionRepository s,PasswordEncoder e,JwtService j,@Value("${security.jwt.refresh-ttl-days}")long days){users=u;sessions=s;encoder=e;jwt=j;this.days=days;}
@Transactional public UserAccount register(String email,String password){String normalized=email.trim().toLowerCase();if(users.findByEmailIgnoreCase(normalized).isPresent())throw new IllegalArgumentException("Unable to create account");return users.save(new UserAccount(normalized,encoder.encode(password),"CUSTOMER"));}
@Transactional public Tokens login(String email,String password){var user=users.findByEmailIgnoreCase(email.trim().toLowerCase()).filter(u->encoder.matches(password,u.getPasswordHash())).orElseThrow(()->new IllegalArgumentException("Invalid credentials"));return issue(user);}
@Transactional public Tokens refresh(String raw){var s=sessions.findByTokenHash(hash(raw)).filter(x->x.getRevokedAt()==null&&x.getExpiresAt().isAfter(Instant.now())).orElseThrow(()->new IllegalArgumentException("Invalid refresh session"));s.revoke();return issue(s.getUser());}
@Transactional public void logout(String raw){sessions.findByTokenHash(hash(raw)).ifPresent(RefreshSession::revoke);}
private Tokens issue(UserAccount u){byte[] b=new byte[48];random.nextBytes(b);String raw=Base64.getUrlEncoder().withoutPadding().encodeToString(b);sessions.save(new RefreshSession(hash(raw),u,Instant.now().plus(days,ChronoUnit.DAYS)));return new Tokens(jwt.issue(u.getEmail(),u.getRole()),raw,u.getRole());}
private String hash(String raw){try{return java.util.HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(raw.getBytes(StandardCharsets.UTF_8)));}catch(Exception e){throw new IllegalStateException(e);}}
public record Tokens(String accessToken,String refreshToken,String role){}
}
