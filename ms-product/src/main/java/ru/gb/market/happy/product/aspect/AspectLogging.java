package ru.gb.market.happy.product.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    @Before("execution(public * ru.gb.market.happy.product.controllers.ProductController.*(..))")
    public void beforeAllMethods(JoinPoint joinPoint){
        System.out.println("Hello Aspect");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        for (Object o: args ) {
            System.out.println("Аргумент: " + o);
        }

    }

}
