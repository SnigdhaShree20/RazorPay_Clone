package com.project.razorpay.Common.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class RandomizerUtil {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String randomnBase64(int length)
    {
       // UUID.randomUUID().toString().replaceAll("-","");
        byte[] buff=new byte[length*4/3];//to determine characters of base 64
        SECURE_RANDOM.nextBytes(buff);//will create randomn number of size length/2
        //byte range is {-128,127}

        return Base64.getUrlEncoder().withoutPadding().encodeToString(buff);

    }
}
