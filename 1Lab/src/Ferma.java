import java.math.BigInteger;
import java.util.Random;

public class Ferma {

    private final static Random rand = new Random();

    public Ferma(){}

    public static boolean isPrime(BigInteger n, int iteration) {
        if (iteration <= 0) {
            return false;
        }

        if (n.compareTo(BigInteger.ONE) == 0) {
            return false;
        }

        if (n.compareTo(BigInteger.TWO) == 0) {
            return true;
        }

        for (int i = 0; i < iteration; i++) {
            BigInteger a = getRandFermaBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }

    private static BigInteger getRandFermaBase(BigInteger n) {

        while (true) {
            final BigInteger a = new BigInteger(n.bitLength(), rand);
            // must have 1 <= a < n
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0) {
                return a;
            }
        }
    }

}