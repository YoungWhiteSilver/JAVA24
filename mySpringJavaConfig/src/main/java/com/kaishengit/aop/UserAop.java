package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Component 不属于Dao，service的用这种spring注释
 * @Aspect 表明该类为Aop通知类
 *
 * @author silver
 * @date 2017/10/30
 */
@Component
@Aspect
public class UserAop {

    /**
     * 配置切入点表达式
     */
    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointcut() {}

    /**
     * 前置通知
     */
    @Before("pointcut()")
    public void beforeAdvice() {

        System.out.println("机器人说：");

    }

    /**
     * 后置通知
     */
    @AfterReturning("pointcut()" )
    public void afterReturning() {

        System.out.println("Spring");

    }

   /*
    带返回值方法使用后置通知可以通过这种方法返回值，把返回值复制给result
   @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturning(Object result) {

        System.out.println("Spring");

    }*/


    /**
     * 异常通知
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing() {

        System.out.println("error");
    }

    /**
     * 最终通知
     */
    @After("pointcut()")
    public void finallyAdvice() {

        System.out.println("最终通知");
    }

    /**
     * 环绕通知
     * @param joinPoint
     * return reslut 如果方法存在返回值，则result为方法的返回值,可以对结果进行处理后再返回
     *
     */
    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {

        Object result = null;
        try{

            System.out.println("前");
            result = joinPoint.proceed();
            System.out.println("后" + result);

        } catch (Throwable throwable) {

            System.out.println("异常");

        } finally {

            System.out.println("最终通知");

        }

        return result;

    }















}
