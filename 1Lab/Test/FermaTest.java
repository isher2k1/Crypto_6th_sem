import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;


class FermaTest {
    private static final int certainty = 100;

    @Test
    public void test() {
        assertTrue(Ferma.isPrime(new BigInteger("45787669"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("407153"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("307169"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("756673"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("1248715577"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("643439"), 300));
        assertTrue(Ferma.isPrime(new BigInteger("17"), 300));
    }

}
