package desktop;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.swing.JOptionPane;

public class HashingExample {

	public static void main(String arg[]) throws Exception {
	    sha512("banana");
	  }

	private static String sha512(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String encrypted = password;
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		digest.reset();
		digest.update(password.getBytes());
		byte[] encryptedBytes = digest.digest();
		encrypted = new BigInteger(1, encryptedBytes).toString(16);
		JOptionPane.showMessageDialog(null, encrypted);
		return encrypted;
	}
}
