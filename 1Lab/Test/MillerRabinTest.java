import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class MillerRabinTest {
    @Test
    public void isPrime() {
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("45787669"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("407153"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("307169"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("756673"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("1248715577"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("643439"), 300));
        Assertions.assertTrue(Ferma.isPrime(new BigInteger("17"), 300));
    }
}