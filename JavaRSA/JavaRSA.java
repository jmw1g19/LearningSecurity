import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class JavaRSA{
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey; // Our private key, e.
    private BigInteger publicKey; // Our private key, d.
    private BigInteger modulus; // The value of n in (mod n), see encrypt()/decrypt().

    public void generateKeyPair(int N){
        // Generate two prime numbers, p and q.
        BigInteger p = BigInteger.probablePrime(N, random);
        BigInteger q = BigInteger.probablePrime(N, random);

        // Calculate n = p * q.
        BigInteger n = p.multiply(q);
        modulus = n;

        // Calculate φ(n). We first need a BigInteger representation of 1.
        BigInteger one = new BigInteger("1");
        // Since p and q are prime, φ(n) = φ(p * q) = φ(p) * φ(q) = (p - 1)(q - 1).
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one)); 

        // Choose a value of e. In practice, e is 2^16 + 1.
        BigInteger e = new BigInteger("65537"); 

        // The public key is (n, e).
        publicKey = e;

        // The private key is (n, d), where d = e^-1 mod φ(n).
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger plaintext){
        // To encrypt a message into ciphertext c, perform c = m^e mod n.
        return plaintext.modPow(publicKey, modulus);
    }
    
    public BigInteger decrypt(BigInteger ciphertext){
        // To decrypt a message into plaintext m, perform m = c^d mod n.
        return ciphertext.modPow(privateKey, modulus);
    }

    public static void main(String[] args){
        // To test if everything works, we will instantiate this class and perform a test.
        JavaRSA instance = new JavaRSA();
        instance.generateKeyPair(128);
        
        // To convert a string, we need to first accept an input.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message: ");
        String plaintext = input.nextLine();

        // Then, we must convert the string into a byte array, and use that to instantiate a BigInteger.
        BigInteger message = new BigInteger(plaintext.getBytes());
        String ciphertext = new String(instance.encrypt(message).toByteArray());
        BigInteger decryptedMessage = instance.decrypt(instance.encrypt(message));

        // For debugging purposes.
        System.out.println("Public key: " + instance.publicKey);
        System.out.println("Private key: " + instance.privateKey);
        System.out.println("N: " + instance.modulus);
        System.out.println("===");
        System.out.println("Encrypted message: " + ciphertext);
        
        // We perform an equality test - the decrypted message should be the same as the original!
        System.out.println("Do they match? " + message.equals(decryptedMessage));
    }
}