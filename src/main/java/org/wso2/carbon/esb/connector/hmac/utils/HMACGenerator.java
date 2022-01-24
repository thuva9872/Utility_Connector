package org.wso2.carbon.esb.connector.hmac.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HMACGenerator {
    private static Map<String, Mac> macInstancesMap = new ConcurrentHashMap<>();

    public static String generateSignature(String payload, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {

        try {
            Mac mac = getMacInstance(algorithm);
            final SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
            mac.init(signingKey);
            return toHexString(mac.doFinal(payload.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
           throw e;
        }
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private static Mac getMacInstance(String algorithm) throws NoSuchAlgorithmException {
        Mac macInstance = macInstancesMap.get(algorithm);
        if (macInstance == null) {
            macInstance = Mac.getInstance(algorithm);
            macInstancesMap.put(algorithm, macInstance);
        }
        return macInstance;
    }
}
