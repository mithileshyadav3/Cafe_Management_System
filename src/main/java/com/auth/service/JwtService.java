package com.auth.service;



import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	private String secretkey="";
	
//  this is for generate key what amount of bytes for e.g 32 bytes
	public JwtService() {
		super();
		// TODO Auto-generated constructor stub
		try {
			KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sKey=keyGenerator.generateKey();
			secretkey=Base64.getEncoder().encodeToString(sKey.getEncoded());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	 private SecretKey getKey() {
	        byte[] bytes = Decoders.BASE64.decode(secretkey);
	        return Keys.hmacShaKeyFor(bytes);
	    }
	public String GenerateToken(String username,String role) {
		Map<String, Object>claims=new HashMap<>();
		claims.put("role", role);
		return Jwts.builder()
	                .claims()
	                .add(claims)
	                .subject(username)
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis()+60*60*1000))
	                .and()
	                .signWith(getKey())
	                .compact();
	}
	

	public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

	public String  extractRoles(String token) {
		return extractClaim(token, claims->claims.get("role",String.class));
	}
	// ------------------- Extract All Claims -------------------
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())          // set signing key
                .build()                       // build parser
                .parseSignedClaims(token)      // Jws<Claims>
                .getPayload();                 // Claims (payload)
    }

    // ------------------- Generic Claim Extractor -------------------
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // ------------------- Validate Token -------------------
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ------------------- Token Expiry Check -------------------
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
