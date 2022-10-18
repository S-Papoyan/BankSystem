package com.digi.banksystem.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(rawPassword.toString().getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder haxString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2) {
                    h.insert(0, "0");
                }
                haxString.append(h);
            }
            return haxString.toString();
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
    }

    @Override
    public boolean matches(CharSequence password, String encodedPassword) {
        return new MD5Encoder().encode(password).equals(encodedPassword);
    }
}
