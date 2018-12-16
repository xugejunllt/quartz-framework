package com.chikage.framework.quartzframework.log;

import com.chikage.framework.quartzframework.utils.ApplicationContextWare;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
public class ApiLogAop implements Ordered {
    private Logger logger = LoggerFactory.getLogger(ApiLogAop.class);

    public ApiLogAop() {
        System.out.printf("{test");
    }

    @Pointcut("@annotation(com.chikage.framework.quartzframework.log.ApiAnnotaion)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder logMethod = new StringBuilder(joinPoint.getSignature().getDeclaringTypeName());
        logMethod.append(".");
        logMethod.append(joinPoint.getSignature().getName());
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("非法参数使用");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        ApiAnnotaion freemudApiAnnotaion = currentMethod.getAnnotation(ApiAnnotaion.class);
        Object requestData = getLogArgs(currentMethod, joinPoint);//对象在堆中，变量会随着接口里变量的变化而变化
        Object object = joinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        ApiLog.infoConvertJson(logMethod.toString(), freemudApiAnnotaion.logMessage(), LogTreadLocal.getTrackingNo(),
                ApplicationContextWare.getAppName(), request, startTime, (System.currentTimeMillis() - startTime),
                requestData, object);
        return object;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        Signature sig = joinPoint.getSignature();
        Method currentMethod = null;
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("非法参数使用");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        try {
            currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
        StringBuilder logMethod = new StringBuilder(joinPoint.getSignature().getDeclaringTypeName());
        logMethod.append(".");
        logMethod.append(joinPoint.getSignature().getName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ErrorLog.errorConvertJson(logMethod.toString(), "出错", LogTreadLocal.getTrackingNo(), ApplicationContextWare.getAppName(), request, System.currentTimeMillis(), System.currentTimeMillis(), getLogArgs(currentMethod, joinPoint), e);
    }

    private List getLogArgs(Method method, JoinPoint joinPoint) {
        if (method == null) {
            return null;
        }
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return null;
        }
        List logArgs = new ArrayList();
        Parameter[] parameters = method.getParameters();
        for (int j = 0; j < parameters.length; j++) {
            Parameter parameter = parameters[j];
            LogParams logParams = parameter.getAnnotation(LogParams.class);
            if (logParams == null) {
                continue;
            }
            logArgs.add(args[j]);
        }
        return logArgs;
    }


    /**
     * 请勿更改此aop顺序 日志是必须要的
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
