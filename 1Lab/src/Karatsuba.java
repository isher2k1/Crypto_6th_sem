import java.math.BigInteger;

public class Karatsuba {

    public Karatsuba(){}

    public static BigInteger multy(BigInteger x, BigInteger y) {

        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 200) return x.multiply(y);

        N = (N / 2) + (N % 2);

        BigInteger a = x.shiftRight(N);
        BigInteger z = x.subtract(a.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        BigInteger zc = multy(z, c);
        BigInteger ad = multy(a, d);
        BigInteger abcd = multy(a.add(a), c.add(d));

        return zc.add(abcd.subtract(zc).subtract(ad).shiftLeft(N)).add(ad.shiftLeft(2*N));
    }

}