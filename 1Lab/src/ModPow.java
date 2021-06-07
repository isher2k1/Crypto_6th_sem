import java.math.BigInteger;

public class ModPow {

    public ModPow(){}

    public static BigInteger modPow(BigInteger x, BigInteger exp, BigInteger n)
    {

        if (n.compareTo(BigInteger.ZERO) <= 0) throw new ArithmeticException("non-positive modulo");
        if (exp.compareTo(BigInteger.ZERO) < 0) return modPow(x.modInverse(n), exp.negate(), n);
        if (exp.equals(BigInteger.ONE)) return x.mod(n);

        BigInteger s = BigInteger.ONE;
        BigInteger t = x;
        BigInteger u = exp;

        while (!u.equals(BigInteger.ZERO))
        {
            if (u.and(BigInteger.ONE).equals(BigInteger.ONE)) s = s.multiply(t).mod(n);

            u = u.shiftRight(1);
            t = t.multiply(t).mod(n);
        }
        return s;
    }
    public static void main(String[] args) {
        BigInteger res = modPow(new BigInteger("18"), new BigInteger("75"), new BigInteger("11"));
        System.out.println(res);
    }

}