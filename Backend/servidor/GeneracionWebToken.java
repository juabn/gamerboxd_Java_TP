package servidor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.SecretKey;

public class GeneracionWebToken {

	public static void main(String[] args) {
		
		probando();


}
	


public static void probando(){
	byte[] content = "Hello World".getBytes(StandardCharsets.UTF_8);
	
	
	SecretKey key = Jwts.SIG.HS256.key().build();
	
	String jws = Jwts.builder().subject("Santi").signWith(key).compact();
	
	try {
	
	    Jwts.parser().verifyWith(key).build().parseSignedClaims(jws);
	
	    System.out.println("Confiaaa");
	
	} catch (JwtException e) {
	
		System.out.println("No Confies");
	}

}

}