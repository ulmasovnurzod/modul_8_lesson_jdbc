package uz.pdp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {

    @Before("execution(* uz.pdp.daos.UserDAO.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Method ishlatildi " + joinPoint.getSignature());
    }

    @AfterReturning("execution( * uz.pdp.daos.UserDAO.*())")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Method tugadi " + joinPoint.getSignature());
    }


}
