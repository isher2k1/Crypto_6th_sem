import java.security.SecureRandom;
import java.util.*;
import java.math.BigInteger;

public class Elgamal {

    static BigInteger p, g, y, a, b, k, EC, M;
    static final BigInteger secretKey = new BigInteger("25565");;
    static final Random sc = new SecureRandom();

    public static BigInteger run (BigInteger number){
        publicKeyCalculationFunc();
        encryptionFunc(number);
        return decryptionFunc();
    }

    private static void publicKeyCalculationFunc(){
        //generate random simple number p
        //choose integer number g, g is antiderivative root of p
        //choose number x , 1 < x < p-1
        p = BigInteger.probablePrime(64, sc);
        g = new BigInteger("3");
        //y = g^x mod p - public key
        y = g.modPow(secretKey, p);
        //x - private key
    }

    private static void encryptionFunc(BigInteger XKEY){
        k = new BigInteger(64, sc); //choose session key k, 1 < k < p-1
        EC = XKEY.multiply(y.modPow(k, p)).mod(p);//Pair (a,b) is a encryption text
        a = g.modPow(k, p);//a = g^k mod p
        b = a.modPow(secretKey, p);//b = (y^k * M) mod p
    }

    private static BigInteger decryptionFunc(){
        BigInteger d = b.modInverse(p);
        M = d.multiply(EC).mod(p);//M = (b*a^(p-1-x)) mod p
        return M;
    }


}