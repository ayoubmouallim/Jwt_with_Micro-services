package com.example.authservice.utils;

public class JwtUtils {

    public static final String SECRET = "my-secret-key";
    public static final String AUTH_HEAD = "Authorization";
    public static final String PREFIXE = "Bearer ";

    public static final long EXPIRE_ACCESS_TOKEN =5*60*1000 ;
    public static final long EXPIRE_REFRESH_TOKEN =15*60*1000 ;


}
