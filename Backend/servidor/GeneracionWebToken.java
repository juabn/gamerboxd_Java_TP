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




public static String enviotoken() {
	
	SecretKey key = Jwts.SIG.HS256.key().build();
	
	byte[] content = "Hello World".getBytes(StandardCharsets.UTF_8);
	
	String jws = Jwts.builder()
	
	.header()
		.keyId("IdEJEMPLO")
		.and()
	
		.subject("Santi")
		
		// (3) JSON Claims, or
	    //.content(aByteArray, "text/plain")        //     any byte[] content, with media type

	    .signWith(key)                       // (4) if signing, or
	    //.encryptWith(key, keyAlg, encryptionAlg)  //     if encrypting

	    .compact();  
	
	return(jws);
	
	
	
}

}