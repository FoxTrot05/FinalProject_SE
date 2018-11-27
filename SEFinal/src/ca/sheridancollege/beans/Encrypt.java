package ca.sheridancollege.beans;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigInteger;

public class Encrypt {

	private static final Random RAND = new SecureRandom();
	private String rawPassword;
	private String encPassword;
	private byte[] byteHash;
	

	
	public Encrypt(String rawPassword) {
		this.rawPassword = rawPassword;
	}
	
	public void process() throws NoSuchAlgorithmException{
		byte[] textBytes = rawPassword.getBytes(StandardCharsets.UTF_16); //Convert input password to bytes
        byte[] salt = getSalt(); //Generate salt
        byte[] concatBytes = null; //Prepare to concatenate the two byte arrays
		
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(); //Create a byte output stream to concatenate
        try {
            outStream.write(textBytes); //Write input password to stream
            outStream.write(salt); //Write salt to stream
            concatBytes = outStream.toByteArray(); //Assign the output stream as an array of bytes to the concatenating array

            //Close and flush output stream
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        MessageDigest m = MessageDigest.getInstance("SHA-256"); //Set algorithm for hashing to SHA-256

        m.update(concatBytes); //Prepare to digest using the concatenated byte array
        byteHash = m.digest(); //Complete the hash with final operations and assign to an array
        
        encPassword = String.format("%064x", new BigInteger(1, byteHash)); //Convert the byte array to a string represented by ASCII hex
	}
	
    public String getEncPassword() {
		return encPassword;
	}

	public byte[] getByteHash() {
		return byteHash;
	}

	private static byte[] getSalt() {
        byte[] salt = new byte[16];
        RAND.nextBytes(salt);
        return salt;
    }
}
