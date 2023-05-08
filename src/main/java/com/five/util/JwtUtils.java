package com.five.util;

import com.five.exception.ParamException;
import com.five.vo.RCodeEnum;
import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author fly
 * @since 2022/12/7 20:12
 */
public class JwtUtils {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String TOKEN_SECRET = "*^%=joh#ads";

    /**
     * 默认token有效时长 30 分钟
     */
    private static final int DEFAULT_VALIDATE_TIME = 1000 * 60 * 30;

    /**
     * 解析Token
     */

    public static Claims parse(String jwt, String secretKey) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return claims;
    }

    public static Claims parse(String jwt) {
        return parse(jwt, TOKEN_SECRET);
    }

    /**
     * 生成Token
     */
    public static String generateToken(TokenInfo tokenInfo) {
        return generateToken(tokenInfo, DEFAULT_VALIDATE_TIME);
    }


    public static String generateToken(TokenInfo tokenInfo, int millisecond) {
        HashMap<String, Object> tokenData = new HashMap<>();
        tokenData.put("userId", tokenInfo.getUserId());
        tokenData.put("realName", tokenInfo.getRealName());
        tokenData.put("roleId", tokenInfo.getRoleId());
        return JwtUtils.genToken(tokenData, millisecond);
    }

    /**
     * 生成 token 提供者
     */
    private static String genToken(Map<String, Object> claims, int millisecond) {
        //获取当前的时间
        Calendar calendar = Calendar.getInstance();
        //获取系统当前时间
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        //失效的时间
        calendar.add(Calendar.MILLISECOND, millisecond);
        //拿到预定过期时间的日期
        Date endTime = calendar.getTime();
        JwtBuilder jwtBuilder = Jwts.builder()
                //签名算法
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                //签发时间
                .setIssuedAt(new Date())
                //到期时间
                .setExpiration(endTime)
                //playLoad
                .setClaims(claims)
                //签发者
                .setIssuer("five")
                //接收者
                .setAudience("user");
        return TOKEN_PREFIX + jwtBuilder.compact();
    }

    /**
     * 从 claims 中获取指定信息
     */
    private static <T> T getInfoData(Claims claims, String key, Class<T> clazz) {
        T t;
        try {
            t = claims.get(key, clazz);
        } catch (Exception e) {
            throw new ParamException(RCodeEnum.INVALID_TOKEN);
        }
        return t;
    }

    /**
     * 是否是合法的token
     */
    public static boolean isValid(String token) {
        return token.contains(TOKEN_PREFIX);
    }

    /**
     * 从Token中获取token信息
     */
    public static TokenInfo getTokenInfo(String token) {
        token = token.replaceAll(TOKEN_PREFIX, "");
        Claims parse = parse(token);
        parse.get("", String.class);
        return new TokenInfo.Builder()
                .username(getInfoData(parse, "realName", String.class))
                .userId(getInfoData(parse, "userId", Integer.class))
                .role(getInfoData(parse, "roleId", Integer.class))
                .build();
    }

}
