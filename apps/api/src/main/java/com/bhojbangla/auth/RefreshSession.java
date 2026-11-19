package com.bhojbangla.auth;
import java.time.Instant;import jakarta.persistence.*;
@Entity @Table(name="refresh_sessions") public class RefreshSession{
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(name="token_hash",nullable=false,unique=true,length=64,columnDefinition="char(64)") private String tokenHash;
@ManyToOne(optional=false) @JoinColumn(name="user_id") private UserAccount user;
@Column(name="expires_at",nullable=false) private Instant expiresAt;
@Column(name="revoked_at") private Instant revokedAt;
protected RefreshSession(){}
public RefreshSession(String hash,UserAccount user,Instant expiry){tokenHash=hash;this.user=user;expiresAt=expiry;}
public String getTokenHash(){return tokenHash;}public UserAccount getUser(){return user;}public Instant getExpiresAt(){return expiresAt;}public Instant getRevokedAt(){return revokedAt;}public void revoke(){revokedAt=Instant.now();}
}

