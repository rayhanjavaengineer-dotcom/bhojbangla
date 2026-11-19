package com.bhojbangla.auth;
import jakarta.servlet.http.HttpServletResponse;import jakarta.validation.Valid;import jakarta.validation.constraints.Email;import jakarta.validation.constraints.NotBlank;import jakarta.validation.constraints.Size;import org.springframework.beans.factory.annotation.Value;import org.springframework.http.HttpHeaders;import org.springframework.http.ResponseCookie;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController{
private final AuthService auth;private final boolean secure;
public AuthController(AuthService a,@Value("${security.cookie.secure}")boolean secure){auth=a;this.secure=secure;}
public record Credentials(@Email @NotBlank String email,@NotBlank @Size(min=12,max=128)String password){}
public record Access(String accessToken,String role){}
@PostMapping("/register") public Access register(@Valid @RequestBody Credentials c,HttpServletResponse r){var u=auth.register(c.email(),c.password());var t=auth.login(u.getEmail(),c.password());setCookie(r,t.refreshToken());return new Access(t.accessToken(),t.role());}
@PostMapping("/login") public Access login(@Valid @RequestBody Credentials c,HttpServletResponse r){var t=auth.login(c.email(),c.password());setCookie(r,t.refreshToken());return new Access(t.accessToken(),t.role());}
@PostMapping("/refresh") public Access refresh(@CookieValue(name="bb_refresh",required=false)String token,HttpServletResponse r){if(token==null)throw new IllegalArgumentException("Invalid refresh session");var t=auth.refresh(token);setCookie(r,t.refreshToken());return new Access(t.accessToken(),t.role());}
@PostMapping("/logout") public void logout(@CookieValue(name="bb_refresh",required=false)String token,HttpServletResponse r){if(token!=null)auth.logout(token);r.addHeader(HttpHeaders.SET_COOKIE,cookie("").maxAge(0).build().toString());}
private void setCookie(HttpServletResponse r,String token){r.addHeader(HttpHeaders.SET_COOKIE,cookie(token).build().toString());}
private ResponseCookie.ResponseCookieBuilder cookie(String v){return ResponseCookie.from("bb_refresh",v).httpOnly(true).secure(secure).sameSite("Lax").path("/api/auth").maxAge(30L*24*60*60);}
}
