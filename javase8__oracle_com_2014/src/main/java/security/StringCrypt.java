package security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class StringCrypt {

    public static void main(String[] args) throws Exception {
        final byte[] key = "1234567887654321".getBytes();
        final byte[] iv = "0123456776543210".getBytes();

        String AES_KEY_HEX = AESManager.getHex(key, key.length);

        System.out.println("KEY : " + AES_KEY_HEX);

        String original = "123";
        System.out.println("original: " + original);

        String encrypted = encrypt(original, key, iv);
        decrypt(encrypted, key, iv);

    }

    private static String encrypt(String str, byte[] key, byte[] iv) throws Exception {
        String adopted = adoptString(str);
        byte[] cipherText = AESManager.encrypt(key, iv, adopted.getBytes());
        String encrypted = new String(Base64.getUrlEncoder().encode(cipherText));
        System.out.println("encrypted: " + encrypted);
        return encrypted;
    }

    private static String decrypt(String str, byte[] key, byte[] iv) throws Exception {
        byte[] decryptedString = AESManager.decrypt(key, iv, Base64.getUrlDecoder().decode(str.getBytes()));
        String decrypted = new String(Base64.getUrlDecoder().decode(decryptedString));
        System.out.println("decrypted: " + decrypted);
        return decrypted;
    }


    public static String adoptString(String original) {
        return Base64.getUrlEncoder().encodeToString(original.getBytes());
    }

    public static byte[] generateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return "1234567887654321".getBytes();
    }

    public static class AESManager {

        public static String ALGORITHM = "AES";
        public static String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

        public static byte[] encrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
            return AESManager.encryptDecrypt(Cipher.ENCRYPT_MODE, key, IV, message);
        }

        public static byte[] decrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
            return AESManager.encryptDecrypt(Cipher.DECRYPT_MODE, key, IV, message);
        }

        private static byte[] encryptDecrypt(final int mode, final byte[] key, final byte[] IV, final byte[] message) throws Exception {
            final Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            final SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            final IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(mode, keySpec, ivSpec);
            return cipher.doFinal(message);
        }

        public static String getHex(byte[] data, int length) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                String hexStr = Integer.toHexString(((int) data[i]) & 0xFF);
                if (hexStr.length() < 2) {
                    sb.append("0").append(hexStr.toUpperCase());
                } else {
                    sb.append(hexStr.toUpperCase());
                }
            }
            return sb.toString();
        }

    }

}
