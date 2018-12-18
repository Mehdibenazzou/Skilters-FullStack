package com.test.Skilters_React.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.test.Skilters_React.model.User;
import static com.test.Skilters_React.security.SecurityConstants.EXPIRATION_TIME;
import static com.test.Skilters_React.security.SecurityConstants.SECRET;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		
		Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
		
		String userId = Long.toString(user.getId());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(user.getId())));
		claims.put("email", user.getEmail());
		claims.put("nom", user.getNom());
		claims.put("prenom", user.getPrenom());
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch(SignatureException ex) {
			System.out.println("Signature JWT invalide");
		} catch(MalformedJwtException ex) {
			System.out.println("Token JWT invalide");
		} catch(ExpiredJwtException ex) {
			System.out.println("Token JWT expiré");
		} catch(UnsupportedJwtException ex) {
			System.out.println("Token JWT non supporté");
		} catch(IllegalArgumentException ex) {
			System.out.println("JWT claims string vide");
		}
		return false;
	}
	
	//Get user Id from token

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
