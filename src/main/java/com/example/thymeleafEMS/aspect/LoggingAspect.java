package com.example.thymeleafEMS.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.thymeleafEMS.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.example.thymeleafEMS.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.example.thymeleafEMS.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====>>>> in @Before : calling Method - "+method);
        //System.out.println("Arguments - ");
        for(Object arg :joinPoint.getArgs()){
            logger.info("====>>> argument : "+arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint joinPoint,Object theResult){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====>>>> in @AfterReturning : calling Method - "+method);

        logger.info("====>>> result : "+theResult);
    }
}
