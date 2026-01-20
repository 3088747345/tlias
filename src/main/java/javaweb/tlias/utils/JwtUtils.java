package javaweb.tlias.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



//Jwt技术：JSON Web Token（JWT）是一种用于在网络应用间安全传递信息的开放标准（RFC 7519）。
//它通常用于身份验证和授权场景，例如用户登录后，服务器可以生成一个JWT令牌并返回给客户端，
//客户端在后续请求中携带该令牌，服务器可以验证令牌的有效性来确定用户是否有权限访问受保护的资源。



/**
 * JWT工具类，用于生成和解析JWT令牌
 * 支持存储id和username信息
 */
public class JwtUtils {

    // 密钥，用于签名JWT
    private static final String SECRET_KEY = "tlias-secret-key-2024";

    // 过期时间：12小时
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成JWT令牌
     * @param id 用户ID
     * @param username 用户名
     * @return JWT令牌字符串
     */
    public static String generateToken(Integer id, String username) {
        // 创建存储用户信息的声明
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);

        // 设置过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        // 生成JWT令牌
        return Jwts.builder()
                // 存储用户信息
                .setClaims(claims)
                // 设置过期时间
                .setExpiration(expireDate)
                // 使用HS256算法签名
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 生成紧凑的JWT字符串
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token JWT令牌字符串
     * @return JWT声明对象，包含用户信息
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                // 设置密钥
                .setSigningKey(SECRET_KEY)
                // 解析令牌
                .parseClaimsJws(token)
                // 获取声明部分
                .getBody();
    }

    /**
     * 从JWT令牌中获取用户ID
     * @param token JWT令牌字符串
     * @return 用户ID
     */
    public static Integer getIdFromToken(String token) {
        Claims claims = parseToken(token);
        return (Integer) claims.get("id");
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token JWT令牌字符串
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return (String) claims.get("username");
    }
}