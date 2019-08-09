package i.am.whp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng
 * @date 2019/8/9
 */
@Aspect
@Component
public class AspectOrientedProgramming {

    @Pointcut("execution(public * i.am.whp.controller.*.*(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("----------------before---------------------");
        System.out.println("Logging before " + joinPoint.getSignature().getName());
    }
}
