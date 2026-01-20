package javaweb.tlias.filter;

//详细介绍一下Filter的运行过程
// 1. 客户端发送请求到服务器
// 2. 服务器接收到请求后，根据请求路径匹配对应的Filter
// 3. 如果匹配到Filter，服务器会先调用Filter的doFilter方法
// 4. 在doFilter方法中，开发者可以编写自定义的逻辑，例如权限校验、日志记录等
// 5. 如果校验通过，调用FilterChain的doFilter方法，将请求传递给下一个Filter或目标资源
// 6. 如果校验不通过，直接返回错误响应，不再调用后续的Filter或目标资源
// 7. 目标资源处理完成后，返回Filter链，继续处理后续的Filter或返回响应给客户端

//过滤器链：多个Filter按顺序组成的链，每个Filter都有机会处理请求和响应
// 每个Filter在doFilter方法中可以选择是否继续传递请求给下一个Filter或直接返回响应
// 最后一个Filter通常是目标资源，负责处理实际的业务逻辑

import javaweb.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取到请求路径
        String requestURI = request.getRequestURI(); // /employee/login

        //2. 判断是否是登录请求, 如果路径中包含 /login, 说明是登录操作, 放行
        if (requestURI.contains("/login")){
            log.info("登录请求, 放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3. 获取请求头中的token
        String token = request.getHeader("token");

        //4. 判断token是否存在, 如果不存在, 说明用户没有登录, 返回错误信息(响应401状态码)
        if (token == null || token.isEmpty()){
            log.info("令牌为空, 响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5. 如果token存在, 校验令牌, 如果校验失败 -> 返回错误信息(响应401状态码)
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法, 响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6. 校验通过, 放行
        log.info("令牌合法, 放行");
        filterChain.doFilter(request, response);
    }
}
