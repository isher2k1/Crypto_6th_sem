import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RC4 {
    private static final int BOX_LENGTH = 256;
    private static final int KEY_MIN_LENGTH = 5;
    //key array
    private byte[] key = new byte[BOX_LENGTH - 1];
    //sbox
    private int[] box = new int[BOX_LENGTH];

    public RC4() {
        reset();
    }

    public RC4(String key) throws InvalidKeyException {
        this();
        setKey(key);
    }

    private void reset() {
        Arrays.fill(key, (byte) 0);
        Arrays.fill(box, 0);
    }

    //Encrypt given message String with given Charset and key
    public byte[] encryptMes(String mes, Charset charset, String key)
            throws InvalidKeyException {
        reset();
        setKey(key);
        byte[] crypt = crypt(mes.getBytes());
        reset();
        return crypt; //return encrypted message
    }

    //Encrypt given message String with given Key and pre-defined UTF-8 charset
    public byte[] encryptMes(String message, String key)
            throws InvalidKeyException {
        return encryptMes(message, StandardCharsets.UTF_8, key);//return encrypted message
    }

    //Decrypt given byte[] message array with given charset and key
    public String decryptMes(byte[] mes, Charset charset, String key)
            throws InvalidKeyException {
        reset();
        setKey(key);
        byte[] msg = crypt(mes);
        reset();
        return new String(msg);//string in given charset
    }

    //Decrypt given byte[] message array with given key and pre-defined UTF-8 charset
    public String decryptMes(byte[] message, String key)
            throws InvalidKeyException {
        return decryptMes(message, StandardCharsets.UTF_8, key);//string in given charset
    }

    //Crypt given byte array. Be aware, that you must init key, before using crypt
    public byte[] crypt(final byte[] msg) {//input param: message array to crypt
        box = initBox(key);
        byte[] code = new byte[msg.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < msg.length; n++) {
            i = (i + 1) % BOX_LENGTH;
            j = (j + box[i]) % BOX_LENGTH;
            swap(i, j, box);
            int rand = box[(box[i] + box[j]) % BOX_LENGTH];
            code[n] = (byte) (rand ^ msg[n]);
        }
        return code;//crypted byte array
    }

    //Initialize SBOX with given key. Key-scheduling algorithm
    private int[] initBox(byte[] key) {
        int[] box = new int[BOX_LENGTH];
        int j = 0;

        for (int i = 0; i < BOX_LENGTH; i++) {
            box[i] = i;
        }

        for (int i = 0; i < BOX_LENGTH; i++) {
            j = (j + box[i] + (key[i % key.length]) & 0xFF) % BOX_LENGTH;
            swap(i, j, box);
        }
        return box;//box int array
    }

    private void swap(int i, int j, int[] sbox) {
        int temp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = temp;
    }

    //Setup key
    public void setKey(String key) throws InvalidKeyException {//Exception if 255 < key length < 5
        if (!(key.length() >= KEY_MIN_LENGTH && key.length() < BOX_LENGTH)) {
            throw new InvalidKeyException("Key length is between "
                    + KEY_MIN_LENGTH + " & " + (BOX_LENGTH - 1));
        }

        this.key = key.getBytes();
    }

}

class InvalidKeyException extends RuntimeException {//to recognise invalid keys

    private static final long serialVersionUID = 1L;

    public InvalidKeyException(String mes) {
        super(mes);
    }

}
