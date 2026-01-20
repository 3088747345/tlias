package javaweb.tlias.filter;

import javaweb.tlias.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//"/*"和"/**"的区别：
//"/*"：只拦截当前目录下的请求，不包括子目录。
//"/**"：匹配所有路径，包括一级路径和多级路径，例如"/login"和"/login/123"。

//过滤器和拦截器的区别：
//过滤器（Filter）：
//    1. 基于Servlet规范，运行在Servlet容器中。
//    2. 可以拦截所有请求，包括静态资源。
//    3. 可以在请求到达目标资源之前或之后对请求进行处理。
//    4. 不能访问Spring容器中的Bean，因为它在容器初始化之前就已经存在。

//拦截器（Interceptor）：
//    1. 基于Spring框架，运行在Spring容器中。
//    2. 只能拦截Spring管理的Bean的请求，不能拦截静态资源。
//    3. 可以在请求到达目标资源之前或之后对请求进行处理。
//    4. 可以访问Spring容器中的Bean，因为它在容器初始化之后才存在。


/**
 * 令牌校验的拦截器
 */
@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
//        //1. 获取到请求路径
//        String requestURI = request.getRequestURI(); // /employee/login
//
//        //2. 判断是否是登录请求, 如果路径中包含 /login, 说明是登录操作, 放行
//        if (requestURI.contains("/login")){
//            log.info("登录请求, 放行");
//            return true;
//        }

        //3. 获取请求头中的token
        String token = request.getHeader("token");

        //4. 判断token是否存在, 如果不存在, 说明用户没有登录, 返回错误信息(响应401状态码)
        if (token == null || token.isEmpty()){
            log.info("令牌为空, 响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5. 如果token存在, 校验令牌, 如果校验失败 -> 返回错误信息(响应401状态码)
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法, 响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6. 校验通过, 放行
        log.info("令牌合法, 放行");
        return true;
    }
}
