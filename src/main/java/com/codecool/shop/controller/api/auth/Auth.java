package com.codecool.shop.controller.api.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Auth {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            byte[] hash = digest.digest(password.getBytes());
            return toHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHexString(byte[] hash) {
        char[] hexChars = new char[hash.length * 2];
        for (int i = 0; i < hash.length; i++) {
            int n = hash[i] & 0xFF;
            hexChars[i * 2] = hexArray[n >>> 4];
            hexChars[i * 2 + 1] = hexArray[n & 0x0F];
        }
        return new String(hexChars);
    }

    public static boolean verifyPasswordHash(String input, String hash) {
        String inputHash = hashPassword(input);
        return inputHash.equals(hash);
    }

}
