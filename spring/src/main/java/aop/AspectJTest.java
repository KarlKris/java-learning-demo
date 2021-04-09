package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Description AspectJ 测试
 * @Author li-yuanwen
 * @Date 2021/3/25 10:31
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.process(..))")
    public void process() {
        System.out.println("------执行test方法-------");
    }

    @Before("process()")
    public void testBefore() {
        System.out.println("------执行testBefore方法------");
    }

    @After("process()")
    public void testAfter() {
        System.out.println("------执行testAfter方法------");
    }

    @Around("process()")
    public Object aroundTest(ProceedingJoinPoint point) {
        System.out.println("------开始执行aroundTest方法------");
        Object obj = null;
        try {
            obj = point.proceed();
        }catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("------执行aroundTest方法完成------");
        return obj;
    }

}
