package com.sn.springboot.aop;

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

    @AfterReturning("sale()")
    public void cashier() {
        System.out.println("后置通知：请扫码付款！");
    }

    @AfterThrowing("sale()")
    public void soldOut() {
        System.out.println("异常通知：商品名不能为空！");
    }

    @After("sale()")
    public void goodbye() {
        System.out.println("最终通知：再见！");
    }
}
