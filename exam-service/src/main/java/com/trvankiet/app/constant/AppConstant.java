package com.trvankiet.app.constant;

import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import javax.crypto.SecretKey;

public class AppConstant {

    public static final String FULL_DATE_TIME_FORMAT = "ddMMyyyyHHmmssSSSSSS";
    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSSSSS";
    public static final String LOCAL_DATE_TIME_FORMAT_WITHOUT_MILLIS = "dd-MM-yyyy HH:mm:ss";
    public static final String LOCAL_TIME_FORMAT = "HH:mm:ss:SSSSSS";

    @SneakyThrows
    public static SecretKey getSecretKey() {
        ClassPathResource resource = new ClassPathResource("static/secret.key");
        byte[] keyBytes = StreamUtils.copyToByteArray(resource.getInputStream());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
