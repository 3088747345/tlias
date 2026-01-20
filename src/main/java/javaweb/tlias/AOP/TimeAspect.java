package javaweb.tlias.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
//AOP：Aspect Oriented Programming，面向切面编程
//作用：在不改变原有代码的情况下，对原有代码进行功能增强
//应用场景：日志记录、性能监控、事务管理、安全检查等

//连接点、切入点、通知、切面、目标对象
//动态代理：在运行时，根据目标对象的类型，动态创建一个代理对象，该代理对象与目标对象实现相同的接口，
//并在调用目标对象的方法时，添加额外的功能（如日志记录、性能监控等）

//通知类型：前置通知（Before）、后置通知（After）、异常通知（AfterThrowing）、最终通知（AfterReturning）、环绕通知（Around）

//execution切入点表达式：execution(访问修饰符（可省略） 返回值类型 包名.类名.方法名(参数列表) throws 异常类型（可省略）)

//通配符：
//*：匹配任意字符（包括0个字符）
//..：匹配任意数量的参数（包括0个参数）

//对于Around通知，需要在方法中添加ProceedingJoinPoint参数，用于执行原始的方法；其他通知类型的方法中，
//可以直接添加JoinPoint参数，用于获取连接点的信息（如方法签名、参数等）
//JoinPoint方法：
//1. getSignature()：获取连接点的方法签名（如方法名、参数等）
//2. getArgs()：获取连接点的参数
//3. getTarget()：获取连接点的目标对象
//4. getThis()：获取连接点的代理对象
*/

@Slf4j
@Aspect //标识当前是一个AOP类
@Component
@Order(1) //设置AOP的执行顺序，目标对象前数字越小，优先级越高,目标对象后数字越小，优先级越低
public class TimeAspect {

    //定义一个切入点表达式，匹配所有在service.impl包下的方法
    @Pointcut("execution(* javaweb.tlias.service.impl.*.*(..))")
    public void pt(){}
    
    //在匹配的方法上添加Around通知
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1. 记录方法运行的开始时间
        long begin = System.currentTimeMillis();

        //2. 执行原始的方法
        Object result = pjp.proceed();

        //3. 记录方法运行的结束时间, 记录耗时
        long end = System.currentTimeMillis();
        log.info("方法 {} 执行耗时: {}ms", pjp.getSignature() ,end-begin);
        return  result;
    }

}