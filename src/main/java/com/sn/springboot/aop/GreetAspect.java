package com.sn.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class GreetAspect {
    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.sn.springboot.aop.CoffeeShop.sale(..))")
    public void sale() {

    }

    @Before("sale()")
    public void welcome() {
        System.out.println("前置通知：欢迎！");
    }

    @AfterReturning(value = "sale()", returning = "result")
    public void cashier(Object result) {
        System.out.println("方法执行结果：" + result);
        System.out.println("后置通知：请扫码付款！");
    }

    /**
     * 每个方法都可以添加JoinPoint，来获取被增强方法的信息
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "sale()", throwing = "e")
    public void soldOut(JoinPoint jp, Exception e) {
        System.out.println(jp.getSignature().getName());
        System.out.println("异常信息：" + e.getMessage());
        System.out.println("异常通知：商品名不能为空！");
    }

    @After("sale()")
    public void goodbye() {
        System.out.println("最终通知：再见！");
    }
}
