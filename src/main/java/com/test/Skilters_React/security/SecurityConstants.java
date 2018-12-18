package com.test.Skilters_React.security;

public class SecurityConstants {
	public static final String SIGN_UP_URL = "/users/register";
	public static final String LOGIN_URL = "/users/login";
	public static final String LIST_USERS = "/users/listusers";
	public static final String H2_URL = "/h2-console/**";
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 300_000; //30 seconds
}
