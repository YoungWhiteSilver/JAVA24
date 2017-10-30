package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author silver
 * @date 2017/10/30
 */
public class AopAdvice {

    public void beforeAdvice() {

        System.out.println("前置通知");

    }

    public void afterReturning(Object res) {

        System.out.println("后置通知" + res);

    }

    public void afterThrowing() {

        System.out.println("异常通知");

    }

    public void after() {

        System.out.println("最终通知");

    }

    /**环绕通知
     * @param joinPoint
     * @return
     */
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {

        Object result = null;
        try {

            System.out.println("前置");
            result = joinPoint.proceed();
            System.out.println("后置");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常");
        } finally {

            System.out.println("最终通知");

        }

        return result;

    }

}
