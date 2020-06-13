package com.sujeet.Practise;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Crypto {

    public static byte[] createSha256(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(encodedhash));
        return encodedhash;
    }

    public static void main(String[] args) {
        try {
            createSha256("124");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

    }
}
