package com.picfood.server.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * Created by shawn on 2018/3/16.
 */
public class JwtUtil {
    public static final long EXPIRATION_TIME = 3600_000_000L; // 1000 hour
    public static final String SECRET = "ThisIsASecret";//please change to your own encryption secret.
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_ID = "userId";

    public static String generateToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put(USER_ID, userId);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return TOKEN_PREFIX + jwt;
    }

    public static HttpServletRequest validateTokenAndAddUserIdToHeader(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                return new CustomHttpServletRequest(request, body);
            } catch (Exception e) {
                throw new TokenValidationException(e.getMessage());
            }
        } else {
            throw new TokenValidationException("Missing token");
        }
    }

    public static class CustomHttpServletRequest extends HttpServletRequestWrapper {
        private Map<String, String> claims;

        public CustomHttpServletRequest(HttpServletRequest request, Map<String, ?> claims) {
            super(request);
            this.claims = new HashMap<>();
            claims.forEach((k, v) -> this.claims.put(k, String.valueOf(v)));
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (claims != null && claims.containsKey(name)) {
                return Collections.enumeration(Arrays.asList(claims.get(name)));
            }
            return super.getHeaders(name);
        }

        public Map<String, String> getClaims() {
            return claims;
        }
    }

    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}

// private static final String REDIS_SET_ACTIVE_SUBJECTS = "active-subjects";
//
// public static String generateToken(String signingKey, String subject) {
//     long nowMillis = System.currentTimeMillis();
//     Date now = new Date(nowMillis);
//     JwtBuilder builder = Jwts.builder()
//             .setSubject(subject)
//             .setIssuedAt(now)
//             .signWith(SignatureAlgorithm.HS256, signingKey);
//     String token = builder.compact();
//     RedisUtil.INSTANCE.sadd(REDIS_SET_ACTIVE_SUBJECTS, subject);
//     return token;
// }
//
// public static String parseToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey) {
//     String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//     if (token == null) return null;
//     String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
//     if (!RedisUtil.INSTANCE.sismember(REDIS_SET_ACTIVE_SUBJECTS, subject)) return null;
//     return subject;
// }
//
// public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
//     RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, (String) httpServletRequest.getAttribute("username"));
// }