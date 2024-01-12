package com.mdd.auto_test_back.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    public static String encode(String originalString) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(originalString.getBytes());
            byte[] result = messageDigest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : result) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
