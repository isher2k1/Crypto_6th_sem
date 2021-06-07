import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RC4Test {

    @Test
    public void testCryptMes() {
        RC4 rc4 = new RC4();
        String mes = "Hey, dude!";
        String key = "blalalalalblsa";
        byte[] crypt = rc4.encryptMes(mes, key);
        String msg = rc4.decryptMes(crypt, key);
        assertEquals(mes, msg);
    }

    @Test(expected = InvalidKeyException.class)
    public void testSetKeyForSmallKey() {
        new RC4("");
    }

    @Test(expected = InvalidKeyException.class)
    public void testSetKeyForBigKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 512; i++) {
            sb.append('z');
        }
        RC4 rc4 = new RC4();
        rc4.setKey(sb.toString());
    }

    @Test
    public void testCryptWithoutEng() {
        String mes = "Доровки)";
        String key = "Ехал Грека по шосе";
        RC4 rc4 = new RC4(key);
        byte[] crypt = rc4.crypt(mes.getBytes());
        byte[] msg = rc4.crypt(crypt);
        assertEquals(mes, new String(msg));
    }



}