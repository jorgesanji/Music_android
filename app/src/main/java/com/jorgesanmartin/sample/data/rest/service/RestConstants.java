package com.jorgesanmartin.sample.data.rest.service;

/**
 * Created by jorgesanmartin on 11/2/17.
 */
//    public static  final String BaseUrl = "http://gateway.marvel.com/v1/public/characters?apikey=a97e89847d934d0d551f6252cb4be16f&hash=HASH&ts=TIMESTAMP";
/*
For example, a user with a public key of "1234" and a private key of "abcd"
could construct a valid call as follows:
(the hash value is the md5 digest of 1abcd1234)
http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150
 */
public class RestConstants {
    public static  final String BaseUrl = "http://gateway.marvel.com/";
    public static  final String publicKey = "a97e89847d934d0d551f6252cb4be16f";
    //public static  final String publicKey = "ca2fa3008b240006562cda3ec9240aa4";
    public static  final String privateKey = "978985e55e35edf030a37de670b4ea650cf2e580";
}
